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
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.material.CustomBlock;

public interface ItemManager {

	public String getStepSound(int id, short data);

	public void setStepSound(int id, short data, String url);

	public void resetStepSound(int id, short data);

	public float getFriction(int id, short data);
	
	public void setFriction(int id, short data, float friction);
	
	public void resetFriction(int id, short data);
	
	public float getHardness(int id, short data);
	
	public void setHardness(int id, short data, float hardness);
	
	public void resetHardness(int id, short data);
	
	public boolean isOpaque(int id, short data);
	
	public void setOpaque(int id, short data, boolean opacity);
	
	public void resetOpacity(int id, short data);
	
	public int getLightLevel(int id, short data);
	
	public void setLightLevel(int id, short data, int level);
	
	public void resetLightLevel(int id, short data);
	
	/**
	 * Gets notchian item name for the item, or the custom name if one overrides it
	 * @param item to get the name of
	 * @return name
	 */
	public String getItemName(Material item);

	/**
	 * Gets notchian item name for the item, or the custom name if one overrides it
	 * @param item to get the name of
	 * @param data for the item
	 * @return name
	 */
	public String getItemName(Material item, short data);
	
	/**
	 * Gets notchian item name for the item, or the custom name if one overrides it
	 * @param item to get the name of
	 * @param data for the item
	 * @return name
	 */
	public String getItemName(int item, short data);
	
	/**
	 * Gets  the custom name of the item, or null if none exists
	 * @param item to get the name of
	 * @return name
	 */
	public String getCustomItemName(Material item);
	
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
	 * @param item to name
	 * @param data of the item
	 * @param name to set
	 */
	public void setItemName(int item, short data, String name);
	
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
	 * Sets the texture of the item.  
	 * 
	 * Please use the version that includes the associated plugin
	 * 
	 * @param item to texture
	 * @param texture to set
	 */
	
	@Deprecated
	public void setItemTexture(Material item, String texture);
	
	 /**
	 * Sets the texture of the item, for use with pre-caching
	 * @param item to texture
	 * @param plugin the plugin to associate with the texture
	 * @param texture to set
	 */
	public void setItemTexture(Material item, Plugin plugin, String texture);

	/**
	 * Sets the texture of the item
	 * 
	 * Please use the version that includes the associated plugin
	 *
	 * @param item to texture
	 * @param data of the item
	 * @param texture to set
	 */
	@Deprecated
	public void setItemTexture(Material item, short data, String texture);
	
	/**
	 * Sets the texture of the item, for use with pre-caching
	 * @param item to texture
	 * @param data of the item
	 * @param plugin the plugin to associate with the texture
	 * @param texture to set
	 */
	public void setItemTexture(Material item, short data, Plugin plugin, String texture);
	
	/**
	 * Sets the texture of a custom item
	 * @param id custom item id
	 * @param plugin the plugin to associate with the texture
	 * @param texture to set
	 */
	public void setCustomItemTexture(int id, Plugin plugin, String texture);

	/**
	 * Gets the custom texture of the item, or null if none exists, for use with pre-caching
	 * @param item to get the texture of
	 * @return texture
	 */

	public String getCustomItemTexture(Material item);
	
	/**
	 * Gets the plugin associated with the custom texture of the item, or null if none exists
	 * @param item to get the texture of
	 * @return texture
	 */
	public String getCustomItemTexturePlugin(Material item);

	/**
	 * Gets the custom texture of the item, or null if none exists
	 * @param item to get the texture of
	 * @param data data of the item
	 * @return texture 
	 */
	public String getCustomItemTexture(Material item, short data);
	
	/**
	 * Gets the plugin associated with the custom texture of the item, or null if none exists
	 * @param item to get the texture of
	 * @param data data of the item
	 * @return texture 
	 */
	public String getCustomItemTexturePlugin(Material item, short data);
	
	/**
	 * Gets the custom texture of the item, or null if none exists
	 * @param id custom item id
	 * @return texture 
	 */
	public String getCustomItemTexture(int id);
	
	/**
	 * Gets the custom texture of the item, or null if none exists
	 * @param id custom item id
	 * @return texture 
	 */
	public String getCustomItemTexturePlugin(int id);

	/**
	 * Resets the texture of the item back to the notchian default
	 * @param item to reset
	 */
	public void resetTexture(Material item);
	
	/**
	 * Resets the texture of a custom item back to the notchian default.  This will reset to the stone texture
	 * @param id custom item id
	 */
	public void resetTexture(int id);

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
	
	/**
	 * Registers the id for a custom item.  The key should be unique.
	 * 
	 * The returned id should be used for accessing the item and is persistent between server restarts and reloads
	 *
	 * @param key Key of the new item
	 * @return the unique id or null on error
	 */
	public int registerCustomItemName(Plugin plugin, String key);
	
	/**
	 * Returns the id for a custom item.  The key should be unique.
	 * 
	 * The returned id should be used for accessing the item and is persistent between server restarts and reloads
	 *
	 * @param key Key of the new item
	 * @return the unique id or null on error
	 */
	public int getCustomItemId(Plugin plugin, String key);
	
	/**
	 * Sets the block id for the block that matches this item.  This block will be placed when this item is used.
	 * 
	 * If the block id is null, no block will be placed and the item will trigger an interaction event instead.
	 *
	 * @param id the custom item id
	 * @param blockId the matched block id
	 * @param metaData the meta data for the block
	 * @return success
	 */
	public void setCustomItemBlock(int id, Integer blockId, Short metaData);
	
	/**
	 * Creates an item stack of a custom item.  The id should be a valid custom item id.
	 *
	 * @param id the custom item id
	 * @param size the size of the item stack
	 * @return an ItemStack of that item
	 */
	public ItemStack getCustomItemStack(int id, int size);
	
	/**
	 * Overrides the block to be the customBlock
	 * 
	 * This can be used to set custom blocks at the location.
	 *
	 * @param block the block to override
	 * @param customBlock the custom block to use instead of the block
	 */
	public boolean overrideBlock(Block block, CustomBlock customBlock);
	
	/**
	 * 
	 * Sets the custom design for a blockId and meta data combination
	 * 
	 * @param blockId the blockId to override
	 * @param metaData the meta data to override
	 * @param design the design to use instead of the block
	 */
	public void setCustomBlockDesign(Integer blockId, Integer metaData, BlockDesign design);
}
