package jadx.core.codegen.json.cls;

import org.jetbrains.annotations.Nullable;

public class JsonNode {
	@Nullable
	private String name;

	@Nullable
	private String alias;

	@Nullable
	private String declaration;
	private int accessFlags;

	@Nullable
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Nullable
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Nullable
	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(@Nullable String declaration) {
		this.declaration = declaration;
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public void setAccessFlags(int accessFlags) {
		this.accessFlags = accessFlags;
	}
}
