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
package org.getspout.spoutapi.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public interface ItemManager {
	
	/**
	 * Gets notchian item name for the item, or the custom name if one overrides it
	 * @param item to get the name of
	 * @return name
	 */
	public String getItemName(Material item);
	
	/**
	 * Gets  the custom name of the item, or null if none exists
	 * @param item to get the name of
	 * @return name
	 */
	public String getCustomItemName(Material item);
	
	/**
	 * Gets notchian item name for the item, or the custom name if one overrides it
	 * @param item to get the name of
	 * @param data for the item
	 * @return name
	 */
	public String getItemName(Material item, short data);
	
	/**
	 * Gets  the custom name of the item, or null if none exists
	 * @param item to get the name of
	 * @param data for the item
	 * @return name
	 */
	public String getCustomItemName(Material item, short data);
	
	/**
	 * Gets  the custom name of the item, or null if none exists
	 * @param id the custom item id
	 * @return name
	 */
	public String getCustomItemName(int id);
	
	/**
	 * Sets the name of the item
	 * @param item to name
	 * @param name to set
	 */
	public void setItemName(Material item, String name);
	
	/**
	 * Sets the name of the item
	 * @param item to name
	 * @param data of the item
	 * @param name to set
	 */
	public void setItemName(Material item, short data, String name);
	
	/**
	 * Sets the name of the item
	 * @param id the custom item id
	 * @param name to set
	 */
	public void setItemName(int id, String name);
	
	/**
	 * Resets the name of the item back to the notchian default
	 * @param item to reset
	 */
	public void resetName(Material item);
	
	/**
	 * Resets the name of the item back to the notchian default
	 * @param item to reset
	 * @param data of the item
	 */
	public void resetName(Material item, short data);
	
	/**
	 * Resets the names of all items to the notchian defaults. Use with care.
	 */
	public void reset();
	
	/**
	 * Registers the key for a custom item.  This key should be unique.
	 * 
	 * The returned id should be used for accessing the item and is persistent between server restarts and reloads
	 *
	 * @param key Key of the new item
	 * @return the unique id or null on error
	 */
	public Integer registerCustomItemName(Plugin plugin, String key);
	
	/**
	 * Sets the block id for the block that matches this item.  This block will be placed when this item is used.
	 * 
	 * If the block id is null, no block will be placed and the item will trigger an interaction event instead.
	 *
	 * @param id the custom item id
	 * @param blockId the matched block id
	 * @return success
	 */
	public void setCustomItemBlock(int id, Integer blockId);
	
	/**
	 * Creates an item stack of a custom item.  The id should be a valid custom item id.
	 *
	 * @param id the custom item id
	 * @param size the size of the item stack
	 * @return an ItemStack of that item
	 */
	public ItemStack getCustomItemStack(int id, int size);

}
