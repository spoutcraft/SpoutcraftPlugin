/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.util.map;

import gnu.trove.iterator.TLongIterator;
import gnu.trove.set.hash.TLongHashSet;

/**
 * A simplistic set that supports 2 ints for one value inside the set.
 * @author Afforess
 *
 */
public class TIntPairHashSet {
	private TLongHashSet set;

	public TIntPairHashSet() {
		set = new TLongHashSet(100);
	}

	public TIntPairHashSet(int capacity) {
		set = new TLongHashSet(capacity);
	}

	public boolean add(int key1, int key2) {
		long key = (((long)key1)<<32) | (((long)key2) & 0xFFFFFFFFL);
		return set.add(key);
	}

	public boolean contains(int key1, int key2) {
		long key = (((long)key1)<<32) | (((long)key2) & 0xFFFFFFFFL);
		return set.contains(key);
	}

	public void clear() {
		set.clear();
	}

	public boolean isEmpty() {
		return set.isEmpty();
	}

	public TLongIterator iterator() {
		return set.iterator();
	}

	public boolean remove(int key1, int key2) {
		long key = (((long)key1)<<32) | (((long)key2) & 0xFFFFFFFFL);
		return set.remove(key);
	}

	public int size() {
		return set.size();
	}

	public static int longToKey1(long composite) {
		return (int) ((composite >> 32) & 4294967295L);
	}

	public static int longToKey2(long composite) {
		return (int) (composite & 4294967295L);
	}
}
