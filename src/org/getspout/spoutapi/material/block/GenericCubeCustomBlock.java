package org.getspout.spoutapi.material.block;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.inventory.GenericCubeBlockDesign;

public class GenericCubeCustomBlock extends GenericCustomBlock {
	
	public GenericCubeCustomBlock(Plugin plugin, String name, GenericCubeBlockDesign design, boolean isOpaque, int customMetaData) {
		super(plugin, name, design, isOpaque, customMetaData);
	}
}
