package jadx.core.dex.nodes;

import java.util.List;
import javax.annotation.Nullable;

public interface IRegion extends IContainer {

	IRegion getParent();

	void setParent(IRegion parent);

	List<IContainer> getSubBlocks();

	boolean replaceSubBlock(IContainer oldBlock, IContainer newBlock);
}
