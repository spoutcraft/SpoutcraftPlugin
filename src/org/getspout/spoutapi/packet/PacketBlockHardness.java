package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Location;

public class PacketBlockHardness implements SpoutPacket{
	private int[] xCoords;
	private int[] yCoords;
	private int[] zCoords;
	private float[] hardness;
	
	public PacketBlockHardness(Location location, float hardness) {
		xCoords = new int[1];
		xCoords[0] = location.getBlockX();
		yCoords = new int[1];
		yCoords[0] = location.getBlockY();
		zCoords = new int[1];
		zCoords[0] = location.getBlockZ();
		this.hardness = new float[1];
		this.hardness[0] = hardness;
	}
	
	public PacketBlockHardness(int[] xCoords, int[] yCoords, int zCoords[], float[] hardness) {
		this.xCoords = xCoords;
		this.yCoords = yCoords;
		this.zCoords = zCoords;
		this.hardness = hardness;
	}
	
	@Override
	public int getNumBytes() {
		return 16;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		int size = input.readInt();
		xCoords = new int[size];
		yCoords = new int[size];
		zCoords = new int[size];
		hardness = new float[size];
		for (int i = 0; i < size; i++) {
			xCoords[i] = input.readInt();
		}
		for (int i = 0; i < size; i++) {
			yCoords[i] = input.readInt();
		}
		for (int i = 0; i < size; i++) {
			zCoords[i] = input.readInt();
		}
		for (int i = 0; i < size; i++) {
			hardness[i] = input.readFloat();
		}
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		int size = xCoords.length;
		output.writeInt(size);
		for (int i = 0; i < size; i++) {
			output.writeInt(xCoords[i]);
		}
		for (int i = 0; i < size; i++) {
			output.writeInt(yCoords[i]);
		}
		for (int i = 0; i < size; i++) {
			output.writeInt(zCoords[i]);
		}
		for (int i = 0; i < size; i++) {
			output.writeFloat(hardness[i]);
		}
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
