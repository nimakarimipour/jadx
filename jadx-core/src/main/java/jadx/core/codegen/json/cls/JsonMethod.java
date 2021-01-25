package jadx.core.codegen.json.cls;

import java.util.List;

import org.jetbrains.annotations.Nullable;

public class JsonMethod extends JsonNode {
	@Nullable
	private String signature;

	@Nullable
	private String returnType;

	@Nullable
	private List<String> arguments;

	@Nullable
	private List<JsonCodeLine> lines;

	@Nullable
	private String offset;

	@Nullable
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Nullable
	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	@Nullable
	public List<String> getArguments() {
		return arguments;
	}

	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	@Nullable
	public List<JsonCodeLine> getLines() {
		return lines;
	}

	public void setLines(List<JsonCodeLine> lines) {
		this.lines = lines;
	}

	@Nullable
	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}
}
