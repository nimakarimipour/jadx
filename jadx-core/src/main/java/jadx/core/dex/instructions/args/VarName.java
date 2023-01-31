package jadx.core.dex.instructions.args;

public class VarName {
	@SuppressWarnings("NullAway.Init") private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
