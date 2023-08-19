package jadx.core.xmlgen.entry;

import java.util.List;

public class ProtoValue {
	 @SuppressWarnings("NullAway.Init") private String parent;
	 @SuppressWarnings("NullAway.Init") private String name;
	 @SuppressWarnings("NullAway.Init") private String value;
	private int type;
	 @SuppressWarnings("NullAway.Init") private List<ProtoValue> namedValues;

	public ProtoValue(String value) {
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

	public ProtoValue setParent(String parent) {
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
