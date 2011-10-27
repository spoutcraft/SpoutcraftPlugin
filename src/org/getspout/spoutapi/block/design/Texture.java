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
	 * 
	 * This is designed to hold multiple SubTextures, similar to vanilla's terrain.png
	 * In order to use it to get multiple SubTextures, you must specify the total width, total height,
	 * and the spriteSize which is the length of the sides of the squares inside it.
	 * 
	 * The layout for textureId's in order to get SubTextures from this goes from
	 * Left -> Right, then Top -> Bottom, like so:
	 * [0, 1, 2,  3
	 *  4, 5, 6,  7,
	 *  8, 9, 10, 11]
	 *  
	 *  If you are using just a single image, the textureId is always 0.
	 * 
	 * @param plugin associated with this Texture
	 * @param texture url to use. Must be a png
	 * @param width of the texture in pixels
	 * @param height of the texture in pixels
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
	 * 
	 * @param textureId to get
	 * @return the SubTexture
	 */
	public SubTexture getSubTexture(int textureId) {

		return subTextures.get(textureId);
	}

	/**
	 * Gets the texture URL from this Texture
	 * 
	 * @return texture URL
	 */
	public String getTexture() {
		return texture;
	}
	
	/**
	 * Gets the size of sprites in this Texture
	 * 
	 * @return spriteSize
	 */
	public int getSpriteSize() {
		return spriteSize;
	}
	
	/**
	 * Gets the total width of this texture
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the total height of this texture
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the plugin associated with this texture
	 * 
	 * @return plugin
	 */
	public Plugin getPlugin() {
		return plugin;
	}
}
