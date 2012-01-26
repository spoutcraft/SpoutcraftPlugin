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
package org.getspout.spoutapi.chunkstore;

import org.bukkit.Chunk;
import org.bukkit.World;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.packet.PacketCustomBlockChunkOverride;
import org.getspout.spoutapi.player.SpoutPlayer;

public abstract class PlayerTask implements Runnable {
	SpoutPlayer player;
	World world;
	int viewDistance;
	int chunkX, chunkZ;
	public PlayerTask(SpoutPlayer player, World world, int viewDistance) {
		this.player = player;
		this.world = world;
		this.viewDistance = viewDistance;
		Chunk chunk = player.getLocation().getBlock().getChunk();
		chunkX = chunk.getX();
		chunkZ = chunk.getZ();
	}

	public void updateChunk(int chunkX, int chunkZ) {
		short[] data = SpoutManager.getChunkDataManager().getCustomBlockIds(world, chunkX, chunkZ);
		if (data == null) {
			return;
		}
		player.sendPacket(new PacketCustomBlockChunkOverride(data, chunkX, chunkZ));
	}
}
