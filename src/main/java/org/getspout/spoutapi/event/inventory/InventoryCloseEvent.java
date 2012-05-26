/*
 * This file is part of SpoutPluginAPI.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPluginAPI is licensed under the GNU Lesser General Public License.
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
package org.getspout.spoutapi.event.inventory;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

@Deprecated
public class InventoryCloseEvent extends InventoryEvent {
	private static final HandlerList handlers = new HandlerList();
	private final Inventory other;

	public InventoryCloseEvent(Player player, Inventory inventory, Inventory other) {
		super("InventoryCloseEvent", player, inventory);
		this.other = other;
	}

	public InventoryCloseEvent(Player player, Inventory inventory, Inventory other, Location location) {
		super("InventoryCloseEvent", player, inventory, location);
		this.other = other;
	}

	/**
	 * Get's the top (or main) inventory that was closed
	 * @return inventory closed
	 */
	public Inventory getInventory() {
		return this.inventory;
	}

	/**
	 * Get's the second, bottom inventory that was closed.
	 * @return bottom inventory closed or null if there was no second inventory closed
	 */
	public Inventory getBottomInventory() {
		return this.other;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}