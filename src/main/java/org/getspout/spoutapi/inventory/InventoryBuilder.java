/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
package org.getspout.spoutapi.inventory;

import java.util.Collection;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * The InventoryBuilder can build a org.bukkit.inventory.Inventory object from a predetermined collection, array, or size
 */
public interface InventoryBuilder {
	/**
	 * @param items to populate the inventory with
	 * @param name  of the inventory
	 * @return Inventory
	 * @deprecated Use {@link org.bukkit.Server#createInventory(org.bukkit.inventory.InventoryHolder, int)} instead. <br/><br/>
	 *             <p/>
	 *             Creates an inventory from the given array of itemstacks and name. The inventory will be the same size as the length of the itemstack array.
	 */
	public Inventory construct(ItemStack[] items, String name);

	/**
	 * @param collection to populate the inventory with
	 * @param name       of the inventory
	 * @return Inventory
	 * @deprecated Use {@link org.bukkit.Server#createInventory(org.bukkit.inventory.InventoryHolder, int)} instead. <br/><br/>
	 *             <p/>
	 *             Creates an inventory from the given array of itemstacks and name. The inventory will be the same size as the size of the collection
	 */
	public Inventory construct(Collection<ItemStack> items, String name);

	/**
	 * @param size of the inventory to build
	 * @param name of the inventory
	 * @return Inventory
	 * @deprecated Use {@link org.bukkit.Server#createInventory(org.bukkit.inventory.InventoryHolder, int)} instead. <br/><br/>
	 *             <p/>
	 *             Creates an inventory empty inventory with the given size and name
	 */
	public Inventory construct(int size, String name);
}
