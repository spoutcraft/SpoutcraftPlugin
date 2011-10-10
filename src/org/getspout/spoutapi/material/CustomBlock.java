package org.getspout.spoutapi.material;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.BlockDesign;

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
}
