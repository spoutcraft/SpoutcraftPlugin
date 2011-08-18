package org.getspout.spoutapi.chunkcache;


public interface CacheManager {

	/**
	 * Handles a cache update packet from the client
	 * 
	 * @param id the entity id of the player
	 * @param add true if the hashes are to be added to the pool
	 * @param hashesh array of hashes to be added/removed from the hash pool
	 * 
	 */
	void handle(int id, boolean add, long[] hashes);
	
	/**
	 * Requests that a chunk is refreshed
	 * 
	 * @param id the player's id
	 * @param cx the chunk X coord
	 * @param cz the chunk Y coord
	 */
	void refreshChunkRequest(int id, int cx, int cz);
}
