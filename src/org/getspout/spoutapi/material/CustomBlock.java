package org.getspout.spoutapi.material;

import org.getspout.spoutapi.inventory.BlockDesign;

public interface CustomBlock extends Block {

	public BlockDesign getBlockDesign();
	
	public void setBlockDesign(BlockDesign design);
	
	public int getCustomID();
	
	public String getFullName();
}
