package jadx.core.xmlgen.entry;

import java.util.List;
import javax.annotation.Nullable;

public class ProtoValue {
	@Nullable private String parent;
	private String name;
	@Nullable private String value;
	private int type;
	private List<ProtoValue> namedValues;

	public ProtoValue(@Nullable String value) {
		this.value = value;
	}

	public ProtoValue() {
	}

	public int getType() {
		return type;
	}

	public ProtoValue setType(int type) {
		this.type = type;
		return this;
	}

	public String getValue() {
		return value;
	}

	public String getParent() {
		return parent;
	}

	public ProtoValue setParent(@Nullable String parent) {
		this.parent = parent;
		return this;
	}

	public ProtoValue setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public ProtoValue setNamedValues(List<ProtoValue> namedValues) {
		this.namedValues = namedValues;
		return this;
	}

	public List<ProtoValue> getNamedValues() {
		return namedValues;
	}
}
