package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketBiomeWeather implements SpoutPacket {
	public byte biome;
	public byte weather;
	
	public PacketBiomeWeather(byte biome, byte weather) {
		this.biome = biome;
		this.weather = weather;
	}

	@Override
	public int getNumBytes() {
		return 2;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		biome = input.readByte();
		weather = input.readByte();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(biome);
		output.writeByte(weather);
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketBiomeWeather;
	}

	@Override
	public int getVersion() {
		return 0;
	}

	@Override
	public void run(int PlayerId) {
	}
}
