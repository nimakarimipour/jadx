package jadx.core.dex.visitors;

import org.jetbrains.annotations.Nullable;

import jadx.core.dex.instructions.BaseInvokeNode;
import jadx.core.dex.nodes.BlockNode;
import jadx.core.dex.nodes.IMethodDetails;
import jadx.core.dex.nodes.InsnNode;
import jadx.core.dex.nodes.MethodNode;
import jadx.core.dex.nodes.RootNode;
import jadx.core.dex.nodes.utils.MethodUtils;
import jadx.core.dex.visitors.shrink.CodeShrinkVisitor;
import jadx.core.dex.visitors.typeinference.TypeInferenceVisitor;
import jadx.core.utils.exceptions.JadxException;

@JadxVisitor(
		name = "Attach Method Details",
		desc = "Attach method details for invoke instructions",
		runBefore = {
				CodeShrinkVisitor.class,
				TypeInferenceVisitor.class,
				MethodInvokeVisitor.class,
				OverrideMethodVisitor.class
		}
)
public class AttachMethodDetails extends AbstractVisitor {

	@Nullable
	private MethodUtils methodUtils;

	@Override
	public void init(RootNode root) {
		methodUtils = root.getMethodUtils();
	}

	@Override
	public void visit(MethodNode mth) throws JadxException {
		if (mth.isNoCode()) {
			return;
		}
		for (BlockNode blockNode : mth.getBasicBlocks()) {
			for (InsnNode insn : blockNode.getInstructions()) {
				if (insn instanceof BaseInvokeNode) {
					attachMethodDetails((BaseInvokeNode) insn);
				}
			}
		}
	}

	private void attachMethodDetails(BaseInvokeNode insn) {
		IMethodDetails methodDetails = methodUtils.getMethodDetails(insn.getCallMth());
		if (methodDetails != null) {
			insn.addAttr(methodDetails);
		}
	}

}
