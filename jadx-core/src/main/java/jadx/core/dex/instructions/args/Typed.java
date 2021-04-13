package jadx.core.dex.instructions.args;

import jadx.Initializer;

import jadx.core.dex.attributes.AttrNode;

public abstract class Typed extends AttrNode {

	protected ArgType type;

	public ArgType getType() {
		return type;
	}

	@Initializer
	public void setType(ArgType type) {
		this.type = type;
	}

	public boolean isTypeImmutable() {
		return false;
	}
}
