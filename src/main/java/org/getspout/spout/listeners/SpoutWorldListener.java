/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spout.listeners;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.getspout.spout.Spout;

import org.getspout.spout.block.SpoutCraftChunk;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.chunkstore.SimpleChunkDataManager;

public class SpoutWorldListener implements Listener {
	public SpoutWorldListener(Spout plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChunkLoad(ChunkLoadEvent event) {
		if (SpoutCraftChunk.replaceBukkitChunk(event.getChunk())) {
			// Update the reference to the chunk in the event
			try {
				Field chunk = ChunkEvent.class.getDeclaredField("chunk");
				chunk.setAccessible(true);
				chunk.set(event, event.getChunk().getWorld().getChunkAt(event.getChunk().getX(), event.getChunk().getZ()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			SimpleChunkDataManager dm = (SimpleChunkDataManager)SpoutManager.getChunkDataManager();
			dm.loadChunk(event.getChunk());
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChunkUnload(ChunkUnloadEvent event) {
			SimpleChunkDataManager dm = (SimpleChunkDataManager)SpoutManager.getChunkDataManager();
			dm.saveChunk(event.getWorld(), event.getChunk().getX(), event.getChunk().getZ(), true);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onWorldLoad(WorldLoadEvent event) {
		SimpleChunkDataManager dm = (SimpleChunkDataManager)SpoutManager.getChunkDataManager();
		dm.loadWorldChunks(event.getWorld());
	}
}
