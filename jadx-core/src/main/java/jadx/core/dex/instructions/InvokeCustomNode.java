package jadx.core.dex.instructions;

import org.jetbrains.annotations.Nullable;

import jadx.api.plugins.input.data.MethodHandleType;
import jadx.api.plugins.input.insns.InsnData;
import jadx.core.dex.info.MethodInfo;
import jadx.core.dex.instructions.args.InsnArg;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.utils.InsnUtils;
import jadx.core.NullUnmarked;

public class InvokeCustomNode extends InvokeNode {
	@Nullable private MethodInfo implMthInfo;
	@Nullable private MethodHandleType handleType;
	@Nullable private InsnNode callInsn;
	private boolean inlineInsn;
	private boolean useRef;

	public InvokeCustomNode(MethodInfo lambdaInfo, InsnData insn, boolean instanceCall, boolean isRange) {
		super(lambdaInfo, insn, InvokeType.CUSTOM, instanceCall, isRange);
	}

	private InvokeCustomNode(@Nullable MethodInfo mth, InvokeType invokeType, int argsCount) {
		super(mth, invokeType, argsCount);
	}

	@Override
	public InsnNode copy() {
		InvokeCustomNode copy = new InvokeCustomNode(getCallMth(), getInvokeType(), getArgsCount());
		copyCommonParams(copy);
		copy.setImplMthInfo(implMthInfo);
		copy.setHandleType(handleType);
		copy.setCallInsn(callInsn);
		copy.setInlineInsn(inlineInsn);
		copy.setUseRef(useRef);
		return copy;
	}

	@NullUnmarked @Override
	public boolean isSame(@Nullable InsnNode obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof InvokeCustomNode) || !super.isSame(obj)) {
			return false;
		}
		InvokeCustomNode other = (InvokeCustomNode) obj;
		return handleType == other.handleType
				&& implMthInfo.equals(other.implMthInfo)
				&& callInsn.isSame(other.callInsn)
				&& inlineInsn == other.inlineInsn
				&& useRef == other.useRef;
	}

	@Nullable public MethodInfo getImplMthInfo() {
		return implMthInfo;
	}

	public void setImplMthInfo(@Nullable MethodInfo implMthInfo) {
		this.implMthInfo = implMthInfo;
	}

	@Nullable public MethodHandleType getHandleType() {
		return handleType;
	}

	public void setHandleType(@Nullable MethodHandleType handleType) {
		this.handleType = handleType;
	}

	@Nullable public InsnNode getCallInsn() {
		return callInsn;
	}

	public void setCallInsn(@Nullable InsnNode callInsn) {
		this.callInsn = callInsn;
	}

	public boolean isInlineInsn() {
		return inlineInsn;
	}

	public void setInlineInsn(boolean inlineInsn) {
		this.inlineInsn = inlineInsn;
	}

	public boolean isUseRef() {
		return useRef;
	}

	public void setUseRef(boolean useRef) {
		this.useRef = useRef;
	}

	@NullUnmarked @Nullable
	public BaseInvokeNode getInvokeCall() {
		if (callInsn.getType() == InsnType.INVOKE) {
			return (BaseInvokeNode) callInsn;
		}
		return null;
	}

	@Override
	public @Nullable InsnArg getInstanceArg() {
		return null;
	}

	@Override
	public boolean isStaticCall() {
		return true;
	}

	@Override
	public int getFirstArgOffset() {
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(InsnUtils.formatOffset(offset)).append(": INVOKE_CUSTOM ");
		if (getResult() != null) {
			sb.append(getResult()).append(" = ");
		}
		appendArgs(sb);
		sb.append("\n handle type: ").append(handleType);
		sb.append("\n lambda: ").append(implMthInfo);
		sb.append("\n call insn: ").append(callInsn);
		return sb.toString();
	}
}
