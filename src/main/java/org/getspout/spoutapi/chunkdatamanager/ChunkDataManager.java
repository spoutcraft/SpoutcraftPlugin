/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.chunkdatamanager;

import java.io.Serializable;

import org.bukkit.World;

import org.getspout.spoutapi.inventory.ItemMap;

public interface ChunkDataManager {
	/**
	 * Creates/retrieves a unique id corresponding to this string
	 * <p/>
	 * This id does not persist over server restarts or reloads
	 * @param string the String to get the id for
	 * @return the id associated with this string
	 */
	public int getStringId(String string);

	/**
	 * Sets block data for the block at (x, y, z) and a given id.
	 * <p/>
	 * Setting data for unloaded chunks has an undefined effect.
	 * @param id   the id used to retrieve the data
	 * @param x    the X block coordinate
	 * @param y    the Y block coordinate
	 * @param z    the Z block coordinate
	 * @param data the data to be stored
	 * @return the old data for that block using that string
	 */
	public Serializable setBlockData(String id, World world, int x, int y, int z, Serializable data);

	/**
	 * Returns the block data for the block at (x, y, z) and a given id.
	 * <p/>
	 * Retrieving data for unloaded chunks is undefined.
	 * @param id the id used to retrieve the data
	 * @param x  the X block coordinate
	 * @param y  the Y block coordinate
	 * @param z  the Z block coordinate
	 * @return the old data for that block using that string
	 */
	public Serializable getBlockData(String id, World world, int x, int y, int z);

	/**
	 * Removes and returns the block data for the block at (x, y, z) and a given id.
	 * <p/>
	 * Deleting data for unloaded chunks has an undefined effect.
	 * @param id the id used to retrieve the data
	 * @param x  the X block coordinate
	 * @param y  the Y block coordinate
	 * @param z  the Z block coordinate
	 * @return the old data for that block using that string
	 */
	public Serializable removeBlockData(String id, World world, int x, int y, int z);

	/**
	 * Sets chunk data for the chunk at (x, z) and a given id.
	 * <p/>
	 * Setting data for unloaded chunks has an undefined effect.
	 * @param id    the id used to retrieve the data
	 * @param world the world
	 * @param x     the X chunk coordinate
	 * @param z     the Z chunk coordinate
	 * @param data  the data to be stored
	 * @return the old data for that block using that string
	 */
	public Serializable setChunkData(String id, World world, int x, int z, Serializable data);

	/**
	 * Returns the chunk data for the chunk at (x, z) and a given id.
	 * <p/>
	 * Retrieving data for unloaded chunks is undefined.
	 * @param id    the id used to retrieve the data
	 * @param world the world
	 * @param x     the X chunk coordinate
	 * @param z     the Z chunk coordinate
	 * @return the old data for that block using that string
	 */
	public Serializable getChunkData(String id, World world, int x, int z);

	/**
	 * Removes and returns the chunk data for the chunk at (x, z) and a given id.
	 * <p/>
	 * Deleting data for unloaded chunks has an undefined effect.
	 * @param id    the id used to retrieve the data
	 * @param world the world
	 * @param x     the X chunk coordinate
	 * @param z     the Z chunk coordinate
	 * @return the old data for that block using that string
	 */
	public Serializable removeChunkData(String id, World world, int x, int z);

	/**
	 * Gets the custom block ids that are used for the chunk at (x, z)
	 * <p/>
	 * Modifying this array <b>will</b> change the contents of this chunk.
	 * @param world the world
	 * @param x     the X chunk coordinate
	 * @param z     the Z chunk coordinate
	 * @return custom block ids
	 */
	public short[] getCustomBlockIds(World world, int x, int z);

	/**
	 * Sets the custom block ids that are used for the chunk at (x, z)
	 * <p/>
	 * Modifying this array will <b>override</b> the contents of this chunk.
	 * @param world the world
	 * @param x     the X chunk coordinate
	 * @param z     the Z chunk coordinate
	 * @param ids   the custom block ids
	 */
	public void setCustomBlockIds(World world, int x, int z, short[] ids);

	public byte[] getCustomBlockData(World world, int x, int z);

	public void setCustomBlockData(World world, int x, int z, byte[] rots);

	public ItemMap getItemMap(World world);
}
