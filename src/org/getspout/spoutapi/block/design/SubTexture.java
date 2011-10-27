package org.getspout.spoutapi.block.design;

public class SubTexture {
	Texture parent;
	int xLoc;
	int yLoc;
	
	int xTopLoc;
	int yTopLoc;
	
	/**
	 * Creates a new SubTexture from the parent Texture
	 * 
	 * @param parent texture
	 * @param xLoc left X location
	 * @param yLoc bottom Y location
	 * @param spriteSize width and height of sprite
	 */
	public SubTexture(Texture parent, int xLoc, int yLoc, int spriteSize) {
		this.parent = parent;
		this.xLoc = xLoc;
		this.xTopLoc = xLoc + spriteSize;
		this.yLoc = yLoc;
		this.yTopLoc = yLoc + spriteSize;
	}
	
	/**
	 * Gets the left-sided X of this subtexture
	 * @return xLoc
	 */
	public int getXLoc() {
		return xLoc;
	}
	
	/*
	 * Gets the bottom-sided y of this subtexture
	 * @return yLoc
	 */
	public int getYLoc() {
		return yLoc;
	}
	
	/**
	 * Gets the right-sided x of this subtexture
	 * 
	 * @return xTopLoc
	 */
	public int getXTopLoc() {
		return xTopLoc;
	}
	
	/**
	 * Gets the top-sided y of this subtexture
	 * 
	 * @return yTopLoc
	 */
	public int getYTopLoc() {
		return yTopLoc;
	}
	
	/**
	 * Gets the parent texture of this subtexture
	 * 
	 * @return parent Texture
	 */
	public Texture getParent() {
		return parent;
	}
}