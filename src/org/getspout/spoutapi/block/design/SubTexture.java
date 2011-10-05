package org.getspout.spoutapi.block.design;

public class SubTexture {
	int xLoc;
	int yLoc;
	
	int xTopLoc;
	int yTopLoc;
	
	public SubTexture(int xLoc, int yLoc, int spriteSize) {
		this.xLoc = xLoc;
		this.xTopLoc = xLoc + spriteSize;
		this.yLoc = yLoc;
		this.yTopLoc = yLoc + spriteSize;
	}
	
	public int getXLoc() {
		return xLoc;
	}
	
	public int getYLoc() {
		return yLoc;
	}
	
	public int getXTopLoc() {
		return xTopLoc;
	}
	
	public int getYTopLoc() {
		return yTopLoc;
	}
}
