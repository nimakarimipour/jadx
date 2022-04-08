package jadx.core.codegen.json.cls;

import org.jetbrains.annotations.Nullable;
import java.util.List;

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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public List<JsonCodeLine> getLines() {
        return lines;
    }

    public void setLines(List<JsonCodeLine> lines) {
        this.lines = lines;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
