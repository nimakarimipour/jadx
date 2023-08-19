package jadx.core.codegen.json.cls;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class JsonClass extends JsonNode {
	 @SuppressWarnings("NullAway.Init") @SerializedName("package")
	private String pkg;
	 @SuppressWarnings("NullAway.Init") private String type; // class, interface, enum
	 @SuppressWarnings("NullAway.Init") @SerializedName("extends")
	private String superClass;
	 @SuppressWarnings("NullAway.Init") @SerializedName("implements")
	private List<String> interfaces;
	 @SuppressWarnings("NullAway.Init") private String dex;

	 @SuppressWarnings("NullAway.Init") private List<JsonField> fields;
	 @SuppressWarnings("NullAway.Init") private List<JsonMethod> methods;
	 @SuppressWarnings("NullAway.Init") private List<JsonClass> innerClasses;

	 @SuppressWarnings("NullAway.Init") private List<String> imports;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSuperClass() {
		return superClass;
	}

	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}

	public List<JsonField> getFields() {
		return fields;
	}

	public void setFields(List<JsonField> fields) {
		this.fields = fields;
	}

	public List<JsonMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<JsonMethod> methods) {
		this.methods = methods;
	}

	public List<JsonClass> getInnerClasses() {
		return innerClasses;
	}

	public void setInnerClasses(List<JsonClass> innerClasses) {
		this.innerClasses = innerClasses;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getDex() {
		return dex;
	}

	public void setDex(String dex) {
		this.dex = dex;
	}

	public List<String> getImports() {
		return imports;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}
}
