package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.block.Biome;
import org.getspout.spoutapi.block.SpoutWeather;

public class PacketBiomeWeather implements SpoutPacket {
	public byte biome;
	public byte weather;
	
	public PacketBiomeWeather(Biome biomeEnum, SpoutWeather weatherEnum) {
		this.biome = (byte) biomeEnum.ordinal();
		this.weather = (byte) weatherEnum.ordinal();
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
	
	@Override
	public void failure(int id) {
		
	}

}
