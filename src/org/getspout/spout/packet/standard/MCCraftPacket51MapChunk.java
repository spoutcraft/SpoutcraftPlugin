/*
 * This file is part of Spout (http://wiki.getspout.org/).
 * 
 * Spout is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spout.packet.standard;

import net.minecraft.server.Packet51MapChunk;

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
	public int getY() {
		return getPacket().b;
	}

	@Override
	public int getZ() {
		return getPacket().c;
	}

	@Override
	public void setX(int x) {
		getPacket().a = x;
	}

	@Override
	public void setY(int y) {
		getPacket().b = y;
	}

	@Override
	public void setZ(int z) {
		getPacket().c = z;
	}

	@Override
	public byte[] getCompressedChunkData() {
		return getPacket().buffer;
	}

	@Override
	public int getSizeX() {
		return getPacket().d;
	}

	@Override
	public int getSizeY() {
		return getPacket().e;
	}

	@Override
	public int getSizeZ() {
		return getPacket().f;
	}

	@Override
	public void setSizeX(int x) {
		getPacket().d = x;
	}

	@Override
	public void setSizeY(int y) {
		getPacket().e = y;
	}

	@Override
	public void setSizeZ(int z) {
		getPacket().f = z;
	}

}
