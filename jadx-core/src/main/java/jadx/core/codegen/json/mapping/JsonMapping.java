package jadx.core.codegen.json.mapping;

import java.util.List;

import org.jetbrains.annotations.Nullable;

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
