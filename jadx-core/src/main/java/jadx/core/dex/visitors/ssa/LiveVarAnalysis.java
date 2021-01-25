package jadx.core.dex.visitors.ssa;

import java.util.BitSet;
import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.exceptions.JadxRuntimeException;

public class LiveVarAnalysis {
	private static final Logger LOG = LoggerFactory.getLogger(LiveVarAnalysis.class);

	private final MethodNode mth;

	@Nullable
	private BitSet[] uses;

	@Nullable
	private BitSet[] defs;

	@Nullable
	private BitSet[] liveIn;

	@Nullable
	private BitSet[] assignBlocks;

	public LiveVarAnalysis(MethodNode mth) {
		this.mth = mth;
	}

	public void runAnalysis() {
		int bbCount = mth.getBasicBlocks().size();
		int regsCount = mth.getRegsCount();
		this.uses = initBitSetArray(bbCount, regsCount);
		this.defs = initBitSetArray(bbCount, regsCount);
		this.assignBlocks = initBitSetArray(regsCount, bbCount);
		fillBasicBlockInfo();
		processLiveInfo();
	}

	public BitSet getAssignBlocks(int regNum) {
		return assignBlocks[regNum];
	}

	public boolean isLive(int blockId, int regNum) {
		if (blockId >= liveIn.length) {
			LOG.warn("LiveVarAnalysis: out of bounds block: {}, max: {}", blockId, liveIn.length);
			return false;
		}
		return liveIn[blockId].get(regNum);
	}

	public boolean isLive(BlockNode block, int regNum) {
		return isLive(block.getId(), regNum);
	}

	private void fillBasicBlockInfo() {
		for (BlockNode block : mth.getBasicBlocks()) {
			int blockId = block.getId();
			BitSet gen = uses[blockId];
			BitSet kill = defs[blockId];
			for (InsnNode insn : block.getInstructions()) {
				for (InsnArg arg : insn.getArguments()) {
					if (arg.isRegister()) {
						int regNum = ((RegisterArg) arg).getRegNum();
						if (!kill.get(regNum)) {
							gen.set(regNum);
						}
					}
				}
				RegisterArg result = insn.getResult();
				if (result != null) {
					int regNum = result.getRegNum();
					kill.set(regNum);
					assignBlocks[regNum].set(blockId);
				}
			}
		}
	}

	private void processLiveInfo() {
		int bbCount = mth.getBasicBlocks().size();
		int regsCount = mth.getRegsCount();
		BitSet[] liveInBlocks = initBitSetArray(bbCount, regsCount);
		List<BlockNode> blocks = mth.getBasicBlocks();
		int blocksCount = blocks.size();
		int iterationsLimit = blocksCount * 10;
		boolean changed;
		int k = 0;
		do {
			changed = false;
			for (BlockNode block : blocks) {
				int blockId = block.getId();
				BitSet prevIn = liveInBlocks[blockId];
				BitSet newIn = new BitSet(regsCount);
				for (BlockNode successor : block.getSuccessors()) {
					newIn.or(liveInBlocks[successor.getId()]);
				}
				newIn.andNot(defs[blockId]);
				newIn.or(uses[blockId]);
				if (!prevIn.equals(newIn)) {
					changed = true;
					liveInBlocks[blockId] = newIn;
				}
			}
			if (k++ > iterationsLimit) {
				throw new JadxRuntimeException("Live variable analysis reach iterations limit, blocks count: " + blocksCount);
			}
		} while (changed);

		this.liveIn = liveInBlocks;
	}

	private static BitSet[] initBitSetArray(int length, int bitsCount) {
		BitSet[] array = new BitSet[length];
		for (int i = 0; i < length; i++) {
			array[i] = new BitSet(bitsCount);
		}
		return array;
	}
}
