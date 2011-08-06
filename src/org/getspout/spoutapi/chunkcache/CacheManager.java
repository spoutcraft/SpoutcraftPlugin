package org.getspout.spoutapi.chunkcache;


public interface CacheManager {

	/**
	 * Handles a cache update packet from the client
	 * 
	 * @param id the entity id of the player
	 * @param add true if the hashes are to be added to the pool
	 * @param hashesh array of hashes to be added/removed from the hash pool
	 * @return an empty MCPacket of type packetId
	 * 
	 */
	void handle(int id, boolean add, long[] hashes);
	
}
