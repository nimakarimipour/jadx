package jadx.core.dex.attributes.nodes;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

import jadx.core.Consts;
import jadx.core.dex.attributes.AFlag;
import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.dex.instructions.args.RegisterArg;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;

public class MethodInlineAttr implements IAttribute {

	private static final MethodInlineAttr INLINE_NOT_NEEDED = new MethodInlineAttr(null, null);

	public static MethodInlineAttr markForInline(MethodNode mth, InsnNode replaceInsn) {
		Objects.requireNonNull(replaceInsn);
		List<RegisterArg> allArgRegs = mth.getAllArgRegs();
		int argsCount = allArgRegs.size();
		int[] regNums = new int[argsCount];
		for (int i = 0; i < argsCount; i++) {
			RegisterArg reg = allArgRegs.get(i);
			regNums[i] = reg.getRegNum();
		}
		MethodInlineAttr mia = new MethodInlineAttr(replaceInsn, regNums);
		mth.addAttr(mia);
		if (Consts.DEBUG) {
			mth.addAttr(AType.COMMENTS, "Removed for inline");
		} else {
			mth.add(AFlag.DONT_GENERATE);
		}
		return mia;
	}

	public static MethodInlineAttr inlineNotNeeded(MethodNode mth) {
		mth.addAttr(INLINE_NOT_NEEDED);
		return INLINE_NOT_NEEDED;
	}

	private final InsnNode insn;

	/**
	 * Store method arguments register numbers to allow remap registers
	 */
	private final int[] argsRegNums;

	private MethodInlineAttr(@Nullable InsnNode insn, int[] argsRegNums) {
		this.insn = insn;
		this.argsRegNums = argsRegNums;
	}

	public boolean notNeeded() {
		return insn == null;
	}

	public InsnNode getInsn() {
		return insn;
	}

	public int[] getArgsRegNums() {
		return argsRegNums;
	}

	@Override
	public AType<MethodInlineAttr> getType() {
		return AType.METHOD_INLINE;
	}

	@Override
	public String toString() {
		if (notNeeded()) {
			return "INLINE_NOT_NEEDED";
		}
		return "INLINE: " + insn;
	}
}
