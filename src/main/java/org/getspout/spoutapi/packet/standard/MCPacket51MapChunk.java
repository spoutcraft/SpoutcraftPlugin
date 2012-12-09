/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
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

public interface MCPacket51MapChunk {
	/**
	 * @return the x world block coordinate
	 */
	public int getX();

	/**
	 * @return the y world block coordinate
	 */
	public int getY();

	/**
	 * @return the z world block coordinate
	 */
	public int getZ();

	/**
	 * @param x sets the x world block coordinate
	 */
	public void setX(int x);

	/**
	 * @param y sets the y world block coordinate
	 */
	public void setY(int y);

	/**
	 * @param z sets the z world block coordinate
	 */
	public void setZ(int z);

	/**
	 * @return (X size of the cuboid) - 1
	 */
	public int getSizeX();

	/**
	 * @return (Y size of the cuboid) - 1
	 */
	public int getSizeY();

	/**
	 * @return (Z size of the cuboid) - 1
	 */
	public int getSizeZ();

	/**
	 * The cuboid to update must be completely within 1 chunk
	 * @param x (Z size of cuboid) - 1
	 */
	public void setSizeX(int x);

	/**
	 * The cuboid to update must be completely within 1 chunk
	 * @param y (Y size of cuboid) - 1
	 */
	public void setSizeY(int y);

	/**
	 * The cuboid to update must be completely within 1 chunk
	 * @param z (Z size of cuboid) - 1
	 */
	public void setSizeZ(int z);

	/**
	 * @return chunk data compressed using Deflate
	 */
	public byte[] getCompressedChunkData();
}
