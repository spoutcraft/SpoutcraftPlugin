package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class PacketSpawnTextEntity implements SpoutPacket {

	private String text;
	private Location location;
	private int duration;
	private Vector movement;
    private float scale;

	public PacketSpawnTextEntity(String text, Location location, float scale, int duration, Vector movement) {
		this.text = text;
		this.location = location;
		this.duration = duration;
		this.movement = movement;
		this.scale = scale;
	}
	
	@Override
	public int getNumBytes() {
		return PacketUtil.getNumBytes(text) + 3 * 8 + 4 + 4 + 3 * 9;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		text = PacketUtil.readString(input);
		double x, y, z;
		x = input.readDouble();
		y = input.readDouble();
		z = input.readDouble();
		location = new Location(null, x, y, z);
		scale = input.readFloat();
		duration = input.readInt();
		x = input.readDouble();
		y = input.readDouble();
		z = input.readDouble();
		movement = new Vector(x, y, z);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, text);
		output.writeDouble(location.getX());
		output.writeDouble(location.getY());
		output.writeDouble(location.getZ());
		output.writeFloat(scale);
		output.writeInt(duration);
		output.writeDouble(movement.getX());
		output.writeDouble(movement.getY());
		output.writeDouble(movement.getZ());
	}

	@Override
	public void run(int playerId) {
		// Nothing to do here
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketSpawnTextEntity;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
