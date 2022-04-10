package jadx.core.dex.instructions.args;

import org.jetbrains.annotations.Nullable;

public class VarName {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
