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
package org.getspout.spoutapi.material.item;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.Armor;

public class GenericArmor implements Armor {
	private final int id;
	private final int data;
	private final String name;
	private final boolean subtypes;
	private final short slot;
	private String customName;
	
	public GenericArmor(String name, int id, int data, short slot, boolean subtypes) {
		this.name = name;
		this.id = id;
		this.data = data;
		this.subtypes = subtypes;
		this.slot = slot;
	}
	
	public GenericArmor(String name, int id, int data, short slot) {
		this(name, id, data, slot, false);
	}
	
	public GenericArmor(String name, int id, int data) {
		this(name, id, data, (short) 0);
	}
	
	public GenericArmor(String name, int id) {
		this(name, id, 0);
	}
	
	@Override
	public Armor setMaxDurability(short durability) {
		return this;
	}

	@Override
	public short getMaxDurability() {
		return 0;
	}

	@Override
	public Armor setMaxArmorValue(short armor) {
		return this;
	}

	@Override
	public short getMaxArmorValue() {
		return 0;
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
	public short getSlot() {
		return slot;
	}
	
	@Override
	public Armor setSlot(short slot) {
		return this;
	}
	
	
	@Override
	public boolean hasSubtypes() {
		return subtypes;
	}

	@Override
	public String getNotchianName() {
		return name;
	}

	@Override
	public String getName() {
		if (customName != null) {
			return customName;
		}
		return name;
	}

	@Override
	public void setName(String name) {
		this.customName = name;
		SpoutManager.getMaterialManager().setItemName(this, name);
	}
}