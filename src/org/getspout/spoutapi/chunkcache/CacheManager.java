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
