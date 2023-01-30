package jadx.core.xmlgen;

import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

public interface IResParser {

	void decode(InputStream inputStream) throws IOException;

	ResourceStorage getResStorage();

	@Nullable String[] getStrings();
}
