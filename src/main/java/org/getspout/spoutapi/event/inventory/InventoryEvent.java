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
package org.getspout.spoutapi.event.inventory;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.inventory.Inventory;

public abstract class InventoryEvent extends Event implements Cancellable {
	protected final Inventory inventory;
	protected final Player player;
	protected boolean cancelled;
	protected Location location = null;

	public InventoryEvent(String event, Player player, Inventory inventory) {
		this.player = player;
		this.inventory = inventory;
	}

	public InventoryEvent(String event, Player player, Inventory inventory, Location location) {
		this.player = player;
		this.inventory = inventory;
		this.location = location;
	}

	public Player getPlayer() {
		return player;
	}

	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * Gets the location of the inventory, if there is one. Returns null if no location could be found.
	 * @return location of the inventory
	 */
	public Location getLocation() {
		return location;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}
}
