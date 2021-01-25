package jadx.core.dex.attributes.nodes;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import jadx.core.codegen.CodeWriter;
import jadx.core.utils.Utils;

public class JadxError implements Comparable<JadxError> {

	private final String error;
	private final Throwable cause;

	public JadxError(String error, @Nullable Throwable cause) {
		this.error = Objects.requireNonNull(error);
		this.cause = cause;
	}

	public String getError() {
		return error;
	}

	public Throwable getCause() {
		return cause;
	}

	@Override
	public int compareTo(@NotNull JadxError o) {
		return this.error.compareTo(o.getError());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		JadxError other = (JadxError) o;
		return error.equals(other.error);
	}

	@Override
	public int hashCode() {
		return error.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("JadxError: ");
		if (error != null) {
			str.append(error);
			str.append(' ');
		}
		if (cause != null) {
			str.append(cause.getClass());
			str.append(':');
			str.append(cause.getMessage());
			str.append(CodeWriter.NL);
			str.append(Utils.getStackTrace(cause));
		}
		return str.toString();
	}
}
