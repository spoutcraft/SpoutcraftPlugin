/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.io.store;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * This implements a SimpleStore that is stored in memory.  It is no persisted between restarts.
 */
public class MemoryStore<T> implements SimpleStore<T> {
	private final Map<String, T> map;
	private final Map<T, String> reverseMap;

	public MemoryStore() {
		map = new HashMap<String, T>();
		reverseMap = new HashMap<T, String>();
	}

	public boolean save() {
		return true;
	}

	public boolean load() {
		return true;
	}

	public Collection<String> getKeys() {
		return map.keySet();
	}

	public Set<Entry<String, T>> getEntrySet() {
		return map.entrySet();
	}

	public int getSize() {
		return map.size();
	}

	public boolean clear() {
		map.clear();
		return true;
	}

	public T get(String key) {
		return map.get(key);
	}

	public String reverseGet(T value) {
		return reverseMap.get(value);
	}

	public T get(String key, T def) {
		T value = get(key);
		if (value == null) {
			return def;
		} else {
			return value;
		}
	}

	public T remove(String key) {
		T value = map.remove(key);
		if (value != null) {
			reverseMap.remove(value);
		}
		return value;
	}

	public T set(String key, T value) {
		T oldValue = map.put(key, value);
		if (oldValue != null) {
			reverseMap.remove(oldValue);
		}
		reverseMap.put(value, key);
		return oldValue;
	}
}
