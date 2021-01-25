package jadx.core.codegen.json.mapping;

import java.util.List;

import org.jetbrains.annotations.Nullable;

public class JsonClsMapping {
	@Nullable
	private String name;

	@Nullable
	private String alias;

	@Nullable
	private String json;
	private boolean inner;

	@Nullable
	private String topClass;

	@Nullable
	private List<JsonFieldMapping> fields;

	@Nullable
	private List<JsonMthMapping> methods;

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

	@Nullable
	public String getTopClass() {
		return topClass;
	}

	public void setTopClass(String topClass) {
		this.topClass = topClass;
	}

	@Nullable
	public List<JsonFieldMapping> getFields() {
		return fields;
	}

	public void setFields(List<JsonFieldMapping> fields) {
		this.fields = fields;
	}

	@Nullable
	public List<JsonMthMapping> getMethods() {
		return methods;
	}

	public void setMethods(List<JsonMthMapping> methods) {
		this.methods = methods;
	}
}
