package org.getspout.spoutapi.block.design;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.design.Texture;

public class GenericCubeBlockDesign extends GenericCuboidBlockDesign {

	/**
	 * Creates a basic cube custom block model
	 * 
	 * @param plugin making this block
	 * @param texture to use
	 * @param textureId[6] Array of faces, give Id's for SubTexture locations
	 * Array is laid out as follows {bottom, face, face, face, face, top}
	 */
	public GenericCubeBlockDesign(Plugin plugin, Texture texture, int[] textureId) {
		super(plugin, texture, textureId, 0F, 0F, 0F, 1F, 1F, 1F);
	}

	/**
	 * Creates a basic cube custom block model with only one texture
	 * 
	 * @param plugin making this block
	 * @param texture url to use
	 * @param textureId to get the SubTexture to use
	 */
	public GenericCubeBlockDesign(Plugin plugin, Texture texture, int textureId) {
		this(plugin, texture, getIdMap(textureId));
	}
	
	/**
	 * Creates a basic cube custom block model with only one texture
	 * 
	 * @param plugin making this block
	 * @param texture url to use - must be a square png
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
