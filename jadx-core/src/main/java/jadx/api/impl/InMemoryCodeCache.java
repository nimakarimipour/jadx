package jadx.api.impl;

import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jadx.api.ICodeCache;
import jadx.api.ICodeInfo;

public class InMemoryCodeCache implements ICodeCache {

	private final Map<String, ICodeInfo> storage = new ConcurrentHashMap<>();

	@Override
	public void add(String clsFullName, ICodeInfo codeInfo) {
		storage.put(clsFullName, codeInfo);
	}

	@Override
	public void remove(String clsFullName) {
		storage.remove(clsFullName);
	}

	@Override@Nullable
	public  ICodeInfo get(String clsFullName) {
		return storage.get(clsFullName);
	}

	@Override
	public String toString() {
		return "InMemoryCodeCache";
	}
}
