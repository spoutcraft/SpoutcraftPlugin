package org.getspout.spoutapi.material.block;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

public class GenericCubeCustomBlock extends GenericCustomBlock {
	
	/**
	 * Creates a new cube block material
	 * 
	 * @param plugin making the block
	 * @param name of the block
	 * @param isOpaque set false for transparency
	 * @param design 
	 * @param customMetaData
	 */
	public GenericCubeCustomBlock(Plugin plugin, String name, boolean isOpaque, GenericCubeBlockDesign design, int customMetaData) {
		super(plugin, name, isOpaque, design, customMetaData);
	}
	
	public GenericCubeCustomBlock(Plugin plugin, String name, GenericCubeBlockDesign design) {
		super(plugin, name);
		this.setBlockDesign(design);
	}
	
	public GenericCubeCustomBlock(Plugin plugin, String name, String texture, int textureSize) {
		super(plugin, name);
		this.setBlockDesign(new GenericCubeBlockDesign(plugin, texture, textureSize));
	}
}
