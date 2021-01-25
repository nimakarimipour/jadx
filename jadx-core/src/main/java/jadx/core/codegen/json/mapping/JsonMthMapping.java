package jadx.core.codegen.json.mapping;

import org.jetbrains.annotations.Nullable;

public class JsonMthMapping {
	@Nullable
	private String signature;

	@Nullable
	private String name;

	@Nullable
	private String alias;

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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Nullable
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Nullable
	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}
}
