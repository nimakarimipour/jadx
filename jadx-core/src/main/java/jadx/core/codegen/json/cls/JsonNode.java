package jadx.core.codegen.json.cls;

import jadx.Initializer;

public class JsonNode {
	private String name;
	private String alias;
	private String declaration;
	private int accessFlags;

	public String getName() {
		return name;
	}

	@Initializer
	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	@Initializer
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDeclaration() {
		return declaration;
	}

	@Initializer
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public void setAccessFlags(int accessFlags) {
		this.accessFlags = accessFlags;
	}
}
