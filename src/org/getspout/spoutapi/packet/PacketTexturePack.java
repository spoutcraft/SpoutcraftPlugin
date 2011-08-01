package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketTexturePack implements SpoutPacket{
	private String url;
	public PacketTexturePack(){
		
	}
	
	public PacketTexturePack(String url) {
		this.url = url;
	}

	@Override
	public int getNumBytes() {
		return PacketUtil.getNumBytes(url);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		url = PacketUtil.readString(input, 256);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, url);
	}

	@Override
	public void run(int PlayerId) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketTexturePack;
	}

}
