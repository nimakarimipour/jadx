package jadx.core.codegen.json.cls;

import jadx.Initializer;
import org.jetbrains.annotations.Nullable;

public class JsonCodeLine {

    private String code;

    private String offset;

    @Nullable
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

    @Nullable
    public Integer getSourceLine() {
        return sourceLine;
    }

    @Initializer
    public void setSourceLine(@Nullable Integer sourceLine) {
        this.sourceLine = sourceLine;
    }
}
