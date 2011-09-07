package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class PacketBlockHardness implements SpoutPacket{
	private List<Integer> xCoords;
	private List<Integer> yCoords;
	private List<Integer> zCoords;
	private List<Float> hardness;
	
	public PacketBlockHardness() {
		
	}
	
	public PacketBlockHardness(Location location, float hardness) {
		xCoords = new ArrayList<Integer>(1);
		xCoords.add(location.getBlockX());
		yCoords = new ArrayList<Integer>(1);
		yCoords.add(location.getBlockY());
		zCoords = new ArrayList<Integer>(1);
		zCoords.add(location.getBlockZ());
		this.hardness = new ArrayList<Float>(1);
		this.hardness.add(hardness);
	}
	
	public PacketBlockHardness(List<Integer> xCoords, List<Integer> yCoords, List<Integer> zCoords, List<Float> hardness) {
		this.xCoords = xCoords;
		this.yCoords = yCoords;
		this.zCoords = zCoords;
		this.hardness = hardness;
	}
	
	@Override
	public int getNumBytes() {
		return 4 + (xCoords.size() * 4 * 4);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		int size = input.readInt();
		xCoords = new ArrayList<Integer>(size);
		yCoords = new ArrayList<Integer>(size);
		zCoords = new ArrayList<Integer>(size);
		hardness = new ArrayList<Float>(size);
		for (int i = 0; i < size; i++) {
			xCoords.add(input.readInt());
		}
		for (int i = 0; i < size; i++) {
			yCoords.add(input.readInt());
		}
		for (int i = 0; i < size; i++) {
			zCoords.add(input.readInt());
		}
		for (int i = 0; i < size; i++) {
			hardness.add(input.readFloat());
		}
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		int size = xCoords.size();
		output.writeInt(size);
		for (int i = 0; i < size; i++) {
			output.writeInt(xCoords.get(i));
		}
		for (int i = 0; i < size; i++) {
			output.writeInt(yCoords.get(i));
		}
		for (int i = 0; i < size; i++) {
			output.writeInt(zCoords.get(i));
		}
		for (int i = 0; i < size; i++) {
			output.writeFloat(hardness.get(i));
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
