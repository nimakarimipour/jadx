package jadx.core.codegen.json.cls;

import org.jetbrains.annotations.Nullable;

public class JsonCodeLine {
	@Nullable
	private String code;

	@Nullable
	private String offset;

	@Nullable
	private Integer sourceLine;

	@Nullable
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Nullable
	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	@Nullable
	public Integer getSourceLine() {
		return sourceLine;
	}

	public void setSourceLine(@Nullable Integer sourceLine) {
		this.sourceLine = sourceLine;
	}
}
