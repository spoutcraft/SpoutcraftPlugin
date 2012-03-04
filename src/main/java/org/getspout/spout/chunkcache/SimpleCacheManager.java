/*
 * This file is part of SpoutPlugin (http://www.spout.org/).
 *
 * SpoutPlugin is licensed under the SpoutDev License Version 1.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev license version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.getspout.spout.chunkcache;

import org.getspout.spoutapi.chunkcache.CacheManager;

public class SimpleCacheManager implements CacheManager {
	@Override
	public void handle(int id, boolean add, long[] hashes) {
		//ChunkCache.addToHashUpdateQueue(id, add, hashes);
	}

	@Override
	public void refreshChunkRequest(int id, int cx, int cz) {
		//SpoutCraftPlayer player = (SpoutCraftPlayer)SpoutManager.getPlayerFromId(id);
		//World world = player.getHandle().world;
		//Packet packet51 = new Packet51MapChunk(world.getChunkAt(cx << 4, cz << 4), true, 0);
		//player.getNetServerHandler().sendPacket(packet51);
		//SpoutNetServerHandler.sendChunkTiles(cx, cz, player.getHandle());
	}
}
