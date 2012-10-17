package org.getspout.spoutapi.packet;

import java.io.IOException;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class PacketValidatePrecache implements SpoutPacket {
	
	long crc;
	String serverName;
	
	public PacketValidatePrecache() {
	}
	
	public PacketValidatePrecache(long crc, String serverName) {
		this.crc = crc;
		this.serverName = serverName;
	}
	
	@Override
	public void readData(SpoutInputStream input) throws IOException {
		crc = input.readLong();
		serverName = input.readString();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeLong(crc);
		output.writeString(serverName);
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketValidatePrecache;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
