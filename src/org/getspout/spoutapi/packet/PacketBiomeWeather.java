/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
