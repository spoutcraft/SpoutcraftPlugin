/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
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
package org.getspout.spoutapi.event.inventory;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class InventoryPlayerClickEvent extends InventoryClickEvent {
	private static final HandlerList handlers = new HandlerList();

	public InventoryPlayerClickEvent(Player player, Inventory inventory, InventorySlotType type, ItemStack item, ItemStack cursor, int slot, boolean leftClick, boolean shift, Location location) {
		super("InventoryPlayerClickEvent", player, inventory, type, item, cursor, slot, leftClick, shift, location);
	}

	@Override
	protected int convertSlot(int slot) { // Why?
		return slot;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}