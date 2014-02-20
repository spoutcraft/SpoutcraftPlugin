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
package org.getspout.spoutapi.material.item;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.Item;

public class GenericItem implements Item {
	private final int id;
	private final int data;
	private final boolean subtypes;
	private final String name;
	private String customName;

	public GenericItem(String name, int id, int data, boolean subtypes) {
		this.name = name;
		this.id = id;
		this.data = data;
		this.subtypes = subtypes;
	}

	public GenericItem(String name, int id, int data) {
		this(name, id, data, false);
	}

	public GenericItem(String name, int id) {
		this(name, id, 0, false);
	}

	@Override
	public int getRawId() {
		return id;
	}

	@Override
	public int getRawData() {
		return data;
	}

	@Override
	public boolean hasSubtypes() {
		return subtypes;
	}

	@Override
	public String getName() {
		if (customName != null) {
			return customName;
		}
		return name;
	}

	@Override
	public String getNotchianName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.customName = name;
		SpoutManager.getMaterialManager().setItemName(this, name);
	}
}
