package org.getspout.spout.block.mcblock;

import net.minecraft.server.Block;

public interface WrappedMCBlock {
	public Block getWrapped();
	
	public void setHardness(float hardness);
}