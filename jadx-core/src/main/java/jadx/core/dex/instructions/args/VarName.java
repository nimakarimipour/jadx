package jadx.core.dex.instructions.args;

import jadx.Initializer;

public class VarName {

    private String name;

    public String getName() {
        return name;
    }

    @Initializer()
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
