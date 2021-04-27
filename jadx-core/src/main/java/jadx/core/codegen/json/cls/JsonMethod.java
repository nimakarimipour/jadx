package jadx.core.codegen.json.cls;

import java.util.List;
import jadx.Initializer;

public class JsonMethod extends JsonNode {

    private String signature;

    private String returnType;

    private List<String> arguments;

    private List<JsonCodeLine> lines;

    private String offset;

    public String getSignature() {
        return signature;
    }

    @Initializer()
    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getReturnType() {
        return returnType;
    }

    @Initializer()
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<String> getArguments() {
        return arguments;
    }

    @Initializer()
    public void setArguments(List<String> arguments) {
        this.arguments = arguments;
    }

    public List<JsonCodeLine> getLines() {
        return lines;
    }

    @Initializer()
    public void setLines(List<JsonCodeLine> lines) {
        this.lines = lines;
    }

    public String getOffset() {
        return offset;
    }

    @Initializer()
    public void setOffset(String offset) {
        this.offset = offset;
    }
}
