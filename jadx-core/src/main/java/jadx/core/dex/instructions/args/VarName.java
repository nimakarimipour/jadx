package jadx.core.dex.instructions.args;
import javax.annotation.Nullable;

public class VarName {
	 @Nullable private String name;

	@Nullable public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Nullable @Override
	public String toString() {
		return name;
	}
}
