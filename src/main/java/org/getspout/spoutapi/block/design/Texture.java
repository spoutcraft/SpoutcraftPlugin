/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.SpoutManager;

public class Texture {
	public String texture;
	public Plugin plugin;
	public int width;
	public int height;
	public int spriteSize;
	public List<SubTexture> subTextures;

	/**
	 * Creates a new Texture for use with BlockDesigns.
	 * <p/>
	 * This is designed to hold multiple SubTextures, similar to vanilla's terrain.png
	 * In order to use it to get multiple SubTextures, you must specify the total width, total height,
	 * and the spriteSize which is the length of the sides of the squares inside it.
	 * <p/>
	 * The image itself used for this Texture MUST be a power of 2 on each side.
	 * 16, 32, 64, 128, 256, 512, etc.
	 * For example, a png that is 18x20 will get loaded by OpenGL as 32x32, thus skewing your SubTextures
	 * <p/>
	 * The layout for textureIds in order to get SubTextures from this goes from
	 * Left -> Right, then Top -> Bottom, like so:
	 * [0, 1, 2,  3
	 * 4, 5, 6,  7,
	 * 8, 9, 10, 11]
	 * <p/>
	 * If you are using just a single image, the textureId is always 0.
	 * @param plugin     associated with this Texture
	 * @param texture    url to use. Must be a png
	 * @param width      of the texture in pixels
	 * @param height     of the texture in pixels
	 * @param spriteSize width and height of the sprites inside the texture
	 */
	public Texture(Plugin plugin, String texture, int width, int height, int spriteSize) {
		this.texture = texture;
		this.plugin = plugin;
		SpoutManager.getFileManager().addToCache(plugin, texture);
		this.width = width;
		this.height = height;
		this.spriteSize = spriteSize;

		int amount = (width / spriteSize) * (height / spriteSize);

		subTextures = new ArrayList<SubTexture>(amount);

		int count = 0;
		for (int y = (height / spriteSize) - 1; y >= 0; y--) {
			for (int x = 0; x < width / spriteSize; x++) {
				subTextures.add(count, new SubTexture(this, x * spriteSize, y * spriteSize, spriteSize));
				count++;
			}
		}
	}

	/**
	 * Gets a SubTexture from this texture
	 * @param textureId to get
	 * @return the SubTexture
	 */
	public SubTexture getSubTexture(int textureId) {
		return subTextures.get(textureId);
	}

	/**
	 * Gets the texture URL from this Texture
	 * @return texture URL
	 */
	public String getTexture() {
		return texture;
	}

	/**
	 * Gets the size of sprites in this Texture
	 * @return spriteSize
	 */
	public int getSpriteSize() {
		return spriteSize;
	}

	/**
	 * Gets the total width of this texture
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the total height of this texture
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the plugin associated with this texture
	 * @return plugin
	 */
	public Plugin getPlugin() {
		return plugin;
	}
}
