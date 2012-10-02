/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
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
	 * @param plugin creating the block
	 * @param name   of the block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name) {
		super(plugin, name);
	}

	/**
	 * Creates a basic GenericCustomblock with no design that is opaque/solid.
	 * @param plugin creating the block
	 * @param name   of the block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, boolean rotate) {
		super(plugin, name);
		setRotate(rotate);
	}

	/**
	 * Creates a new cube block material
	 * @param plugin         making the block
	 * @param name           of the block
	 * @param isOpaque       true if you want the block solid
	 * @param design         to use for the block
	 * @param customMetaData of the block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, boolean isOpaque, GenericCuboidBlockDesign design) {
		super(plugin, name, isOpaque, design);
	}

	/**
	 * Creates a new cube block material
	 * @param plugin         making the block
	 * @param name           of the block
	 * @param isOpaque       true if you want the block solid
	 * @param design         to use for the block
	 * @param customMetaData of the block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, boolean isOpaque, GenericCuboidBlockDesign design, boolean rotate) {
		super(plugin, name, isOpaque, design, rotate);
	}

	/**
	 * Creates a new opaque/solid cube block material
	 * @param plugin making the block
	 * @param name   of the block
	 * @param design to use for the block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, GenericCuboidBlockDesign design) {
		this(plugin, name, design.isFullCube(), design);
	}

	/**
	 * Creates a new opaque/solid cube block material
	 * @param plugin making the block
	 * @param name   of the block
	 * @param design to use for the block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, GenericCuboidBlockDesign design,  boolean rotate) {
		this(plugin, name, design.isFullCube(), design, rotate);
	}

	/**
	 * Creates a new basic opaque/solid cube block material
	 * @param plugin      making the block
	 * @param name        of the block
	 * @param texture     url to use for the block - must be a square PNG
	 * @param textureSize width and height of the texture in pixels
	 * @param xMin        minimum x coordinate of the cuboid
	 * @param yMin        minimum y coordinate of the cuboid
	 * @param zMin        minimum z coordinate of the cuboid
	 * @param xMax        maximum x coordinate of the cuboid
	 * @param yMax        maximum y coordinate of the cuboid
	 * @param zMax        maximum z coordinate of the cuboid
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, String texture, int textureSize, float xMin, float yMin, float zMin, float xMax, float yMax, float zMax) {
		this(plugin, name, new GenericCuboidBlockDesign(plugin, texture, textureSize, xMin, yMin, zMin, xMax, yMax, zMax));
	}

	/**
	 * Creates a new basic opaque/solid cube block material
	 * @param plugin      making the block
	 * @param name        of the block
	 * @param texture     url to use for the block - must be a square PNG
	 * @param textureSize width and height of the texture in pixels
	 * @param xMin        minimum x coordinate of the cuboid
	 * @param yMin        minimum y coordinate of the cuboid
	 * @param zMin        minimum z coordinate of the cuboid
	 * @param xMax        maximum x coordinate of the cuboid
	 * @param yMax        maximum y coordinate of the cuboid
	 * @param zMax        maximum z coordinate of the cuboid
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, String texture, int textureSize, float xMin, float yMin, float zMin, float xMax, float yMax, float zMax, boolean rotate) {
		this(plugin, name, new GenericCuboidBlockDesign(plugin, texture, textureSize, xMin, yMin, zMin, xMax, yMax, zMax), rotate);
	}

	/**
	 * Creates a basic GenericCustomblock with no design that has an underlying block ID.
	 * @param plugin  creating the block
	 * @param name    of the block
	 * @param blockID of the underlying vanilla block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId) {
		super(plugin, name, blockId);
	}

	/**
	 * Creates a basic GenericCustomblock with no design that has an underlying block ID.
	 * @param plugin  creating the block
	 * @param name    of the block
	 * @param blockID of the underlying vanilla block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, boolean rotate) {
		super(plugin, name, blockId, rotate);
	}

	/**
	 * Creates a basic GenericCustomblock with no design that has an underlying block ID + metadata.
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param blockID  of the underlying vanilla block
	 * @param metadata of the underlying vanilla block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, int metadata) {
		super(plugin, name, blockId, metadata);
	}

	/**
	 * Creates a basic GenericCustomblock with no design that has an underlying block ID + metadata.
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param blockID  of the underlying vanilla block
	 * @param metadata of the underlying vanilla block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, int metadata, boolean rotate) {
		super(plugin, name, blockId, metadata, rotate);
	}

	/**
	 * Creates a new cube block material that has an underlying block ID.
	 * @param plugin  making the block
	 * @param name    of the block
	 * @param blockID of the underlying vanilla block
	 * @param design  to use for the block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, GenericCuboidBlockDesign design) {
		super(plugin, name, blockId, design);
	}

	/**
	 * Creates a new cube block material that has an underlying block ID.
	 * @param plugin  making the block
	 * @param name    of the block
	 * @param blockID of the underlying vanilla block
	 * @param design  to use for the block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, GenericCuboidBlockDesign design, boolean rotate) {
		super(plugin, name, blockId, design, rotate);
	}

	/**
	 * Creates a new cube block material that has an underlying block ID + metadata.
	 * @param plugin   making the block
	 * @param name     of the block
	 * @param blockID  of the underlying vanilla block
	 * @param metadata of the underlying vanilla block
	 * @param design   to use for the block
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, int metadata, GenericCuboidBlockDesign design) {
		super(plugin, name, blockId, metadata, design);
	}

	/**
	 * Creates a new cube block material that has an underlying block ID + metadata.
	 * @param plugin   making the block
	 * @param name     of the block
	 * @param blockID  of the underlying vanilla block
	 * @param metadata of the underlying vanilla block
	 * @param design   to use for the block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, int metadata, GenericCuboidBlockDesign design, boolean rotate) {
		super(plugin, name, blockId, metadata, design, rotate);
	}

	/**
	 * Creates a new basic opaque/solid cube block material that has an underlying block ID.
	 * @param plugin      making the block
	 * @param name        of the block
	 * @param blockID     of the underlying vanilla block
	 * @param texture     url to use for the block - must be a square PNG
	 * @param textureSize width and height of the texture in pixels
	 * @param xMin        minimum x coordinate of the cuboid
	 * @param yMin        minimum y coordinate of the cuboid
	 * @param zMin        minimum z coordinate of the cuboid
	 * @param xMax        maximum x coordinate of the cuboid
	 * @param yMax        maximum y coordinate of the cuboid
	 * @param zMax        maximum z coordinate of the cuboid
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, String texture, int textureSize, float xMin, float yMin, float zMin, float xMax, float yMax, float zMax) {
		this(plugin, name, blockId, new GenericCuboidBlockDesign(plugin, texture, textureSize, xMin, yMin, zMin, xMax, yMax, zMax));
	}

	/**
	 * Creates a new basic opaque/solid cube block material that has an underlying block ID.
	 * @param plugin      making the block
	 * @param name        of the block
	 * @param blockID     of the underlying vanilla block
	 * @param texture     url to use for the block - must be a square PNG
	 * @param textureSize width and height of the texture in pixels
	 * @param xMin        minimum x coordinate of the cuboid
	 * @param yMin        minimum y coordinate of the cuboid
	 * @param zMin        minimum z coordinate of the cuboid
	 * @param xMax        maximum x coordinate of the cuboid
	 * @param yMax        maximum y coordinate of the cuboid
	 * @param zMax        maximum z coordinate of the cuboid
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, String texture, int textureSize, float xMin, float yMin, float zMin, float xMax, float yMax, float zMax, boolean rotate) {
		this(plugin, name, blockId, new GenericCuboidBlockDesign(plugin, texture, textureSize, xMin, yMin, zMin, xMax, yMax, zMax), rotate);
	}

	/**
	 * Creates a new basic opaque/solid cube block material that has an underlying block ID + metadata.
	 * @param plugin      making the block
	 * @param name        of the block
	 * @param blockID     of the underlying vanilla block
	 * @param metadata    of the underlying vanilla block
	 * @param texture     url to use for the block - must be a square PNG
	 * @param textureSize width and height of the texture in pixels
	 * @param xMin        minimum x coordinate of the cuboid
	 * @param yMin        minimum y coordinate of the cuboid
	 * @param zMin        minimum z coordinate of the cuboid
	 * @param xMax        maximum x coordinate of the cuboid
	 * @param yMax        maximum y coordinate of the cuboid
	 * @param zMax        maximum z coordinate of the cuboid
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, int metadata, String texture, int textureSize, float xMin, float yMin, float zMin, float xMax, float yMax, float zMax) {
		this(plugin, name, blockId, metadata, new GenericCuboidBlockDesign(plugin, texture, textureSize, xMin, yMin, zMin, xMax, yMax, zMax));
	}

	/**
	 * Creates a new basic opaque/solid cube block material that has an underlying block ID + metadata.
	 * @param plugin      making the block
	 * @param name        of the block
	 * @param blockID     of the underlying vanilla block
	 * @param metadata    of the underlying vanilla block
	 * @param texture     url to use for the block - must be a square PNG
	 * @param textureSize width and height of the texture in pixels
	 * @param xMin        minimum x coordinate of the cuboid
	 * @param yMin        minimum y coordinate of the cuboid
	 * @param zMin        minimum z coordinate of the cuboid
	 * @param xMax        maximum x coordinate of the cuboid
	 * @param yMax        maximum y coordinate of the cuboid
	 * @param zMax        maximum z coordinate of the cuboid
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCuboidCustomBlock(Plugin plugin, String name, int blockId, int metadata, String texture, int textureSize, float xMin, float yMin, float zMin, float xMax, float yMax, float zMax, boolean rotate) {
		this(plugin, name, blockId, metadata, new GenericCuboidBlockDesign(plugin, texture, textureSize, xMin, yMin, zMin, xMax, yMax, zMax), rotate);
	}
}
