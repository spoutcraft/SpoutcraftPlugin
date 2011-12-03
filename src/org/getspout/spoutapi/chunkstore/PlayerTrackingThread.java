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

import java.util.concurrent.LinkedBlockingDeque;
import org.bukkit.Bukkit;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.util.map.TIntPairHashSet;

public class PlayerTrackingThread extends Thread{
	private final Thread instance;
	private final SpoutPlayer player;
	private final TIntPairHashSet managedChunks;
	private final int viewDistance;
	private final LinkedBlockingDeque<PlayerTask> queue = new LinkedBlockingDeque<PlayerTask>(1024);
	private volatile boolean shutdown = false;
	public PlayerTrackingThread(SpoutPlayer player) {
		super("Chunk Tracking Thread For " + player.getName());
		this.player = player;
		viewDistance = Bukkit.getServer().getViewDistance();
		managedChunks = new TIntPairHashSet(viewDistance * viewDistance);
		instance = this;
		this.start();
	}
	
	@Override
	public void run() {
		while(!shutdown) {
			try {
				PlayerTask task = queue.take();
				task.run();
			}
			catch (InterruptedException ignore) {
				;
			}
		}
	}

	public void onPlayerJoin() {
		PlayerTask task = new PlayerJoinTask(player, viewDistance, managedChunks);
		queue.add(task);
	}
	
	public void onWorldChange() {
		PlayerTask task = new WorldChangeTask(player, viewDistance, managedChunks);
		queue.add(task);
	}
	
	public void onMoveChunk() {
		PlayerTask task = new PlayerUpdateChunksTask(player, viewDistance, managedChunks);
		queue.add(task);
	}
	
	public void onPlayerQuit() {
		shutdown = true;
		instance.interrupt();
	}
}

class WorldChangeTask extends PlayerTask{
	private TIntPairHashSet managedChunks;
	public WorldChangeTask(SpoutPlayer player, int viewDistance, TIntPairHashSet managedChunks) {
		super(player, player.getWorld(), viewDistance);
		this.managedChunks = managedChunks;
	}

	@Override
	public void run() {
		managedChunks.clear();
		for (int dx = -(viewDistance); dx < viewDistance; dx++) {
			for (int dz = -(viewDistance); dz < viewDistance; dz++) {
				int cx = chunkX + dx;
				int cz = chunkZ + dz;
				managedChunks.add(cx, cz);
				updateChunk(cx, cz);
			}
		}
	}
}


class PlayerUpdateChunksTask extends PlayerTask{
	private TIntPairHashSet managedChunks;
	public PlayerUpdateChunksTask(SpoutPlayer player, int viewDistance, TIntPairHashSet managedChunks) {
		super(player, player.getWorld(), viewDistance);
		this.managedChunks = managedChunks;
	}

	@Override
	public void run() {
		for (int dx = -(viewDistance); dx < viewDistance; dx++) {
			for (int dz = -(viewDistance); dz < viewDistance; dz++) {
				int cx = chunkX + dx;
				int cz = chunkZ + dz;
				if (!managedChunks.contains(cx, cz)) {
					updateChunk(cx, cz);
				}
			}
		}
		
		managedChunks.clear();
		for (int dx = -(viewDistance); dx < viewDistance; dx++) {
			for (int dz = -(viewDistance); dz < viewDistance; dz++) {
				int cx = chunkX + dx;
				int cz = chunkZ + dz;
				managedChunks.add(cx, cz);
			}
		}
	}
}

class PlayerJoinTask extends PlayerTask{
	private TIntPairHashSet managedChunks;
	public PlayerJoinTask(SpoutPlayer player, int viewDistance, TIntPairHashSet managedChunks) {
		super(player, player.getWorld(), viewDistance);
		this.managedChunks = managedChunks;
	}

	@Override
	public void run() {
		for (int dx = -(viewDistance); dx < viewDistance; dx++) {
			for (int dz = -(viewDistance); dz < viewDistance; dz++) {
				int cx = chunkX + dx;
				int cz = chunkZ + dz;
				managedChunks.add(cx, cz);
				updateChunk(cx, cz);
			}
		}
	}
}
