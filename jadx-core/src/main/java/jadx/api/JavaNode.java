package jadx.api;

import java.util.List;
import org.jetbrains.annotations.Nullable;

public interface JavaNode {

    String getName();

    String getFullName();

    @Nullable()
    JavaClass getDeclaringClass();

    @Nullable()
    JavaClass getTopParentClass();

    int getDecompiledLine();

    List<JavaNode> getUseIn();
}
