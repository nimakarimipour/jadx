package jadx.core.codegen.json.mapping;

import javax.annotation.Nullable;
import java.util.List;

public class JsonMapping {

    @Nullable
    private List<JsonClsMapping> classes;

    @Nullable
    public List<JsonClsMapping> getClasses() {
        return classes;
    }

    public void setClasses(List<JsonClsMapping> classes) {
        this.classes = classes;
    }
}
