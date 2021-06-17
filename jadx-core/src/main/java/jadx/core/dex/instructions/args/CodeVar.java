package jadx.core.dex.instructions.args;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jadx.Initializer;

public class CodeVar {
	private String name;
	private ArgType type; // before type inference can be null and set only for immutable types
	private List<SSAVar> ssaVars = Collections.emptyList();

	private boolean isFinal;
	private boolean isThis;
	private boolean isDeclared;

	public static CodeVar fromMthArg(RegisterArg mthArg, boolean linkRegister) {
		CodeVar var = new CodeVar();
		var.setType(mthArg.getInitType());
		var.setName(mthArg.getName());
		var.setThis(mthArg.isThis());
		var.setDeclared(true);
		var.setThis(mthArg.isThis());
		if (linkRegister) {
			var.setSsaVars(Collections.singletonList(new SSAVar(mthArg.getRegNum(), 0, mthArg)));
		}
		return var;
	}

	public String getName() {
		return name;
	}

	@Initializer
	public void setName(String name) {
		this.name = name;
	}

	public ArgType getType() {
		return type;
	}

	@Initializer
	public void setType(ArgType type) {
		this.type = type;
	}

	public List<SSAVar> getSsaVars() {
		return ssaVars;
	}

	public void addSsaVar(SSAVar ssaVar) {
		if (ssaVars.isEmpty()) {
			ssaVars = new ArrayList<>(3);
		}
		if (!ssaVars.contains(ssaVar)) {
			ssaVars.add(ssaVar);
		}
	}

	public void setSsaVars(List<SSAVar> ssaVars) {
		this.ssaVars = ssaVars;
	}

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean aFinal) {
		isFinal = aFinal;
	}

	public boolean isThis() {
		return isThis;
	}

	public void setThis(boolean aThis) {
		isThis = aThis;
	}

	public boolean isDeclared() {
		return isDeclared;
	}

	public void setDeclared(boolean declared) {
		isDeclared = declared;
	}

	/**
	 * Merge flags with OR operator
	 */
	public void mergeFlagsFrom(CodeVar other) {
		if (other.isDeclared()) {
			setDeclared(true);
		}
		if (other.isThis()) {
			setThis(true);
		}
		if (other.isFinal()) {
			setFinal(true);
		}
	}

	@Override
	public String toString() {
		return (isFinal ? "final " : "") + type + ' ' + name;
	}
}
