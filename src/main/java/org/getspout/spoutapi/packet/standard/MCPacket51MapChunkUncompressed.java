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
package org.getspout.spoutapi.packet.standard;

public interface MCPacket51MapChunkUncompressed extends MCPacket51MapChunk {
	/**
	 * The chunk data contains 4 sections
	 * <p/>
	 * type data:	  1 byte per block
	 * meta data:	  1 nibble per block
	 * block light:	1 nibble per block
	 * sunlight level: 1 nibble per block
	 * <p/>
	 * The data in each section can be indexed using
	 * <p/>
	 * data[x, y, z] = section[y + z*(sizeY + 1) + x*(sizeY + 1)*(sizeZ + 1)
	 * <p/>
	 * For the last 3 sections, section[] is an array of nibbles.
	 * <p/>
	 * At least one of the 3 size coordinates is even, which guarantees an even number of nibbles.
	 * @return uncompressed chunk data
	 */
	public byte[] getUncompressedChunkData();
}
