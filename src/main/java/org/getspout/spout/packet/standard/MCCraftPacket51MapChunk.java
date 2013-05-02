/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spout.packet.standard;

import java.lang.reflect.Field;

import net.minecraft.server.v1_5_R3.Packet51MapChunk;

import org.getspout.spoutapi.packet.standard.MCPacket51MapChunk;

public class MCCraftPacket51MapChunk extends MCCraftPacket implements MCPacket51MapChunk {
	@Override
	public Packet51MapChunk getPacket() {
		return (Packet51MapChunk) packet;
	}

	@Override
	public int getX() {
		return getPacket().a;
	}

	@Override
	public int getZ() {
		return getPacket().b;
	}

	@Override
	public void setX(int x) {
		getPacket().a = x;
	}

	@Override
	public void setZ(int z) {
		getPacket().b = z;
	}

	@Override
	public byte[] getCompressedChunkData() {
		try {
			Field buffer = Packet51MapChunk.class.getDeclaredField("buffer");
			buffer.setAccessible(true);
			return (byte[]) buffer.get(getPacket());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	@Override
	public int getSizeX() {
		return 16;
	}

	@Override
	public int getSizeY() {
		return 256;
	}

	@Override
	public int getSizeZ() {
		return 16;
	}

	@Override
	@Deprecated
	public void setSizeX(int x) {
	}

	@Override
	@Deprecated
	public void setSizeY(int y) {
	}

	@Override
	@Deprecated
	public void setSizeZ(int z) {
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	@Deprecated
	public void setY(int y) {
	}
}
