package jadx.core.dex.visitors.typeinference;

import java.util.LinkedHashSet;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import jadx.core.dex.instructions.args.ArgType;

public class TypeInfo {
	@Nullable
	private ArgType type = ArgType.UNKNOWN;

	private final Set<ITypeBound> bounds = new LinkedHashSet<>();

	@NotNull
	@Nullable
	public ArgType getType() {
		return type;
	}

	public void setType(@Nullable ArgType type) {
		this.type = type;
	}

	public Set<ITypeBound> getBounds() {
		return bounds;
	}

	@Override
	public String toString() {
		return "TypeInfo{type=" + type + ", bounds=" + bounds + '}';
	}
}
