package jadx.core.xmlgen.entry;

import jadx.Initializer;

import java.util.List;

public final class ResourceEntry {

	private final int id;
	private final String pkgName;
	private final String typeName;
	private final String keyName;
	private final String config;

	private int parentRef;
	private RawValue simpleValue;
	private List<RawNamedValue> namedValues;

	public ResourceEntry(int id, String pkgName, String typeName, String keyName, String config) {
		this.id = id;
		this.pkgName = pkgName;
		this.typeName = typeName;
		this.keyName = keyName;
		this.config = config;
	}

	public ResourceEntry copy(String newKeyName) {
		ResourceEntry copy = new ResourceEntry(id, pkgName, typeName, newKeyName, config);
		copy.parentRef = this.parentRef;
		copy.simpleValue = this.simpleValue;
		copy.namedValues = this.namedValues;
		return copy;
	}

	public ResourceEntry copyWithId() {
		return copy(keyName + "_RES_" + id);
	}

	public int getId() {
		return id;
	}

	public String getPkgName() {
		return pkgName;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getKeyName() {
		return keyName;
	}

	public String getConfig() {
		return config;
	}

	public void setParentRef(int parentRef) {
		this.parentRef = parentRef;
	}

	public int getParentRef() {
		return parentRef;
	}

	public RawValue getSimpleValue() {
		return simpleValue;
	}

	@Initializer
	public void setSimpleValue(RawValue simpleValue) {
		this.simpleValue = simpleValue;
	}

	@Initializer
	public void setNamedValues(List<RawNamedValue> namedValues) {
		this.namedValues = namedValues;
	}

	public List<RawNamedValue> getNamedValues() {
		return namedValues;
	}

	@Override
	public String toString() {
		return "  0x" + Integer.toHexString(id) + " (" + id + ')' + config + " = " + typeName + '.' + keyName;
	}
}
