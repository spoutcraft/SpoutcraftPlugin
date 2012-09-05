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
	private int type;
	private short defense;
	private String textureArmor;
	
	public GenericCustomArmor(Plugin plugin, String name, String texture, String textureArmor, int type) {
		super(plugin, name, texture);
		this.type = type;
		this.textureArmor = textureArmor;
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
	public int getType() {
		return type;
	}

	@Override
	public String getArmorTexture() {
		return textureArmor;
	}
	
	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		setMaxDurability(input.readShort());
		this.textureArmor = input.readString();
		this.type = input.readInt();
		this.defense = input.readShort();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeShort(getMaxDurability());
		output.writeString(textureArmor);
		output.writeInt(type);	
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
