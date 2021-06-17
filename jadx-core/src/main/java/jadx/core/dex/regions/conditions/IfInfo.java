package jadx.core.dex.regions.conditions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.InsnNode;
import jadx.Initializer;
import org.jetbrains.annotations.Nullable;

public final class IfInfo {
	private final IfCondition condition;
	private final Set<BlockNode> mergedBlocks;
	private final BlockNode thenBlock;
	private final BlockNode elseBlock;
	private final Set<BlockNode> skipBlocks;
	private final List<InsnNode> forceInlineInsns;
	@Nullable
	private BlockNode outBlock;
	@Deprecated
	private BlockNode ifBlock;

	public IfInfo(IfCondition condition, BlockNode thenBlock, BlockNode elseBlock) {
		this(condition, thenBlock, elseBlock, new HashSet<>(), new HashSet<>(), new ArrayList<>());
	}

	public IfInfo(IfInfo info, @Nullable BlockNode thenBlock, @Nullable BlockNode elseBlock) {
		this(info.getCondition(), thenBlock, elseBlock,
				info.getMergedBlocks(), info.getSkipBlocks(), info.getForceInlineInsns());
	}

	private IfInfo(IfCondition condition, @Nullable BlockNode thenBlock, @Nullable BlockNode elseBlock,
			Set<BlockNode> mergedBlocks, Set<BlockNode> skipBlocks, List<InsnNode> forceInlineInsns) {
		this.condition = condition;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock;
		this.mergedBlocks = mergedBlocks;
		this.skipBlocks = skipBlocks;
		this.forceInlineInsns = forceInlineInsns;
	}

	public static IfInfo invert(IfInfo info) {
		IfCondition invertedCondition = IfCondition.invert(info.getCondition());
		IfInfo tmpIf = new IfInfo(invertedCondition,
				info.getElseBlock(), info.getThenBlock(),
				info.getMergedBlocks(), info.getSkipBlocks(), info.getForceInlineInsns());
		tmpIf.setIfBlock(info.getIfBlock());
		return tmpIf;
	}

	public void merge(IfInfo... arr) {
		for (IfInfo info : arr) {
			mergedBlocks.addAll(info.getMergedBlocks());
			skipBlocks.addAll(info.getSkipBlocks());
			addInsnsForForcedInline(info.getForceInlineInsns());
		}
	}

	public IfCondition getCondition() {
		return condition;
	}

	public Set<BlockNode> getMergedBlocks() {
		return mergedBlocks;
	}

	public Set<BlockNode> getSkipBlocks() {
		return skipBlocks;
	}

	public BlockNode getThenBlock() {
		return thenBlock;
	}

	public BlockNode getElseBlock() {
		return elseBlock;
	}

	@Nullable
	public BlockNode getOutBlock() {
		return outBlock;
	}

	@Initializer
	public void setOutBlock(@Nullable BlockNode outBlock) {
		this.outBlock = outBlock;
	}

	public BlockNode getIfBlock() {
		return ifBlock;
	}

	@Initializer
	public void setIfBlock(BlockNode ifBlock) {
		this.ifBlock = ifBlock;
	}

	public List<InsnNode> getForceInlineInsns() {
		return forceInlineInsns;
	}

	public void resetForceInlineInsns() {
		forceInlineInsns.clear();
	}

	public void addInsnsForForcedInline(List<InsnNode> insns) {
		forceInlineInsns.addAll(insns);
	}

	@Override
	public String toString() {
		return "IfInfo: then: " + thenBlock + ", else: " + elseBlock;
	}
}
