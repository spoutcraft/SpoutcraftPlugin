package org.getspout.spoutapi.material;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.inventory.BlockDesign;

public interface CustomBlock extends Block {

	public BlockDesign getBlockDesign();
	
	public CustomBlock setBlockDesign(BlockDesign design);
	
	public int getCustomID();
	
	public String getFullName();
	
	public CustomBlock setCustomMetaData(int customMetaData);

	public int getCustomMetaData();
	
	public Plugin getPlugin();
}
