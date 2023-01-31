package jadx.core.codegen.json.mapping;

public class JsonFieldMapping {
	@SuppressWarnings("NullAway.Init") private String name;
	@SuppressWarnings("NullAway.Init") private String alias;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
}
