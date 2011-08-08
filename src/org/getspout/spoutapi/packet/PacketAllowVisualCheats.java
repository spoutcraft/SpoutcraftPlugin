package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketAllowVisualCheats implements SpoutPacket{
	private boolean cheating = false;
	public PacketAllowVisualCheats() {

	}
	
	public PacketAllowVisualCheats(boolean allow) {
		this.cheating = allow;
	}

	@Override
	public int getNumBytes() {
		return 1;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		cheating = input.readBoolean();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeBoolean(cheating);
	}

	@Override
	public void run(int playerId) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketAllowVisualCheats;
	}

}
