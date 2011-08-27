package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketSetVelocity implements SpoutPacket {
	private double motX = 0;
	private double motY = 0;
	private double motZ = 0;
	
	public PacketSetVelocity() {
		
	}
	
	public PacketSetVelocity(double motX, double motY, double motZ) {
		this.motX = motX;
		this.motY = motY;
		this.motZ = motZ;
	}

	@Override
	public int getNumBytes() {
		return 24;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		motX = input.readDouble();
		motY = input.readDouble();
		motZ = input.readDouble();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeDouble(motX);
		output.writeDouble(motY);
		output.writeDouble(motZ);
	}

	@Override
	public void run(int playerId) {
		
	}

	@Override
	public void failure(int playerId) {
		
	}

	@Override
	public PacketType getPacketType() {
		return null;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
