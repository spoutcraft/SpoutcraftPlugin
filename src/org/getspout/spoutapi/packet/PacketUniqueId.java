package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class PacketUniqueId implements SpoutPacket{
	private long lsb;
	private long msb;
	private int entityId;
	
	public PacketUniqueId() {
		
	}
	
	public PacketUniqueId(UUID id, int entityId) {
		lsb = id.getLeastSignificantBits();
		msb = id.getMostSignificantBits();
		this.entityId = entityId;
	}

	@Override
	public int getNumBytes() {
		return 20;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		lsb = input.readLong();
		msb = input.readLong();
		entityId = input.readInt();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeLong(lsb);
		output.writeLong(msb);
		output.writeInt(entityId);
	}

	@Override
	public void run(int playerId) {
		
	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketUniqueId;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
