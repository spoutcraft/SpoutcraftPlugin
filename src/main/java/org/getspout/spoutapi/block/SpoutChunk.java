/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.block;

import java.io.Serializable;

import org.bukkit.Chunk;

import org.getspout.spoutapi.material.CustomBlock;

public interface SpoutChunk extends Chunk {
	/**
	 * Sets chunk data for the chunk at (x, z) and a given id.
	 * <p/>
	 * Setting data for unloaded chunks has an undefined effect.
	 * @param id   the id used to retrieve the data
	 * @param data the data to be stored
	 * @return the old data for that block using that string
	 */
	public Serializable setData(String id, Serializable data);

	/**
	 * Returns the chunk data for the chunk at (x, z) and a given id.
	 * <p/>
	 * Retrieving data for unloaded chunks is undefined.
	 * @param id the id used to retrieve the data
	 * @return the old data for that block using that string
	 */
	public Serializable getData(String id);

	/**
	 * Removes and returns the chunk data for the chunk at (x, z) and a given id.
	 * <p/>
	 * Deleting data for unloaded chunks has an undefined effect.
	 * @param id the id used to retrieve the data
	 * @return the old data for that block using that string
	 */
	public Serializable removeData(String id);

	/**
	 * Gets the custom block ids that are used for the chunk at (x, z).
	 * <p/>
	 * It may be null if there are no custom block ids.
	 * <p/>
	 * Modifying this array <b>will</b> change the contents of this chunk.
	 * @return custom block ids
	 */
	public short[] getCustomBlockIds();

	/**
	 * Sets the custom block ids that are used for the chunk at (x, z).
	 * <p/>
	 * This array should be 32768 in length.
	 * <p/>
	 * Modifying this array will <b>override</b> the contents of this chunk.
	 * @param ids the custom block ids
	 */
	public void setCustomBlockIds(short[] ids);

	/**
	 * Gets the custom block id at this x, y, z location.
	 * <p/>
	 * If no custom block exists, it will return zero,
	 * @param x
	 * @param y
	 * @param z
	 * @return custom block id
	 */
	public short getCustomBlockId(int x, int y, int z);

	/**
	 * Sets the custom block id at this x, y, z location
	 * @param x
	 * @param y
	 * @param z
	 * @param id to set
	 * @return the previous id at the location
	 */
	public short setCustomBlockId(int x, int y, int z, short id);

	/**
	 * Sets the custom block at this x, y, z location
	 * @param x
	 * @param y
	 * @param z
	 * @param custom block to set
	 * @return the previous custom block at the location, or null if none existed.
	 */
	public CustomBlock setCustomBlock(int x, int y, int z, CustomBlock block);
	
	public byte[] getCustomBlockData();

	public void setCustomBlockData(byte[] rots);
	
	public byte getCustomBlockData(int x, int y, int z);
	
	public byte setCustomBlockData(int x, int y, int z, byte rot);
	
	public CustomBlock setCustomBlock(int x, int y, int z, CustomBlock block, byte data);
}
