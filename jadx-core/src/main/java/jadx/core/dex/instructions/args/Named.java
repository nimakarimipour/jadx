package jadx.core.dex.instructions.args;

import org.jetbrains.annotations.Nullable;

public interface Named {

    @Nullable
    String getName();

    void setName(String name);
}
