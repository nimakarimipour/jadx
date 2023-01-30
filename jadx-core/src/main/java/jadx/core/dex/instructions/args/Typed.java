package jadx.core.dex.instructions.args;

import jadx.core.dex.attributes.AttrNode;
import javax.annotation.Nullable;

public abstract class Typed extends AttrNode {

	@Nullable protected ArgType type;

	@Nullable public ArgType getType() {
		return type;
	}

	public void setType(@Nullable ArgType type) {
		this.type = type;
	}

	public boolean isTypeImmutable() {
		return false;
	}
}
