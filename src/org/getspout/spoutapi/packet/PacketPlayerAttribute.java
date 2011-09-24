package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketPlayerAttribute implements SpoutPacket {
	private byte attribute = -1;
	private boolean value = false;
	
	public PacketPlayerAttribute() {
		
	}
	
	public PacketPlayerAttribute(byte attribute, boolean value) {
		this.attribute = attribute;
		this.value = value;
	}

	@Override
	public int getNumBytes() {
		return 2;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		attribute = input.readByte();
		value = input.readBoolean();

	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(attribute);
		output.writeBoolean(value);
		
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketPlayerAttribute;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
