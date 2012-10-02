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
package org.getspout.spoutapi.block.design;

import org.bukkit.plugin.Plugin;

public class GenericCubeBlockDesign extends GenericCuboidBlockDesign {
	/**
	 * Creates a basic cube custom block model
	 * @param plugin       making this block
	 * @param texture      to use
	 * @param textureId[6] Array of faces, give IDs for SubTexture locations
	 *                     Array is laid out as follows {bottom, face, face, face, face, top}
	 */
	public GenericCubeBlockDesign(Plugin plugin, Texture texture, int[] textureId) {
		super(plugin, texture, textureId, 0F, 0F, 0F, 1F, 1F, 1F);
	}

	/**
	 * Creates a basic cube custom block model with only one texture
	 * @param plugin    making this block
	 * @param texture   url to use
	 * @param textureId to get the SubTexture to use
	 */
	public GenericCubeBlockDesign(Plugin plugin, Texture texture, int textureId) {
		this(plugin, texture, getIdMap(textureId));
	}

	/**
	 * Creates a basic cube custom block model with only one texture
	 * @param plugin      making this block
	 * @param texture     url to use - must be a square png
	 * @param textureSize size of the width/height of the texture in pixels
	 */
	public GenericCubeBlockDesign(Plugin plugin, String texture, int textureSize) {
		this(plugin, new Texture(plugin, texture, textureSize, textureSize, textureSize), 0);
	}

	private static int[] getIdMap(int textureId) {
		int[] idMap = new int[6];
		for (int i = 0; i < 6; i++) {
			idMap[i] = textureId;
		}
		return idMap;
	}
}
