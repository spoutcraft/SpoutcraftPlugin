package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketOpenSignGUI implements SpoutPacket {
	int x,y,z;
	
	public PacketOpenSignGUI(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public int getNumBytes() {
		return 12; //Never be too lazy to calculate !
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		x = input.readInt();
		y = input.readInt();
		z = input.readInt();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(x);
		output.writeInt(y);
		output.writeInt(z);
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketOpenSignGUI;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
