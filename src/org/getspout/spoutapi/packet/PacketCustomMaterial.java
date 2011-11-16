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
	private byte type;
	private boolean isItem = false;
	private boolean isOpaque = false;
	private float hardness;
	private float friction;
	private int lightLevel;

	public PacketCustomMaterial() {

	}
	
	public PacketCustomMaterial(CustomBlock block) {
		this.plugin = block.getPlugin();
		this.name = block.getName();
		this.id = block.getCustomId();
		this.isItem = false;
		this.isOpaque = block.isOpaque();
		this.hardness = block.getHardness();
		this.friction = block.getFriction();
		this.lightLevel = block.getLightLevel();
	}
	
	public PacketCustomMaterial(CustomItem item) {
		this.plugin = item.getPlugin();
		this.name = item.getName();
		this.id = item.getCustomId();
		this.isItem = true;
		this.isOpaque = false;
		hardness = 0F;
		friction = 0F;
		lightLevel = 0;
	}

	public int getNumBytes() {
		return PacketUtil.getNumBytes(plugin.getDescription().getName()) + PacketUtil.getNumBytes(name) + 4 + 1 + 1 + 1 + 4 + 4 + 4;
	}

	public void readData(DataInputStream input) throws IOException {
		plugin = Bukkit.getServer().getPluginManager().getPlugin((PacketUtil.readString(input)));
		name = PacketUtil.readString(input);
		id = input.readInt();
		type = input.readByte();
		isItem = type == 2 ? true : false;
		isOpaque = type == 1 ? true : false;
		hardness = input.readFloat();
		friction = input.readFloat();
		lightLevel = input.readInt();
	}

	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, plugin.getDescription().getName());
		PacketUtil.writeString(output, name);
		output.writeInt(id);
		output.writeByte(isItem ? 2 : isOpaque ? 1 : 0);
		output.writeFloat(hardness);
		output.writeFloat(friction);
		output.writeInt(lightLevel);
	}

	public void run(int playerId) {
	}

	public void failure(int playerId) {
	}

	public PacketType getPacketType() {
		return PacketType.PacketCustomMaterial;
	}

	public int getVersion() {
		return 2;
	}
}
