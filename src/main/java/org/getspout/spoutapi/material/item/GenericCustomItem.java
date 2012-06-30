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

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.MaterialManager;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.packet.PacketType;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.player.SpoutPlayer;

public class GenericCustomItem extends GenericItem implements CustomItem, SpoutPacket {
	public static MaterialManager mm = SpoutManager.getMaterialManager();
	private String fullName;
	private Plugin plugin;
	private int customId;
	private boolean stackable = true;
	private short counter = Short.MIN_VALUE;
	public String texture;

	public GenericCustomItem(Plugin plugin, String name) {
		super(name, 318, mm.registerCustomItemName(plugin, plugin.getDescription().getName() + "." + name));
		this.fullName = plugin.getDescription().getName() + "." + name;
		this.customId = mm.registerCustomItemName(plugin, fullName);
		this.plugin = plugin;
		this.setName(name);
		MaterialData.addCustomItem(this);
		for (SpoutPlayer player : Spout.getServer().getOnlinePlayers()) {
			player.sendPacket(this);
		}
	}

	public GenericCustomItem(Plugin plugin, String name, String texture) {
		this(plugin, name);
		this.setTexture(texture);
	}
	
	@Override
	public boolean isStackable() {
		return stackable;
	}
	
	@Override
	public CustomItem setStackable(boolean stackable) {
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
	public CustomItem setTexture(String texture) {
		this.setTexture(texture, true);
		return this;
	}

	public CustomItem setTexture(String texture, boolean addToCache) {
		if (addToCache == true) {
			SpoutManager.getFileManager().addToCache(plugin, texture);
		}
		this.texture = texture;
		return this;
	}
	
	public CustomItem setTexture(File texture) {
		SpoutManager.getFileManager().addToCache(plugin, texture);
		this.setTexture(texture.getName(), false);
		return this;
	}
	
	@Override
	public String getTexture() {
		if (texture == null) {
			return "";
		}
		return texture;
	}

	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, BlockFace face) {
		return true;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		customId = input.readInt();
		setName(input.readString());
		plugin = Bukkit.getServer().getPluginManager().getPlugin(input.readString());
		texture = input.readString();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(customId);
		output.writeString(getName());
		output.writeString(getPlugin().getDescription().getName());
		output.writeString(getTexture());
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomItem;
	}

	@Override
	public int getVersion() {
		return 0;
	}

	@Override
	public short getCounter() {
		short res = counter;
		if(counter == Short.MAX_VALUE) counter = Short.MIN_VALUE;
		else counter++;
		return res;
	}
}
