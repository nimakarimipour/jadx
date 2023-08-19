package jadx.core.codegen.json.mapping;

import java.util.List;

public class JsonClsMapping {
	 @SuppressWarnings("NullAway.Init") private String name;
	 @SuppressWarnings("NullAway.Init") private String alias;

	 @SuppressWarnings("NullAway.Init") private String json;
	private boolean inner;
	 @SuppressWarnings("NullAway.Init") private String topClass;

	 @SuppressWarnings("NullAway.Init") private List<JsonFieldMapping> fields;
	 @SuppressWarnings("NullAway.Init") private List<JsonMthMapping> methods;

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

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public boolean isInner() {
		return inner;
	}

	public void setInner(boolean inner) {
		this.inner = inner;
	}

	public String getTopClass() {
		return topClass;
	}

	public void setTopClass(String topClass) {
		this.topClass = topClass;
	}

	public List<JsonFieldMapping> getFields() {
		return fields;
	}

	public void setFields(List<JsonFieldMapping> fields) {
		this.fields = fields;
	}

	public List<JsonMthMapping> getMethods() {
		return methods;
	}

	public void setMethods(List<JsonMthMapping> methods) {
		this.methods = methods;
	}
}
