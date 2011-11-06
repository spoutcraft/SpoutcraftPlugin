package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.CustomItem;

public class PacketCustomMaterial implements SpoutPacket {
	private Plugin plugin;
	private String name;
	private int id;
	private int data;
	private byte type;
	private boolean isItem = false;
	private boolean isOpaque = false;

	public PacketCustomMaterial() {

	}
	
	public PacketCustomMaterial(CustomBlock block) {
		this.plugin = block.getPlugin();
		this.name = block.getName();
		this.id = block.getCustomId();
		this.data = block.getCustomMetaData();
		this.isItem = false;
		this.isOpaque = block.isOpaque();
	}
	
	public PacketCustomMaterial(CustomItem item) {
		this.plugin = item.getPlugin();
		this.name = item.getName();
		this.id = item.getCustomId();
		this.data = 0;
		this.isItem = true;
		this.isOpaque = false;
	}

	public int getNumBytes() {
		return PacketUtil.getNumBytes(plugin.getDescription().getName()) + PacketUtil.getNumBytes(name);
	}

	public void readData(DataInputStream input) throws IOException {
		plugin = Bukkit.getServer().getPluginManager().getPlugin((PacketUtil.readString(input)));
		name = PacketUtil.readString(input);
		id = input.readInt();
		data = input.readInt();
		type = input.readByte();
		isItem = type == 2 ? true : false;
		isOpaque = type == 1 ? true : false;
	}

	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, plugin.getDescription().getName());
		PacketUtil.writeString(output, name);
		output.writeInt(id);
		output.writeInt(data);
		output.writeByte(isItem ? 2 : isOpaque ? 1 : 0);
	}

	public void run(int playerId) {
	}

	public void failure(int playerId) {
	}

	public PacketType getPacketType() {
		return PacketType.PacketCustomMaterial;
	}

	public int getVersion() {
		return 0;
	}

}
