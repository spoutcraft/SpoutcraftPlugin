package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketBlockHardness implements SpoutPacket{
	private int x, y, z;
	private float hardness;
	
	@Override
	public int getNumBytes() {
		return 16;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		x = input.readInt();
		y = input.readInt();
		z = input.readInt();
		hardness = input.readFloat();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(x);
		output.writeInt(y);
		output.writeInt(z);
		output.writeFloat(hardness);
	}

	@Override
	public void run(int playerId) {

	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketBlockHardness;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
