/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
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
import org.bukkit.inventory.Inventory;

public class InventoryOpenEvent extends InventoryEvent {
	private Inventory other;
	private static final long serialVersionUID = -5638814678689580398L;

	public InventoryOpenEvent(Player player, Inventory inventory, Inventory other) {
		super("InventoryOpenEvent", player, inventory);
		this.other = other;
	}

	public InventoryOpenEvent(Player player, Inventory inventory, Inventory other, Location location) {
		super("InventoryOpenEvent", player, inventory, location);
		this.other = other;
	}
	
	/**
	 * Get's the top (or main) inventory that was opened
	 * @return inventory opened
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
	
	/**
	 * Get's the second, bottom inventory that was opened. 
	 * @return bottom inventory opened or null if there was no second inventory opened
	 */
	public Inventory getBottomInventory() {
		return this.other;
	}
}
