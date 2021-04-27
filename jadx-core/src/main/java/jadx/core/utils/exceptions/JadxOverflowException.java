package jadx.core.utils.exceptions;

import org.jetbrains.annotations.Nullable;

public class JadxOverflowException extends JadxRuntimeException {

    private static final long serialVersionUID = 2568659798680154204L;

    public JadxOverflowException(@Nullable() String message) {
        super(message);
    }
}
