/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.material.block;

import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.block.design.GenericCubeBlockDesign;

public abstract class GenericCubeCustomBlock extends GenericCuboidCustomBlock {
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
	
	
	/**
	 * Creates a new cube block material with underlying block ID.
	 *
	 * @param plugin making the block
	 * @param name of the block
	 * @param blockID of the underlying vanilla block
	 * @param design to use for the block
	 */
	public GenericCubeCustomBlock(Plugin plugin, String name, int blockId, GenericCubeBlockDesign design) {
		super(plugin, name, blockId, design);
	}

	/**
	 * Creates a new basic opaque/solid cube block material with underlying block ID.
	 *
	 * @param plugin making the block
	 * @param name of the block
	 * @param blockID of the underlying vanilla block
	 * @param texture url to use for the block - must be a square PNG
	 * @param textureSize width and height of the texture in pixels
	 */
	public GenericCubeCustomBlock(Plugin plugin, String name, int blockId, String texture, int textureSize) {
		super(plugin, name, blockId);
		this.setBlockDesign(new GenericCubeBlockDesign(plugin, texture, textureSize));
	}
}
