package jadx.core.dex.instructions;

import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.InsnNode;
import javax.annotation.Nullable;
import jadx.core.NullUnmarked;

public final class ConstClassNode extends InsnNode {

	@Nullable private final ArgType clsType;

	public ConstClassNode(@Nullable ArgType clsType) {
		super(InsnType.CONST_CLASS, 0);
		this.clsType = clsType;
	}

	@Nullable public ArgType getClsType() {
		return clsType;
	}

	@Override
	public InsnNode copy() {
		return copyCommonParams(new ConstClassNode(clsType));
	}

	@NullUnmarked @Override
	public boolean isSame(@Nullable InsnNode obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ConstClassNode) || !super.isSame(obj)) {
			return false;
		}
		ConstClassNode other = (ConstClassNode) obj;
		return clsType.equals(other.clsType);
	}

	@Override
	public String toString() {
		return super.toString() + ' ' + clsType + ".class";
	}
}
