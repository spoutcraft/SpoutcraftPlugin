/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.material.item;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.MaterialManager;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.packet.PacketType;
import org.getspout.spoutapi.packet.PacketUtil;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.player.SpoutPlayer;

public class GenericCustomItem extends GenericItem implements CustomItem, SpoutPacket {
	public static MaterialManager mm = SpoutManager.getMaterialManager();
	private String fullName;
	private Plugin plugin;
	private int customId;
	public String texture;

	public GenericCustomItem(Plugin plugin, String name) {
		super(name, 318, mm.registerCustomItemName(plugin, plugin.getDescription().getName() + "." + name));
		this.fullName = plugin.getDescription().getName() + "." + name;
		this.customId = mm.registerCustomItemName(plugin, fullName);
		this.plugin = plugin;
		this.setName(name);
		MaterialData.addCustomItem(this);
		for(SpoutPlayer player : Spout.getServer().getOnlinePlayers()) {
			player.sendPacket(this);
		}
	}

	public GenericCustomItem(Plugin plugin, String name, String texture) {
		this(plugin,name);
		SpoutManager.getFileManager().addToCache(plugin, texture);
		this.setTexture(texture);
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
		this.texture = texture;
		return this;
	}

	@Override
	public String getTexture() {
		return texture;
	}

	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block,	BlockFace face) {
		return true;
	}

	@Override
	public int getNumBytes() {
		return 4 + PacketUtil.getNumBytes(getName()) + PacketUtil.getNumBytes(getPlugin().getDescription().getName()) + PacketUtil.getNumBytes(getTexture());
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		customId = input.readInt();
		setName(PacketUtil.readString(input));
		plugin = Bukkit.getServer().getPluginManager().getPlugin(PacketUtil.readString(input));
		texture = PacketUtil.readString(input);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(customId);
		PacketUtil.writeString(output, getName());
		PacketUtil.writeString(output, getPlugin().getDescription().getName());
		PacketUtil.writeString(output, getTexture());
	}

	@Override
	public void run(int playerId) {}

	@Override
	public void failure(int playerId) {}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomItem;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
