package jadx.core.dex.nodes;

import org.jetbrains.annotations.Nullable;
import jadx.core.dex.attributes.IAttributeNode;
import jadx.core.dex.info.AccessInfo;

public interface ICodeNode extends IDexNode, IAttributeNode, IUsageInfoNode {

    AccessInfo getAccessFlags();

    void setAccessFlags(AccessInfo newAccessFlags);
}
