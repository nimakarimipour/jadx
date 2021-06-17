package jadx.core.utils.exceptions;

import jadx.core.dex.nodes.ClassNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.utils.ErrorsCounter;
import org.jetbrains.annotations.Nullable;

public class JadxException extends Exception {

	private static final long serialVersionUID = 3577449089978463557L;

	public JadxException(String message) {
		super(message);
	}

	public JadxException(String message, Throwable cause) {
		super(message, cause);
	}

	public JadxException(ClassNode cls, String msg, @Nullable Throwable th) {
		super(ErrorsCounter.formatMsg(cls, msg), th);
	}

	public JadxException(MethodNode mth, String msg, @Nullable Throwable th) {
		super(ErrorsCounter.formatMsg(mth, msg), th);
	}
}
