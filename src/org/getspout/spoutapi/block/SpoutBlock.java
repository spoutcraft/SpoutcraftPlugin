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

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public interface SpoutBlock extends Block{
	
	/**
	 * Set's the block type safely, appearing instantly and seamlessly to all players
	 * Can be used on main thread, but recommended for async threads
	 * @param type to set the block to
	 */
	public void setTypeAsync(Material type);
	
	/**
	 * Set's the block type id safely, appearing instantly and seamlessly to all players
	 * Can be used on main thread, but recommended for async threads
	 * @param type id to set the block to
	 */
	public void setTypeIdAsync(int type);
	
	/**
	 * Set's the block data safely, appearing instantly and seamlessly to all players
	 * Can be used on main thread, but recommended for async threads
	 * @param data to set the block to
	 */
	public void setDataAsync(byte data);
	
	/**
	 * Set's the block type id and data safely, appearing instantly and seamlessly to all players
	 * Can be used on main thread, but recommended for async threads
	 * @param type to set the block to
	 * @param data to set the block to
	 */
	public void setTypeIdAndDataAsync(int type, byte data);
	
	/**
	 * Sets block data for the block at (x, y, z) and a given id.  
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
	 * Returns the block data for the block at (x, y, z) and a given id.
	 * 
	 * Retrieving data for unloaded chunks is undefined.
	 * 
	 * @param id the id used to retrieve the data
	 * @return the old data for that block using that string
	 * 
	 */
	public Serializable getData(String id);
	
	/**
	 * Removes and returns the block data for the block at (x, y, z) and a given id.
	 * 
	 * Deleting data for unloaded chunks has an undefined effect.
	 * 
	 * @param id the id used to retrieve the data
	 * @return the old data for that block using that string
	 * 
	 */
	public Serializable removeData(String id);
	
	/**
	 * Gets the name of this block
	 * 
	 * Ex dirt block returns "Dirt".
	 * @return block name
	 */
	public String getName();
	
	/**
	 * Sets the power state of this block, overriding the default power state.
	 * 
	 * This will override the default power state. 
	 * Unpowering this location will prevent normal redstone sources from working, powering it will allow for non-redstone sources to generate a power current.
	 * 
	 * Note: The new power state will remain even if the underlying block type changes!
	 * @param power to set
	 */
	public void setBlockPowered(boolean power);
	
	/**
	 * Sets the power state of the block face of this block, overriding the default power state.
	 * 
	 * Valid block faces are null (entire block), EAST, WEST, NORTH, SOUTH, UP, DOWN. Non-valid states will throw an exception.
	 * 
	 * This will override the default power state of this blockface.
	 * Unpowering this location will prevent normal redstone sources from working, powering it will allow for non-redstone sources to generate a power current.
	 * 
	 * Note: The new power state will remain even if the underlying block type changes!
	 * @param power to set
	 * @param face to affect
	 */
	public void setBlockPowered(boolean power, BlockFace face);
	
	/**
	 * Resets the block back to the minecraft standard rules for power.
	 */
	public void resetBlockPower();

	//public void setLightLevel(byte level);
	
	//public void resetLightLevel();
	
	/**
	 * Gets the hardness of this block.
	 * @return hardness
	 */
	public float getHardness();
	
	/**
	 * Sets the hardness of this location. 
	 * 
	 * Note: The new hardness value will remain even if the underlying block type changes!
	 * @param hardness to set
	 */
	public void setHardness(float hardness);
	
	/**
	 * Resets the hardness of this location back to the standard minecraft values.
	 */
	public void resetHardness();
	
	@Override
	public SpoutBlock getFace(BlockFace face);
	
	@Override
	public SpoutBlock getFace(BlockFace face, int distance);
	
	@Override
	public SpoutBlock getRelative(int modX, int modY, int modZ);
	
	@Override
	public SpoutBlock getRelative(BlockFace face);
	
	@Override
	public SpoutChunk getChunk();

}
