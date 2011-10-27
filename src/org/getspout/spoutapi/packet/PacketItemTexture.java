package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketItemTexture implements SpoutPacket{
	private int id;
	private short data;
	private String pluginName;
	private String name;
	public PacketItemTexture() {
		
	}
	
	public PacketItemTexture(int id, short data, String pluginName, String name) {
		this.id = id;
		this.data = data;
		this.name = name;
		this.pluginName = pluginName;
	}
	
	private String getPluginName() {
		if (pluginName == null) {
			return "";
		} else {
			return pluginName;
		}
	}
	
	private void setPluginName(String pluginName) {
		if (pluginName.equals("")) {
			this.pluginName = "";
		} else {
			this.pluginName = pluginName;
		}
	}

	@Override
	public int getNumBytes() {
		return 6 + PacketUtil.getNumBytes(name) + PacketUtil.getNumBytes(getPluginName());
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		id = input.readInt();
		data = input.readShort();
		name = PacketUtil.readString(input);
		setPluginName(PacketUtil.readString(input));
		
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(id);
		output.writeShort(data);
		PacketUtil.writeString(output, name);
		PacketUtil.writeString(output, getPluginName());
	}

	@Override
	public void run(int PlayerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketItemTexture;
	}
	
	@Override
	public int getVersion() {
		return 1;
	}

	@Override
	public void failure(int playerId) {

	}

}
