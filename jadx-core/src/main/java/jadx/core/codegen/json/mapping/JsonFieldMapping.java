package jadx.core.codegen.json.mapping;

import org.jetbrains.annotations.Nullable;

public class JsonFieldMapping {
	@Nullable
	private String name;

	@Nullable
	private String alias;

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
}
