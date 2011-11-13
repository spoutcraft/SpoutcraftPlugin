package org.getspout.spoutapi.material.block;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

public abstract class GenericCubeCustomBlock extends GenericCustomBlock {
	
	/**
	 * Creates a new cube block material
	 * 
	 * @param plugin making the block
	 * @param name of the block
	 * @param isOpaque true if you want the block solid
	 * @param design to use for the block
	 * @param customMetaData of the block
	 */
	public GenericCubeCustomBlock(Plugin plugin, String name, boolean isOpaque, GenericCubeBlockDesign design) {
		super(plugin, name, isOpaque, design);
	}
	
	/**
	 * Creates a new opaque/solid cube block material
	 * 
	 * @param plugin making the block
	 * @param name of the block
	 * @param design to use for the block
	 */
	public GenericCubeCustomBlock(Plugin plugin, String name, GenericCubeBlockDesign design) {
		super(plugin, name);
		this.setBlockDesign(design);
	}
	
	/**
	 * Creates a new basic opaque/solid cube block material
	 * 
	 * @param plugin making the block
	 * @param name of the block
	 * @param texture url to use for the block - must be a square PNG
	 * @param textureSize width and height of the texture in pixels
	 */
	public GenericCubeCustomBlock(Plugin plugin, String name, String texture, int textureSize) {
		super(plugin, name);
		this.setBlockDesign(new GenericCubeBlockDesign(plugin, texture, textureSize));
	}
}
