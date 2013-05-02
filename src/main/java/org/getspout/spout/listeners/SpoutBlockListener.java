/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
package org.getspout.spout.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.craftbukkit.v1_5_R3.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.getspout.spout.Spout;

import org.getspout.spout.block.SpoutCraftBlock;
import org.getspout.spout.inventory.SimpleMaterialManager;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.event.spout.ServerTickEvent;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutBlockListener implements Listener {
	private final SimpleMaterialManager mm;

	/**
	 * Used to prevent multiple piston events in the same tick.
	 */
	private List<Location> pistonEventQueue = new ArrayList<Location>();

	public SpoutBlockListener(Spout plugin) {
		mm = (SimpleMaterialManager) SpoutManager.getMaterialManager();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent event) {
		if (event.isCancelled()) {
			return;
		}

		SpoutBlock block = (SpoutBlock) event.getBlock();

		SpoutPlayer player = (SpoutPlayer) event.getPlayer();

		if (block.getCustomBlock() != null) {
			CustomBlock material = block.getCustomBlock();
			material.onBlockDestroyed(block.getWorld(), block.getX(), block.getY(), block.getZ(), player);
			if (material.getItemDrop() != null) {
				if (player.getGameMode() == GameMode.SURVIVAL) {
					block.getWorld().dropItem(block.getLocation(), material.getItemDrop());
				}
				block.setTypeId(0);
				event.setCancelled(true);
			}
			mm.removeBlockOverride(block);
		}
	}

	// This replaces nms functionality that is broken due to the references in the nms.Block.byId[] no longer matching the static final refernces in Block.
	// Specifically, public boolean a(int i, int j, int k, int l, boolean flag, int i1) in World.java is broken otherwise.
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockCanBuild(BlockCanBuildEvent event) {
		if (event.isBuildable()) {
			return;
		}
		Block block = event.getBlock();
		Material type = block.getType();
		if (type == Material.WATER || type == Material.STATIONARY_WATER || type == Material.LAVA || type == Material.STATIONARY_LAVA || type == Material.FIRE || type == Material.SNOW || type == Material.DEAD_BUSH || type == Material.LONG_GRASS) {
			if (net.minecraft.server.v1_5_R3.Block.byId[event.getMaterialId()].canPlace(((CraftWorld) block.getWorld()).getHandle(), block.getX(), block.getY(), block.getZ())) {
				event.setBuildable(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPistonExtend(BlockPistonExtendEvent event) {
		if (event.isCancelled()) {
			return;
		}

		if (this.pistonEventQueue.contains(event.getBlock().getLocation())) {
			return;
		}
		pistonEventQueue.add(event.getBlock().getLocation());

		List eventBlocks = event.getBlocks();
		ListIterator itr = eventBlocks.listIterator(eventBlocks.size());
		while (itr.hasPrevious()) {
			SpoutBlock sb = (SpoutBlock) itr.previous();
			pistonBlockMove(sb, event.getDirection());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPistonRetract(BlockPistonRetractEvent event) {
		if ((event.isCancelled()) || (!event.isSticky())) {
			return;
		}

		if (this.pistonEventQueue.contains(event.getBlock().getLocation())) {
			return;
		}
		pistonEventQueue.add(event.getBlock().getLocation());

		pistonBlockMove((SpoutBlock) event.getBlock().getRelative(event.getDirection(), 2), event.getDirection().getOppositeFace());
	}

	private void pistonBlockMove(SpoutBlock block, BlockFace blockFace) {
		if (block.getCustomBlock() == null) {
			return;
		}
		if (block.getPistonMoveReaction().equals(PistonMoveReaction.MOVE)) {
			int customBlockId = block.getCustomBlock().getCustomId();
			Byte customBlockData = block.getCustomBlockData();

			SpoutCraftBlock targetScb = (SpoutCraftBlock) block.getRelative(blockFace);

			// Only move the data, since the base block will be moved *After* this event fires. So if we also move the base block now, we are in fact, creating a new block.
			targetScb.setCustomBlockId(customBlockId);
			targetScb.setCustomBlockData(customBlockData);
			this.mm.queueBlockOverrides(targetScb, customBlockId, customBlockData);

			this.mm.removeBlockOverride(block);
		} else if (block.getPistonMoveReaction().equals(PistonMoveReaction.BREAK)) {
			breakOnPushed(block);
		}
	}

	public void breakOnPushed(SpoutBlock sb) {
		if (sb.getCustomBlock() == null || !sb.getPistonMoveReaction().equals(PistonMoveReaction.BREAK)) {
			return;
		}
		sb.getWorld().dropItemNaturally(sb.getLocation(), sb.getCustomBlock().getItemDrop());
		SpoutManager.getMaterialManager().removeBlockOverride(sb);
	}

	@EventHandler
	public void onTick(ServerTickEvent event) {
		pistonEventQueue.clear();
	}
}
