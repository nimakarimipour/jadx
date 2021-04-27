package jadx.core.codegen.json.mapping;

import java.util.List;
import jadx.Initializer;

public class JsonMapping {

    private List<JsonClsMapping> classes;

    public List<JsonClsMapping> getClasses() {
        return classes;
    }

    @Initializer()
    public void setClasses(List<JsonClsMapping> classes) {
        this.classes = classes;
    }
}
