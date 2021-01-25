package jadx.core.codegen.json.cls;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.google.gson.annotations.SerializedName;

public class JsonClass extends JsonNode {
	@SerializedName("package")
	@Nullable
	private String pkg;

	@Nullable
	private String type;
	// class, interface, enum
	@SerializedName("extends")
	@Nullable
	private String superClass;

	@SerializedName("implements")
	@Nullable
	private List<String> interfaces;

	@Nullable
	private String dex;

	@Nullable
	private List<JsonField> fields;

	@Nullable
	private List<JsonMethod> methods;

	@Nullable
	private List<JsonClass> innerClasses;

	@Nullable
	private List<String> imports;

	@Nullable
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Nullable
	public String getSuperClass() {
		return superClass;
	}

	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	@Nullable
	public List<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<String> interfaces) {
		this.interfaces = interfaces;
	}

	@Nullable
	public List<JsonField> getFields() {
		return fields;
	}

	public void setFields(List<JsonField> fields) {
		this.fields = fields;
	}

	@Nullable
	public List<JsonMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<JsonMethod> methods) {
		this.methods = methods;
	}

	@Nullable
	public List<JsonClass> getInnerClasses() {
		return innerClasses;
	}

	public void setInnerClasses(List<JsonClass> innerClasses) {
		this.innerClasses = innerClasses;
	}

	@Nullable
	public String getPkg() {
		return pkg;
	}

	public void setPkg(@Nullable String pkg) {
		this.pkg = pkg;
	}

	@Nullable
	public String getDex() {
		return dex;
	}

	public void setDex(String dex) {
		this.dex = dex;
	}

	@Nullable
	public List<String> getImports() {
		return imports;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}
}
