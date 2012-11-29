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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import gnu.trove.map.hash.TObjectFloatHashMap;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.MaterialManager;
import org.getspout.spoutapi.inventory.SpoutEnchantment;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.material.CustomArmor;
import org.getspout.spoutapi.material.Material;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.Armor;
import org.getspout.spoutapi.packet.PacketType;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.player.SpoutPlayer;

public class GenericCustomArmor extends GenericArmor implements CustomArmor, SpoutPacket {
	public static MaterialManager mm = SpoutManager.getMaterialManager();
	private String fullName;
	private Plugin plugin;
	private int customId;
	private boolean stackable = false;
	public String itemTexture;
	public String armorTexture;
	private short counter = Short.MIN_VALUE;
	private short maxdurability = 100;
	private short maxarmor = 100;
	private short slot = 0;
	
	public GenericCustomArmor(Plugin plugin, String name) {
		super(name, 318, mm.registerCustomItemName(plugin, plugin.getDescription().getName() + "." + name));
		this.fullName = plugin.getDescription().getName() + "." + name;
		this.customId = mm.registerCustomItemName(plugin, fullName);
		this.plugin = plugin;
		this.setName(name);
		MaterialData.addCustomArmor(this);
		for (SpoutPlayer player : Spout.getServer().getOnlinePlayers()) {
			player.sendPacket(this);
		}
	}
	
	public GenericCustomArmor(Plugin plugin, String name, String itemTexture, String armorTexture, short slot) {
		this(plugin, name);
		this.setItemTexture(itemTexture);
		this.setArmorTexture(armorTexture);
	}
	
	@Override
	public boolean isStackable() {
		return stackable;
	}

	@Override
	public CustomArmor setStackable(boolean stackable) {
		this.stackable = stackable;
		return this;
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		mm.setItemName(this, name);
	}
	
	@Override
	public int getCustomId() {
		return customId;
	}
	
	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public Plugin getPlugin() {
		return plugin;
	}
	
	@Override
	public CustomArmor setItemTexture(String itemTexture) {
		this.setItemTexture(itemTexture, true);
		return this;
	}
	
	public CustomArmor setItemTexture(String itemTexture, boolean addToCache) {
		if (addToCache == true) {
			SpoutManager.getFileManager().addToCache(plugin, itemTexture);
		}
		this.itemTexture = itemTexture;
		return this;
	}
	
	public CustomArmor setItemTexture(File itemTexture) {
		SpoutManager.getFileManager().addToCache(plugin, itemTexture);
		this.setItemTexture(itemTexture.getName(), false);
		return this;
	}
	
	public CustomArmor setItemTexture(InputStream input, String cacheName) {
		SpoutManager.getFileManager().addToCache(getPlugin(), input, cacheName);
		this.setItemTexture(cacheName, false);
		return this;
	}
	
	@Override
	public CustomArmor setArmorTexture(String armorTexture) {
		this.setItemTexture(armorTexture, true);
		return this;
	}
	
	public CustomArmor setArmorTexture(String armorTexture, boolean addToCache) {
		if (addToCache == true) {
			SpoutManager.getFileManager().addToCache(plugin, armorTexture);
		}
		this.armorTexture = armorTexture;
		return this;
	}
	
	public CustomArmor setArmorTexture(File armorTexture) {
		SpoutManager.getFileManager().addToCache(plugin, armorTexture);
		this.setArmorTexture(armorTexture.getName(), false);
		return this;
	}
	
	public CustomArmor setArmorTexture(InputStream input, String cacheName) {
		SpoutManager.getFileManager().addToCache(getPlugin(), input, cacheName);
		this.setItemTexture(cacheName, false);
		return this;
	}
	
	@Override
	public String getItemTexture() {
		if (itemTexture == null) {
			return "";
		}
		return itemTexture;
	}
	
	@Override
	public String getArmorTexture() {
		if (armorTexture == null) {
			return "";
		}
		return armorTexture;
	}
	
	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, BlockFace face) {
		return true;
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
	public Armor setMaxArmorValue(short armor) {
		maxarmor = armor;
		return this;
	}

	@Override
	public short getMaxArmorValue() {
		return maxarmor;
	}
	
	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomArmor;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		customId = input.readInt();
		setName(input.readString());
		plugin = Bukkit.getServer().getPluginManager().getPlugin(input.readString());
		itemTexture = input.readString();
		armorTexture = input.readString();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(customId);
		output.writeString(getName());
		output.writeString(getPlugin().getDescription().getName());
		output.writeString(getItemTexture());
		output.writeString(getArmorTexture());
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public int getVersion() {
		return 0;
	}
	
	@Override
	public short getCounter() {
		short res = counter;
		if (counter == Short.MAX_VALUE) {
			counter = Short.MIN_VALUE;
		} else {
			counter++;
		}
		return res;
	}

	@Override
	public CustomArmor setSlot(short armorSlot) {
		slot = armorSlot;
		return this;
	}

	@Override
	public short getSlot() {
		return slot;
	}
}