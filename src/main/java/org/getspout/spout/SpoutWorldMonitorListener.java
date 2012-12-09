/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spout;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.chunkstore.SimpleChunkDataManager;

public class SpoutWorldMonitorListener implements Listener {
	public SpoutWorldMonitorListener(Spout plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onChunkUnload(ChunkUnloadEvent event) {
		if (event.isCancelled()) {
			return;
		}
		SimpleChunkDataManager dm = (SimpleChunkDataManager)SpoutManager.getChunkDataManager();
		dm.saveChunk(event.getChunk());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onWorldSave(WorldSaveEvent event) {
		SimpleChunkDataManager dm = (SimpleChunkDataManager)SpoutManager.getChunkDataManager();
		dm.saveWorldChunks(event.getWorld());
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onWorldUnload(WorldUnloadEvent event) {
		if (event.isCancelled()) {
			return;
		}
		SimpleChunkDataManager dm = (SimpleChunkDataManager)SpoutManager.getChunkDataManager();
		dm.unloadWorldChunks(event.getWorld());
	}
}
