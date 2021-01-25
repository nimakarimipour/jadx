package jadx.core.dex.attributes.nodes;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import jadx.core.dex.attributes.AType;
import jadx.core.dex.attributes.IAttribute;
import jadx.core.dex.instructions.mods.ConstructorInsn;
import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.FieldNode;
import jadx.core.dex.nodes.MethodNode;

public class EnumClassAttr implements IAttribute {

	public static class EnumField {
		private final FieldNode field;
		private final ConstructorInsn constrInsn;

		@Nullable
		private ClassNode cls;

		public EnumField(FieldNode field, ConstructorInsn co) {
			this.field = field;
			this.constrInsn = co;
		}

		public FieldNode getField() {
			return field;
		}

		public ConstructorInsn getConstrInsn() {
			return constrInsn;
		}

		@Nullable
		public ClassNode getCls() {
			return cls;
		}

		public void setCls(ClassNode cls) {
			this.cls = cls;
		}

		@Override
		public String toString() {
			return field + "(" + constrInsn + ") " + cls;
		}
	}

	private final List<EnumField> fields;

	@Nullable
	private MethodNode staticMethod;

	public EnumClassAttr(List<EnumField> fields) {
		this.fields = fields;
	}

	public List<EnumField> getFields() {
		return fields;
	}

	@Nullable
	public MethodNode getStaticMethod() {
		return staticMethod;
	}

	public void setStaticMethod(MethodNode staticMethod) {
		this.staticMethod = staticMethod;
	}

	@Override
	public AType<EnumClassAttr> getType() {
		return AType.ENUM_CLASS;
	}

	@Override
	public String toString() {
		return "Enum fields: " + fields;
	}
}
