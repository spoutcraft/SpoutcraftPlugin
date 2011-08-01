package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface SpoutPacket {
	
	public int getNumBytes();
	
	public void readData(DataInputStream input) throws IOException;
	
	public void writeData(DataOutputStream output) throws IOException;
	
	public void run(int playerId);
	
	public PacketType getPacketType();

}
