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

import java.util.Set;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.Material;

public interface MaterialManager {
	public final static String blockIdString = "org.spout.customblocks.blockid";

	public String getStepSound(org.getspout.spoutapi.material.Block block);

	public void setStepSound(org.getspout.spoutapi.material.Block block, String url);

	public void resetStepSound(org.getspout.spoutapi.material.Block block);

	public float getFriction(org.getspout.spoutapi.material.Block block);

	public void setFriction(org.getspout.spoutapi.material.Block block, float friction);

	public void resetFriction(org.getspout.spoutapi.material.Block block);

	public float getHardness(org.getspout.spoutapi.material.Block block);

	public void setHardness(org.getspout.spoutapi.material.Block block, float hardness);

	public void resetHardness(org.getspout.spoutapi.material.Block block);

	public boolean isOpaque(org.getspout.spoutapi.material.Block block);

	public void setOpaque(org.getspout.spoutapi.material.Block block, boolean opacity);

	public void resetOpacity(org.getspout.spoutapi.material.Block block);

	public int getLightLevel(org.getspout.spoutapi.material.Block block);

	public void setLightLevel(org.getspout.spoutapi.material.Block block, int level);

	public void resetLightLevel(org.getspout.spoutapi.material.Block block);

	public Set<org.getspout.spoutapi.material.Block> getModifiedBlocks();

	public void onCustomMaterialRegistered(Material mat);

	/**
	 * Sets the name of the item
	 * @param item to name
	 * @param name to set
	 */
	public void setItemName(Material item, String name);

	/**
	 * Resets the name of the item back to the notchian default
	 * @param item to reset
	 */
	public void resetName(Material item);

	/**
	 * Resets the names and textures of all items to the notchian defaults. Use with care.
	 */
	public void reset();

	/**
	 * Registers the id for a custom item.  The key should be unique.
	 * <p/>
	 * The returned id should be used for accessing the item and is persistent between server restarts and reloads
	 * @param key Key of the new item
	 * @return the unique id or null on error
	 */
	public int registerCustomItemName(Plugin plugin, String key);

	/**
	 * Removes the custom block override from this block
	 * @param block to remove the override
	 * @return true if successful
	 */
	boolean removeBlockOverride(Block block);

	/**
	 * Overrides the block to be the customBlock
	 * <p/>
	 * This can be used to set custom blocks at the location.
	 * @param block       the block to override
	 * @param customBlock the custom block to use instead of the block
	 */
	public boolean overrideBlock(Block block, CustomBlock customBlock);

	/**
	 * Overrides the block at x y z to be the customBlock
	 * <p/>
	 * This can be used to set custom blocks at the location.
	 * @param world       the location is in
	 * @param x           location
	 * @param y           location
	 * @param z           location
	 * @param customBlock the custom block to use at the location
	 */
	public boolean overrideBlock(World world, int x, int y, int z, CustomBlock customBlock);

	public boolean overrideBlock(Block block, CustomBlock customBlock, byte rotation);

	public boolean overrideBlock(World world, int x, int y, int z, CustomBlock customBlock, byte rotation);

	/**
	 * Registers a SpoutRecipe to the server
	 * @param recipe to register
	 * @return true if successful
	 */
	public boolean registerSpoutRecipe(Recipe recipe);
}
