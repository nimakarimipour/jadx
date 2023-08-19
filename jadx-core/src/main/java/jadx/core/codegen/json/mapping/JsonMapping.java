package jadx.core.codegen.json.mapping;

import java.util.List;

public class JsonMapping {
	 @SuppressWarnings("NullAway.Init") private List<JsonClsMapping> classes;

	public List<JsonClsMapping> getClasses() {
		return classes;
	}

	public void setClasses(List<JsonClsMapping> classes) {
		this.classes = classes;
	}
}
