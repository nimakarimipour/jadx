package jadx.core.dex.instructions.args;

import org.jetbrains.annotations.Nullable;

import jadx.core.dex.attributes.AttrNode;

public abstract class Typed extends AttrNode {

	@Nullable
	protected ArgType type;

	@Nullable
	public ArgType getType() {
		return type;
	}

	public void setType(@Nullable ArgType type) {
		this.type = type;
	}

	public boolean isTypeImmutable() {
		return false;
	}
}
