package jadx.api;

import org.jetbrains.annotations.Nullable;
import java.util.List;

public interface JavaNode {

    String getName();

    String getFullName();

    @Nullable
    JavaClass getDeclaringClass();

    @Nullable
    JavaClass getTopParentClass();

    int getDecompiledLine();

    List<JavaNode> getUseIn();
}
