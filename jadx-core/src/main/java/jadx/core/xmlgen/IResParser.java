package jadx.core.xmlgen;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;

public interface IResParser {

    void decode(InputStream inputStream) throws IOException;

    ResourceStorage getResStorage();

    @Nullable
    String[] getStrings();
}
