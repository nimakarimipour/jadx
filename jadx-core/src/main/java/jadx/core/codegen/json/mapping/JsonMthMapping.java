package jadx.core.codegen.json.mapping;

import jadx.Initializer;

public class JsonMthMapping {

    private String signature;

    private String name;

    private String alias;

    private String offset;

    public String getSignature() {
        return signature;
    }

    @Initializer()
    public void setSignature(String signature) {
        this.signature = signature;
    }

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

    public String getOffset() {
        return offset;
    }

    @Initializer()
    public void setOffset(String offset) {
        this.offset = offset;
    }
}
