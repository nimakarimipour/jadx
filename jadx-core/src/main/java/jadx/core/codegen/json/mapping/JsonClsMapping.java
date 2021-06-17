package jadx.core.codegen.json.mapping;

import java.util.List;
import jadx.Initializer;

public class JsonClsMapping {
	private String name;
	private String alias;

	private String json;
	private boolean inner;
	private String topClass;

	private List<JsonFieldMapping> fields;
	private List<JsonMthMapping> methods;

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

	public String getJson() {
		return json;
	}

	@Initializer
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

	@Initializer
	public void setTopClass(String topClass) {
		this.topClass = topClass;
	}

	public List<JsonFieldMapping> getFields() {
		return fields;
	}

	@Initializer
	public void setFields(List<JsonFieldMapping> fields) {
		this.fields = fields;
	}

	public List<JsonMthMapping> getMethods() {
		return methods;
	}

	@Initializer
	public void setMethods(List<JsonMthMapping> methods) {
		this.methods = methods;
	}
}
