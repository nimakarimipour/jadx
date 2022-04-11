package jadx.core.dex.visitors.typeinference;

import org.jetbrains.annotations.Nullable;
import java.util.Objects;
import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.instructions.args.RegisterArg;

public final class TypeBoundConst implements ITypeBound {

    private final BoundEnum bound;

    @Nullable
    private final ArgType type;

    @Nullable
    private final RegisterArg arg;

    public TypeBoundConst(BoundEnum bound, @Nullable ArgType type) {
        this(bound, type, null);
    }

    public TypeBoundConst(BoundEnum bound, @Nullable ArgType type, @Nullable RegisterArg arg) {
        this.bound = bound;
        this.type = type;
        this.arg = arg;
    }

    @Override
    public BoundEnum getBound() {
        return bound;
    }

    @Override
    @Nullable
    public ArgType getType() {
        return type;
    }

    @Override
    @Nullable
    public RegisterArg getArg() {
        return arg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypeBoundConst that = (TypeBoundConst) o;
        return bound == that.bound && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bound, type);
    }

    @Override
    public String toString() {
        return "{" + bound + ": " + type + '}';
    }
}
