package jadx.core.dex.instructions.args;

import org.jetbrains.annotations.Nullable;

public class VarName {
	@Nullable
	private String name;

	@Nullable
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	@Nullable
	public String toString() {
		return name;
	}
}
