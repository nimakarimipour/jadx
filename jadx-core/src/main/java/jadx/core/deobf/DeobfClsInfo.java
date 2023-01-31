package jadx.core.deobf;

import jadx.core.dex.nodes.ClassNode;
import javax.annotation.Nullable;
import jadx.core.NullUnmarked;

class DeobfClsInfo {
	private final Deobfuscator deobfuscator;
	private final ClassNode cls;
	@Nullable private final PackageNode pkg;
	private final String alias;

	public DeobfClsInfo(Deobfuscator deobfuscator, ClassNode cls, @Nullable PackageNode pkg, String alias) {
		this.deobfuscator = deobfuscator;
		this.cls = cls;
		this.pkg = pkg;
		this.alias = alias;
	}

	@NullUnmarked public String makeNameWithoutPkg() {
		String prefix;
		ClassNode parentClass = cls.getParentClass();
		if (parentClass != cls) {
			DeobfClsInfo parentDeobfClsInfo = deobfuscator.getClsMap().get(parentClass.getClassInfo());
			if (parentDeobfClsInfo != null) {
				prefix = parentDeobfClsInfo.makeNameWithoutPkg();
			} else {
				prefix = deobfuscator.getNameWithoutPackage(parentClass.getClassInfo());
			}
			prefix += Deobfuscator.INNER_CLASS_SEPARATOR;
		} else {
			prefix = "";
		}
		return prefix + (this.alias != null ? this.alias : this.cls.getShortName());
	}

	@NullUnmarked public String getFullName() {
		return pkg.getFullAlias() + Deobfuscator.CLASS_NAME_SEPARATOR + makeNameWithoutPkg();
	}

	public ClassNode getCls() {
		return cls;
	}

	@Nullable public PackageNode getPkg() {
		return pkg;
	}

	public String getAlias() {
		return alias;
	}
}
