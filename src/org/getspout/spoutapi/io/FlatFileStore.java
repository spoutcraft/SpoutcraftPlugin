package org.getspout.spoutapi.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FlatFileStore {
	
	private final File file;
	private final Map<String, Integer> map;
	private final Map<Integer, String> reverseMap;
	private boolean dirty = false;
	
	public FlatFileStore(File file) {
		map = new HashMap<String, Integer>();
		reverseMap = new HashMap<Integer, String>();
		this.file = file;
		if (file != null) {
			if (!file.exists()) {
				if (!FileUtil.createFile(file)) {
					return;
				}
			}
		}
	}
	
	public boolean save() {
		if (dirty) {
			Collection<String> strings = getStrings();
			boolean saved = FileUtil.stringToFile(strings, file);
			if (saved) {
				dirty = false;
			}
			return saved;
		} else {
			return true;
		}
	}
	
	public boolean load() {
		Collection<String> strings = FileUtil.fileToString(file);
		if (strings == null) {
			return false;
		}
		boolean loaded = processStrings(strings);
		if (loaded) {
			dirty = false;
		}
		return loaded;
	}
	
	public Collection<String> getKeys() {
		return map.keySet();
	}
	
	public Integer get(String key) {
		return map.get(key);
	}
	
	public String reverseGet(Integer value) {
		return reverseMap.get(value);
	}
	
	public Integer get(String key, Integer def) {
		Integer value = get(key);
		if (value == null) {
			return def;
		} else {
			return value;
		}
	}
	
	public Integer remove(String key) {
		Integer value = map.remove(key);
		if (value != null) {
			reverseMap.remove(value);
			dirty = true;
		}
		return value;
	}
	
	public Integer set(String key, Integer value) {
		dirty = true;
		Integer oldValue = map.put(key, value);
		if (oldValue != null) {
			reverseMap.remove(oldValue);
		}
		reverseMap.put(value, key);
		return oldValue;
	}
	
	private Collection<String> getStrings() {
		Iterator<Entry<String, Integer>> itr = map.entrySet().iterator();
		ArrayList<String> strings = new ArrayList<String>(map.size());
		while (itr.hasNext()) {
			Entry<String, Integer> entry = itr.next();
			String encodedKey = encode(entry.getKey());
			Integer value = entry.getValue();
			strings.add(value + ":" + encodedKey);
		}
		return strings;
	}
	
	private boolean processStrings(Collection<String> strings) {
		map.clear();
		for (String string : strings) {
			String[] split = string.trim().split(":");
			if (split.length != 2) {
				return false;
			}
			Integer value;
			try {
				value = Integer.parseInt(split[0]);
			} catch (NumberFormatException nfe) {
				return false;
			}
			String key = decode(split[1]);
			set(key, value);
		}
		return true;
	}
	
	private static String encode(String key) {
		String encoded = key;
		encoded = encoded.replace("\\", "\\\\");
		encoded = encoded.replace("\n", "\\n");
		encoded = encoded.replace(":", "\\:");
		return encoded;
	}
	
	private static String decode(String encoded) {
		String key = encoded;
		key = key.replace("\\:", ":");
		key = key.replace("\\n", "\n");
		key = key.replace("\\\\", "\\");
		return encoded;
	}

}
