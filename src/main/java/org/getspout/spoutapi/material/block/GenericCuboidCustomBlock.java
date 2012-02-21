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

import org.getspout.spoutapi.block.design.GenericCuboidBlockDesign;

public class GenericCuboidCustomBlock extends GenericCustomBlock {
	/**
	 * Creates a basic GenericCustomblock with no design that is opaque/solid.
	 *
	 * @param plugin creating the block
	 * @param name of the block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name) {
		super(plugin, name);
	}

	/**
	 * Creates a new cube block material
	 *
	 * @param plugin making the block
	 * @param name of the block
	 * @param isOpaque true if you want the block solid
	 * @param design to use for the block
	 * @param customMetaData of the block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, boolean isOpaque, GenericCuboidBlockDesign design) {
		super(plugin, name, isOpaque, design);
	}

	/**
	 * Creates a new opaque/solid cube block material
	 *
	 * @param plugin making the block
	 * @param name of the block
	 * @param design to use for the block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, GenericCuboidBlockDesign design) {
		this(plugin, name, design.isFullCube(), design);
	}

	/**
	 * Creates a new basic opaque/solid cube block material
	 *
	 * @param plugin making the block
	 * @param name of the block
	 * @param texture url to use for the block - must be a square PNG
	 * @param textureSize width and height of the texture in pixels
	 * @param xMin minimum x coordinate of the cuboid
	 * @param yMin minimum y coordinate of the cuboid
	 * @param zMin minimum z coordinate of the cuboid
	 * @param xMax maximum x coordinate of the cuboid
	 * @param yMax maximum y coordinate of the cuboid
	 * @param zMax maximum z coordinate of the cuboid
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, String texture, int textureSize, float xMin, float yMin, float zMin, float xMax, float yMax, float zMax) {
		this(plugin, name, new GenericCuboidBlockDesign(plugin, texture, textureSize, xMin, yMin, zMin, xMax, yMax, zMax));
	}
	
	
	
	/**
	 * Creates a basic GenericCustomblock with no design that has an underlying block ID.
	 *
	 * @param plugin creating the block
	 * @param name of the block
	 * @param underlying vanilla ID of the block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId) {
		super(plugin, name, int blockId);
	}
	/**
	 * Creates a new cube block material that has an underlying block ID.
	 *
	 * @param plugin making the block
	 * @param name of the block
	 * @param underlying vanilla ID of the block
	 * @param design to use for the block
	 * @param customMetaData of the block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, GenericCuboidBlockDesign design) {
		super(plugin, name, blockId, design);
	}

	/**
	 * Creates a new basic opaque/solid cube block material that has an underlying block ID.
	 *
	 * @param plugin making the block
	 * @param name of the block
	 * @param underlying vanilla ID of the block
	 * @param texture url to use for the block - must be a square PNG
	 * @param textureSize width and height of the texture in pixels
	 * @param xMin minimum x coordinate of the cuboid
	 * @param yMin minimum y coordinate of the cuboid
	 * @param zMin minimum z coordinate of the cuboid
	 * @param xMax maximum x coordinate of the cuboid
	 * @param yMax maximum y coordinate of the cuboid
	 * @param zMax maximum z coordinate of the cuboid
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, String texture, int textureSize, float xMin, float yMin, float zMin, float xMax, float yMax, float zMax) {
		this(plugin, name, blockId, new GenericCuboidBlockDesign(plugin, texture, textureSize, xMin, yMin, zMin, xMax, yMax, zMax));
	}
}
