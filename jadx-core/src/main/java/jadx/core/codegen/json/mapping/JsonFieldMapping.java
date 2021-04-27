package jadx.core.codegen.json.mapping;

import jadx.Initializer;

public class JsonFieldMapping {

    private String name;

    private String alias;

    public String getName() {
        return name;
    }

    @Initializer()
    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    @Initializer()
    public void setAlias(String alias) {
        this.alias = alias;
    }
}
