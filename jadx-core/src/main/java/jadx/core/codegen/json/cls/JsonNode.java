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

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }
}
