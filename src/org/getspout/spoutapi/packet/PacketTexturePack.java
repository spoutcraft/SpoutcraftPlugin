package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketTexturePack implements SpoutPacket{
	private String url;
	private long expectedCRC;
	
	public PacketTexturePack(){
		
	}
	
	public PacketTexturePack(String url, long expectedCRC) {
		this.url = url;
		this.expectedCRC = expectedCRC;
	}

	@Override
	public int getNumBytes() {
		return PacketUtil.getNumBytes(url) + 8;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		url = PacketUtil.readString(input, 256);
		expectedCRC = input.readLong();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, url);
		output.writeLong(expectedCRC);
	}

	@Override
	public void run(int PlayerId) {
		
	}

	@Override
	public void failure(int id) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketTexturePack;
	}
	
	@Override
	public int getVersion() {
		return 2;
	}

}
