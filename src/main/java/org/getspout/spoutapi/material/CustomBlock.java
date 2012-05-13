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
package org.getspout.spoutapi.material;

import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.player.SpoutPlayer;

public interface CustomBlock extends Block {
	/**
	 * Gets the BlockDesign associated with this CustomBlock
	 * @return design
	 */
	public BlockDesign getBlockDesign();

	/**
	 * Sets the BlockDesign to use with this CustomBlock
	 * @param design to use
	 * @return this
	 */
	public CustomBlock setBlockDesign(BlockDesign design);
	
	public BlockDesign getBlockDesign(int id);

	public CustomBlock setBlockDesign(BlockDesign design, int id);


	/**
	 * Gets the Custom ID number associated with this CustomBlock
	 * @return customId
	 */
	public int getCustomId();

	/**
	 * Gets the full name of this CustomBlock, which is plugin name + block name
	 * @return fullName
	 */
	public String getFullName();

	/**
	 * Gets the plugin associated with this CustomBlock.
	 * @return plugin
	 */
	public Plugin getPlugin();

	/**
	 * Gets the CustomItem that representts this CustomBlock in the inventory
	 * @return blockItem
	 */
	public CustomItem getBlockItem();

	/**
	 * Gets the Id of the underlying block
	 * @return blockId
	 */
	public int getBlockId();

	/**
	 * Gets the metadata of the underlying block
	 * @return metadata
	 */
	public int getBlockData();

	/**
	 * Sets the ItemStack that drops when breaking this CustomBlock, set to null for no drop
	 * @param item to set
	 * @return this
	 */
	public CustomBlock setItemDrop(ItemStack item);

	/**
	 * Gets the ItemStack that drops when breaking this custom block. Null if no drop.
	 * @return dropped itemstack
	 */
	public SpoutItemStack getItemDrop();
	
	/**
	 * Whether or not this CustomBlock will rotate to face the player when placed.
	 * @return Should the block rotate
	 */
	public boolean canRotate();

	/**
	 * Sets whether or not this CustomBlock will rotate to face the player when placed.
	 * @param rotate Should th eblock rotate
	 * @return this
	 */
	public CustomBlock setRotate(boolean rotate);

	/**
	 * Fires when a neighboring block changes
	 * @param world     the block is in
	 * @param x         location of the block
	 * @param y         location of the block
	 * @param z         location of the block
	 * @param changedId - Id of the changed block
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, int changedId);

	/**
	 * Fires when the block is placed
	 * @param world the block is placed in
	 * @param x     location of the block
	 * @param y     location of the block
	 * @param z     location of the block
	 */
	public void onBlockPlace(World world, int x, int y, int z);

	/**
	 * Fires when the block is placed
	 * @param world  the block is placed in
	 * @param x      location of the block
	 * @param y      location of the block
	 * @param z      location of the block
	 * @param living entity who placed the block
	 */
	public void onBlockPlace(World world, int x, int y, int z, LivingEntity living);

	/**
	 * Fires when the block is destroyed
	 * @param world the block was in
	 * @param x     location of the block
	 * @param y     location of the block
	 * @param z     location of the block
	 */
	public void onBlockDestroyed(World world, int x, int y, int z);

	/**
	 * Fires when the block is destroyed
	 * @param world  the block was in
	 * @param x      location of the block
	 * @param y      location of the block
	 * @param z      location of the block
	 * @param living entity who destroyed the block
	 */
	public void onBlockDestroyed(World world, int x, int y, int z, LivingEntity living);

	/**
	 * Called when the block is interacted with.
	 * <p/>
	 * Return TRUE if you do not want to allow vanilla interactions like placement to happen when you interact with it.
	 * <p/>
	 * Return FALSE if you wish to allow placement of vanilla blocks and usage of items while targetting it.
	 * @param world  the block is in
	 * @param x      location of the block
	 * @param y      location of the block
	 * @param z      location of the block
	 * @param player who interacted with the block
	 * @return true if successful
	 */
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player);

	/**
	 * Called if an entity has moved on this block
	 * @param world  the block is in
	 * @param x      location of the block
	 * @param y      location of the block
	 * @param z      location of the block
	 * @param entity that moved on it
	 */
	public void onEntityMoveAt(World world, int x, int y, int z, Entity entity);

	/**
	 * Called when the block is clicked
	 * @param world  the block is in
	 * @param x      location of the block
	 * @param y      location of the block
	 * @param z      location of the block
	 * @param player who clicked the block
	 */
	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player);

	/**
	 * Called to check if the block is providing redstone power to a face
	 * @param world the block is in
	 * @param x     location of the block
	 * @param y     location of the block
	 * @param z     location of the block
	 * @param face  to check
	 * @return true if the face is powered
	 */
	public boolean isProvidingPowerTo(World world, int x, int y, int z, BlockFace face);

	/**
	 * Called to check if the block is indirectly providing redstone power to a face
	 * @param world the block is in
	 * @param x     location of the block
	 * @param y     location of the block
	 * @param z     location of the block
	 * @param face  to check
	 * @return true if the face is powered
	 */
	public boolean isIndirectlyProvidingPowerTo(World world, int x, int y, int z, BlockFace face);
	
	/**
	 * True if this custom block is capable of providing redstone power to adjacent blocks
	 * @return true if power source
	 */
	public boolean isPowerSource();
}
