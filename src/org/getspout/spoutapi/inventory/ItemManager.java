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

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.CustomItem;

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
	
	public Set<org.getspout.spoutapi.material.Block> getModifiedBlocks();

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
	 * Sets the name of the CustomItem
	 * 
	 * @param item to name
	 * @param name to set
	 */
	public void setItemName(CustomItem item, String name);
	
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
	 * @param id of the item
	 * @param data of the item
	 * @param plugin the plugin to associate with the texture
	 * @param texture to set
	 */
	public void setItemTexture(int id, short data, Plugin plugin, String texture);

	/**
	 * Sets the texture of the item, for use with pre-caching
	 * 
	 * @param item to texture
	 * @param plugin to associate with the texture
	 * @param texture to set
	 */
	public void setItemTexture(CustomItem item, Plugin plugin, String texture);
	
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
	 * Registers the CustomBlock to be placed by the specified CustomItem
	 * 
	 * @param item to use
	 * @param block to place
	 */
	public void setCustomItemBlock(CustomItem item, CustomBlock block);
	
	/**
	 * Creates an item stack of a custom block.
	 *
	 * @param block to make a stack of
	 * @param size the size of the item stack
	 * @return an ItemStack of that item
	 */
	public ItemStack getCustomItemStack(CustomBlock block, int size);
	
	/**
	 * Creates an item stack of a custom item.
	 * 
	 * @param item to make a stack of
	 * @param size of the item stack
	 * @return an ItemStack of that item
	 */
	public ItemStack getCustomItemStack(CustomItem item, int size);
	
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
	 * Overrides the block at x y z to be the customBlock
	 * 
	 * This can be used to set custom blocks at the location.
	 *
	 * @param world the location is in
	 * @param x location
	 * @param y location
	 * @param z location
	 * @param customBlock the custom block to use at the location
	 */
	public boolean overrideBlock(World world, int x, int y, int z, CustomBlock customBlock);
	
	/**
	 * Sets the custom design for a blockId and meta data combination
	 * 
	 * @param blockId the blockId to override
	 * @param metaData the meta data to override
	 * @param design the design to use instead of the block
	 */
	public void setCustomBlockDesign(int blockId, short metaData, BlockDesign design);
	
	/**
	 * Checks if the specified block is a custom block or not
	 * 
	 * @param block to check
	 * @return true if block is custom
	 */
	public boolean isCustomBlock(Block block);

	/**
	 * Gets the SpoutBlock associated with this block
	 * 
	 * @param block to get the SpoutBlock from
	 * @return SpoutBlock
	 */
	public SpoutBlock getSpoutBlock(Block block);
	
	/**
	 * Registers a SpoutRecipe to the server
	 * 
	 * @param recipe to register
	 * @return true if successful
	 */
	public boolean registerSpoutRecipe(Recipe recipe);
	
	/**
	 * Checks if the this item is a custom item or not
	 * 
	 * @param item to check
	 * @return true if it is a custom item
	 */
	public boolean isCustomItem(ItemStack item);
	
	/**
	 * Gets the custom item from an item stack
	 * 
	 * @param item stack to get the custom item from
	 * @return the custom item
	 */
	public CustomItem getCustomItem(ItemStack item);
}
