package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class PacketMovementModifiers implements SpoutPacket{
	double gravityMod = 1;
	double walkingMod = 1;
	double swimmingMod = 1;
	
	public PacketMovementModifiers() {
		
	}
	
	public PacketMovementModifiers(double gravity, double walking, double swimming) {
		this.gravityMod = gravity;
		this.walkingMod = walking;
		this.swimmingMod = swimming;
	}

	@Override
	public int getNumBytes() {
		return 24;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		gravityMod = input.readDouble();
		walkingMod = input.readDouble();
		swimmingMod = input.readDouble();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeDouble(gravityMod);
		output.writeDouble(walkingMod);
		output.writeDouble(swimmingMod);
	}

	@Override
	public void run(int playerId) {
		
	}

	@Override
	public void failure(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PacketType getPacketType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

}
