package jadx.core.codegen.json.cls;

import jadx.Initializer;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class JsonClass extends JsonNode {
	@SerializedName("package")
	private String pkg;
	private String type; // class, interface, enum
	@SerializedName("extends")
	private String superClass;
	@SerializedName("implements")
	private List<String> interfaces;
	private String dex;

	private List<JsonField> fields;
	private List<JsonMethod> methods;
	private List<JsonClass> innerClasses;

	private List<String> imports;

	public String getType() {
		return type;
	}

	@Initializer
	public void setType(String type) {
		this.type = type;
	}

	public String getSuperClass() {
		return superClass;
	}

	@Initializer
	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	public List<String> getInterfaces() {
		return interfaces;
	}

	@Initializer
	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}

	public List<JsonField> getFields() {
		return fields;
	}

	@Initializer
	public void setFields(List<JsonField> fields) {
		this.fields = fields;
	}

	public List<JsonMethod> getMethods() {
		return methods;
	}

	@Initializer
	public void setMethods(List<JsonMethod> methods) {
		this.methods = methods;
	}

	public List<JsonClass> getInnerClasses() {
		return innerClasses;
	}

	@Initializer
	public void setInnerClasses(List<JsonClass> innerClasses) {
		this.innerClasses = innerClasses;
	}

	public String getPkg() {
		return pkg;
	}

	@Initializer
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getDex() {
		return dex;
	}

	@Initializer
	public void setDex(String dex) {
		this.dex = dex;
	}

	public List<String> getImports() {
		return imports;
	}

	@Initializer
	public void setImports(List<String> imports) {
		this.imports = imports;
	}
}
