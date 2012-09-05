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

import java.io.IOException;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.inventory.SpoutEnchantment;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.material.Armor;
import org.getspout.spoutapi.material.Material;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.packet.PacketType;

public class GenericCustomArmor  extends GenericCustomItem implements Armor {
	private short maxdurability = 100;
	private byte type;
	private short defense;
	
	public GenericCustomArmor(Plugin plugin, String name, String texture, byte type) {
		super(plugin, name, texture);
		this.type = type;
	}

	@Override
	public Armor setMaxDurability(short durability) {
		maxdurability = durability;
		return this;
	}
	
	@Override
	public short getMaxDurability() {
		return maxdurability;
	}

	@Override
	public short getDefense() {
		return defense;
	}
	
	@Override
	public void setDefense(short defense) {
		this.defense = defense;
	}
	
	@Override
	public byte getType() {
		return type;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		setMaxDurability(input.readShort());
		this.type = (byte)input.readShort();	// FIX: There isn't a input.readByte()...
		this.defense = input.readShort();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeShort(getMaxDurability());
		output.writeShort(type);				// FIX: There isn't a input.writeByte()...
		output.writeShort(getDefense());
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomArmor;
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 0;
	}
	
	public static short getDurability(ItemStack is) {
		Material m = MaterialData.getMaterial(is.getTypeId(), is.getDurability());
		if(!(m instanceof Armor)) throw new IllegalArgumentException("Itemstack must be an Armor!");
		return (short) is.getEnchantmentLevel(SpoutEnchantment.DURABILITY);
	}
	
	public static void setDurability(ItemStack is, short durability) {
		Material m = MaterialData.getMaterial(is.getTypeId(), is.getDurability());
		if(!(m instanceof Armor)) throw new IllegalArgumentException("Itemstack must be an Armor!");
		is.removeEnchantment(SpoutEnchantment.DURABILITY);
		is.addUnsafeEnchantment(SpoutEnchantment.DURABILITY, durability);
	}

}
