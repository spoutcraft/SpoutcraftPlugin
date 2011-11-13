/*
 * This file is part of Spout (http://wiki.getspout.org/).
 * 
 * Spout is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.chunkstore;

import gnu.trove.list.array.TIntArrayList;

import org.bukkit.World;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.packet.PacketCustomMultiBlockOverride;
import org.getspout.spoutapi.packet.SpoutPacket;
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
	}
	
	public void updateChunk(int chunkX, int chunkZ) {
		short[] data = SpoutManager.getChunkDataManager().getCustomBlockIds(world, chunkX, chunkZ);
		if (data == null){
			return;
		}
		TIntArrayList xCoords = new TIntArrayList(100);
		TIntArrayList yCoords = new TIntArrayList(100);
		TIntArrayList zCoords = new TIntArrayList(100);
		TIntArrayList typeIds = new TIntArrayList(100);
		for (int i = 0; i < data.length; i++) {
			if (data[i] != 0) {
				int x = ((i >> 11) & 0xF) + chunkX * 16;
				int y = i & 0x7F;
				int z = ((i >> 7) & 0xF) + chunkZ * 16;
				xCoords.add(x);
				yCoords.add(y);
				zCoords.add(z);
				typeIds.add(data[i]);
			}
		}
		if (xCoords.size() > 0) {
			SpoutPacket packet = new PacketCustomMultiBlockOverride(xCoords, yCoords, zCoords, typeIds);
			player.sendPacket(packet);
		}
	}
}