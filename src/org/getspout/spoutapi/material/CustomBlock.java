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

	public BlockDesign getBlockDesign();
	
	public CustomBlock setBlockDesign(BlockDesign design);
	
	public int getCustomId();
	
	public String getFullName();
	
	public CustomBlock setCustomMetaData(int customMetaData);

	public int getCustomMetaData();
	
	public Plugin getPlugin();
	
	public CustomItem getBlockItem();
	
	public int getBlockId();
	
	public CustomBlock setItemDrop(ItemStack item);
	
	public void onNeighborBlockChange(World world, int x, int y, int z, int changedId);
	
	public void onBlockPlace(World world, int x, int y, int z);
	
	public void onBlockPlace(World world, int x, int y, int z, LivingEntity living);
	
	public void onBlockDestroyed(World world, int x, int y, int z);
	
	public boolean canPlaceBlockAt(World world, int x, int y, int z, BlockFace face);
	
	public boolean canPlaceBlockAt(World world, int x, int y, int z);
	
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player);
	
	public void onEntityMoveAt(World world, int x, int y, int z, Entity entity);
	
	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player);
		
	public boolean isProvidingPowerTo(World world, int x, int y, int z, BlockFace face);
	
	public boolean isIndirectlyProdivingPowerTo(World world, int x, int y, int z, BlockFace face); 
}
