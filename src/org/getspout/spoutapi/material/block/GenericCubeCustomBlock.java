package org.getspout.spoutapi.material.block;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

public class GenericCubeCustomBlock extends GenericCustomBlock {
	
	public GenericCubeCustomBlock(Plugin plugin, String name, boolean isOpaque, GenericCubeBlockDesign design, int customMetaData) {
		super(plugin, name, isOpaque, design, customMetaData);
	}
}
