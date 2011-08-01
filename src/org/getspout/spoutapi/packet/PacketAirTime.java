package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketAirTime implements SpoutPacket{
	public int airTime;
	public int air;
	
	public PacketAirTime() {
		
	}
	
	public PacketAirTime(int maxTime, int time) {
		this.airTime = maxTime;
		this.air = time;
	}

	@Override
	public int getNumBytes() {
		return 8;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		this.airTime = input.readInt();
		this.air = input.readInt();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(this.airTime);
		output.writeInt(this.air);
	}

	@Override
	public void run(int id) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(id);
		player.setRemainingAir(air);
		player.setMaximumAir(airTime);
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketAirTime;
	}
}
