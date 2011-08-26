package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketPreCacheCompleted implements SpoutPacket{
	
	public PacketPreCacheCompleted() {
	}
	
	@Override
	public int getNumBytes() {
		return 0;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketPreCacheCompleted;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
