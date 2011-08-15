package org.getspout.spoutapi.block;

import java.io.Serializable;

import org.bukkit.Chunk;

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

}
