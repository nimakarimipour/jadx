package jadx.core.dex.instructions.args;
import javax.annotation.Nullable;

public interface Named {

	@Nullable String getName();

	void setName(String name);
}
