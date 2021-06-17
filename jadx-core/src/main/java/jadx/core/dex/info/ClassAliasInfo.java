package jadx.core.dex.info;

import org.jetbrains.annotations.Nullable;
import jadx.Initializer;

class ClassAliasInfo {
	private final String shortName;
	
	private final String pkg;
	
	private String fullName;

	ClassAliasInfo( String pkg, String shortName) {
		this.pkg = pkg;
		this.shortName = shortName;
	}

	
	public String getPkg() {
		return pkg;
	}

	public String getShortName() {
		return shortName;
	}

	
	public String getFullName() {
		return fullName;
	}

	@Initializer
	public void setFullName( String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Alias{" + shortName + ", pkg=" + pkg + ", fullName=" + fullName + '}';
	}
}
