package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketPreCacheFile implements SpoutPacket{
	private boolean cached = false;
	private long expectedCRC;
	private String file;
	private String plugin;
	
	public PacketPreCacheFile() {
		
	}
	
	public PacketPreCacheFile(String plugin, String file, long expectedCRC) {
		this.file = file;
		this.plugin = plugin;
		this.expectedCRC = expectedCRC;
	}

	@Override
	public int getNumBytes() {
		return 9 + PacketUtil.getNumBytes(file) + PacketUtil.getNumBytes(plugin);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		this.cached = input.readBoolean();
		this.expectedCRC = input.readLong();
		this.file = PacketUtil.readString(input);
		this.plugin = PacketUtil.readString(input);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeBoolean(this.cached);
		output.writeLong(this.expectedCRC);
		PacketUtil.writeString(output, this.file);
		PacketUtil.writeString(output, this.plugin);
	}

	@Override
	public void run(int playerId) {
		if (!cached) {
			//TODO send file
		}
	}

	@Override
	public void failure(int playerId) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketPreCacheFile;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
