package jadx.core.codegen.json.cls;

import org.jetbrains.annotations.Nullable;

import jadx.Initializer;

public class JsonCodeLine {
	private String code;
	private String offset;
	private Integer sourceLine;

	public String getCode() {
		return code;
	}

	@Initializer
	public void setCode(String code) {
		this.code = code;
	}

	public String getOffset() {
		return offset;
	}

	@Initializer
	public void setOffset(String offset) {
		this.offset = offset;
	}

	public Integer getSourceLine() {
		return sourceLine;
	}

	@Initializer
	public void setSourceLine( @Nullable Integer sourceLine) {
		this.sourceLine = sourceLine;
	}
}
