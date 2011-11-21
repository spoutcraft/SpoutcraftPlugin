package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketCustomId implements SpoutPacket {
	
	private int itemId;
	private Integer blockId;
	
	public PacketCustomId() {
	}
	
	public PacketCustomId(int itemId, Integer blockId) {
		this.itemId = itemId;
		this.blockId = blockId;
	}
	
	@Override
	public int getNumBytes() {
		return 8;
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		itemId = input.readInt();
		blockId = input.readInt();
		if (blockId == -1) {
			blockId = null;
		}
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(itemId);
		output.writeInt(blockId == null ? -1 : blockId);
	}

	@Override
	public void run(int PlayerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomId;
	}
	
	@Override
	public int getVersion() {
		return 4;
	}

	@Override
	public void failure(int playerId) {
	}


}
