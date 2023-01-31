package jadx.core.codegen.json.cls;

import org.jetbrains.annotations.Nullable;
import jadx.core.NullUnmarked;

public class JsonCodeLine {
	@SuppressWarnings("NullAway.Init") private String code;
	@SuppressWarnings("NullAway.Init") private String offset;
	@SuppressWarnings("NullAway.Init") private Integer sourceLine;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public Integer getSourceLine() {
		return sourceLine;
	}

	@NullUnmarked public void setSourceLine(@Nullable Integer sourceLine) {
		this.sourceLine = sourceLine;
	}
}
