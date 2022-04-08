package jadx.core.codegen.json.cls;

import org.jetbrains.annotations.Nullable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class JsonClass extends JsonNode {

    @SerializedName("package")
    @Nullable
    private String pkg;

    // class, interface, enum
    @Nullable
    private String type;

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
