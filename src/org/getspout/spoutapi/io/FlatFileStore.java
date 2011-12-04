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
	
	public FlatFileStore(File file) {
		map  = new HashMap<String, Integer>();
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
		Collection<String> strings = getStrings();
		return FileUtil.stringToFile(strings, file);
	}
	
	public boolean load() {
		Collection<String> strings = FileUtil.fileToString(file);
		if (strings == null) {
			return false;
		}
		return processStrings(strings);
	}
	
	public Collection<String> getKeys() {
		return map.keySet();
	}
	
	public Integer get(String key) {
		return map.get(key);
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
		return map.remove(key);
	}
	
	public Integer set(String key, Integer value) {
		return map.put(key, value);
	}
	
	private Collection<String> getStrings() {
		Iterator<Entry<String, Integer>> itr = map.entrySet().iterator();
		ArrayList<String> strings = new ArrayList<String>(map.size());
		while (itr.hasNext()) {
			Entry<String, Integer> entry = itr.next();
			String encodedKey = encode(entry.getKey());
			Integer value = entry.getValue();
			strings.add(value + "=" + encodedKey);
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
