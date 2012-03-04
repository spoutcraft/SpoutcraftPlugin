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
package org.getspout.spoutapi.inventory;

import java.util.Collection;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * The InventoryBuilder can build a org.bukkit.inventory.Inventory object from a predetermined collection, array, or size
 */
public interface InventoryBuilder {
	/**
	 * @deprecated Use {@link org.bukkit.Server#createInventory(org.bukkit.inventory.InventoryHolder, int)} instead. <br/><br/>
	 * 
	 * Creates an inventory from the given array of itemstacks and name. The inventory will be the same size as the length of the itemstack array.
	 * @param items to populate the inventory with
	 * @param name of the inventory
	 * @return Inventory
	 */
	public Inventory construct(ItemStack[] items, String name);

	/**
	 * @deprecated Use {@link org.bukkit.Server#createInventory(org.bukkit.inventory.InventoryHolder, int)} instead. <br/><br/>
	 * 
	 * Creates an inventory from the given array of itemstacks and name. The inventory will be the same size as the size of the collection
	 * @param collection to populate the inventory with
	 * @param name of the inventory
	 * @return Inventory
	 */
	public Inventory construct(Collection<ItemStack> items, String name);

	/**
	 * @deprecated Use {@link org.bukkit.Server#createInventory(org.bukkit.inventory.InventoryHolder, int)} instead. <br/><br/>
	 * 
	 * Creates an inventory empty inventory with the given size and name
	 * @param size of the inventory to build
	 * @param name of the inventory
	 * @return Inventory
	 */
	public Inventory construct(int size, String name);
}
