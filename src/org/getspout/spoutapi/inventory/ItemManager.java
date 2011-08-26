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
	 * Sets the texture of the item
	 * @param item to texture
	 * @param texture to set
	 */
	public void setItemTexture(Material item, String texture);

	/**
	 * Sets the texture of the item
	 * @param item to texture
	 * @param data of the item
	 * @param texture to set
	 */
	public void setItemTexture(Material item, short data, String texture);

	/**
	 * Gets the custom texture of the item, or null if none exists
	 * @param item to get the texture of
	 * @return texture
	 */
	public String getCustomItemTexture(Material item);

	/**
	 * Gets the custom texture of the item, or null if none exists
	 * @param item to get the texture of
	 * @param data data of the item
	 * @return texture 
	 */
	public String getCustomItemTexture(Material item, short data);

	/**
	 * Resets the texture of the item back to the notchian default
	 * @param item to reset
	 */
	public void resetTexture(Material item);

	/**
	 * Resets the texture of the item back to the notchian default
	 * @param item to reset
	 * @param data of the item
	 */
	public void resetTexture(Material item, short data);
	/**
	 * Resets the names and textures of all items to the notchian defaults. Use with care.
	 */
	public void reset();

}
