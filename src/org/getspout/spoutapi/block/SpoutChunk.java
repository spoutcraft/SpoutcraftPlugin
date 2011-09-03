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
package org.getspout.spoutapi.block;

import java.io.Serializable;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.util.BlockVector;

public interface SpoutChunk extends Chunk{
	
	/**
	 * Sets chunk data for the chunk at (x, z) and a given id.  
	 * 
	 * Setting data for unloaded chunks has an undefined effect.
	 * 
	 * @param id the id used to retrieve the data
	 * @param data the data to be stored
	 * @return the old data for that block using that string
	 * 
	 */
	public Serializable setData(String id, Serializable data);
	
	/**
	 * Returns the chunk data for the chunk at (x, z) and a given id.
	 * 
	 * Retrieving data for unloaded chunks is undefined.
	 * 
	 * @param id the id used to retrieve the data
	 * @return the old data for that block using that string
	 * 
	 */
	public Serializable getData(String id);
	
	/**
	 * Removes and returns the chunk data for the chunk at (x, z) and a given id.
	 * 
	 * Deleting data for unloaded chunks has an undefined effect.
	 * 
	 * @param id the id used to retrieve the data
	 * @return the old data for that block using that string
	 * 
	 */
	public Serializable removeData(String id);
	
	/**
	 * Returns an array of all the blocks in the chunk that have associated data
	 * 
	 * Only the least significant portion of the vector coordinates is guaranteed to be correct
	 * 
	 * @param world the world
	 * @param x the X chunk coordinate
	 * @param z the Z chunk coordinate
	 * @return and array containing all the blocks with associated data
	 * 
	 */
	public BlockVector[] getTaggedBlocks();

}
