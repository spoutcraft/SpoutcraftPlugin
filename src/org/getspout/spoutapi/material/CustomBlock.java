package org.getspout.spoutapi.material;

import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.player.SpoutPlayer;

public interface CustomBlock extends Block {

	/**
	 * Gets the BlockDesign associated with this CustomBlock
	 * 
	 * @return design
	 */
	public BlockDesign getBlockDesign();
	
	/**
	 * Sets the BlockDesign to use with this CustomBlock
	 * 
	 * @param design to use
	 * @return this
	 */
	public CustomBlock setBlockDesign(BlockDesign design);
	
	/**
	 * Gets the Custom ID number associated with this CustomBlock
	 * 
	 * @return customId
	 */
	public int getCustomId();
	
	/**
	 * Gets the full name of this CustomBlock, which is plugin name + block name
	 * 
	 * @return fullName
	 */
	public String getFullName();
	
	/**
	 * Sets the custom meta data associated with this CustomBlock. Use this to keep track of extra information on the disk.
	 * 
	 * @param customMetaData to set
	 * @return this
	 */
	public CustomBlock setCustomMetaData(int customMetaData);

	/**
	 * Gets the custom meta data associated with this CustomBlock.
	 * 
	 * @return customMetaData
	 */
	public int getCustomMetaData();
	
	/**
	 * Gets the plugin associated with this CustomBlock.
	 * 
	 * @return plugin
	 */
	public Plugin getPlugin();
	
	/**
	 * Gets the CustomItem that representts this CustomBlock in the inventory
	 * 
	 * @return blockItem
	 */
	public CustomItem getBlockItem();
	
	/**
	 * Gets the Id of the underlying block
	 * 
	 * @return 1 for Stone if opaque, 20 for glass if not opaque
	 */
	public int getBlockId();
	
	/**
	 * Sets the ItemStack that drops when breaking this CustomBlock, set to null for no drop
	 * 
	 * @param item to set
	 * @return this
	 */
	public CustomBlock setItemDrop(ItemStack item);
	
	/**
	 * Fires when a neighboring block changes
	 * 
	 * @param world the block is in
	 * @param x location of the block
	 * @param y location of the block
	 * @param z location of the block
	 * @param changedId - Id of the changed block
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, int changedId);
	
	/**
	 * Fires when the block is placed
	 * 
	 * @param world the block is placed in
	 * @param x location of the block
	 * @param y location of the block
	 * @param z location of the block
	 */
	public void onBlockPlace(World world, int x, int y, int z);
	
	/**
	 * Fires when the block is placed
	 * 
	 * @param world the block is placed in
	 * @param x location of the block
	 * @param y location of the block
	 * @param z location of the block
	 * @param living entity who placed the block
	 */
	public void onBlockPlace(World world, int x, int y, int z, LivingEntity living);
	
	/**
	 * Fires when the block is destroyed
	 * 
	 * @param world the block was in
	 * @param x location of the block
	 * @param y location of the block
	 * @param z location of the block
	 */
	public void onBlockDestroyed(World world, int x, int y, int z);
	
	/**
	 * Called when the block is interacted with
	 * 
	 * @param world the block is in
	 * @param x location of the block
	 * @param y location of the block
	 * @param z location of the block
	 * @param player who interacted with the block
	 * @return true if successful
	 */
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player);
	
	/**
	 * Called if an entity has moved on this block
	 * 
	 * @param world the block is in
	 * @param x location of the block
	 * @param y location of the block
	 * @param z location of the block
	 * @param entity that moved on it
	 */
	public void onEntityMoveAt(World world, int x, int y, int z, Entity entity);
	
	/**
	 * Called when the block is clicked
	 * 
	 * @param world the block is in
	 * @param x location of the block
	 * @param y location of the block
	 * @param z location of the block
	 * @param player who clicked the block
	 */
	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player);
	
	/**
	 * Called to check if the block is providing redstone power to a face
	 * 
	 * @param world the block is in
	 * @param x location of the block
	 * @param y location of the block
	 * @param z location of the block
	 * @param face to check
	 * @return true if the face is powered
	 */
	public boolean isProvidingPowerTo(World world, int x, int y, int z, BlockFace face);
	
	/**
	 * Called to check if the block is indirectly providing redstone power to a face
	 * 
	 * @param world the block is in
	 * @param x location of the block
	 * @param y location of the block
	 * @param z location of the block
	 * @param face to check
	 * @return true if the face is powered
	 */
	public boolean isIndirectlyProdivingPowerTo(World world, int x, int y, int z, BlockFace face); 
}
