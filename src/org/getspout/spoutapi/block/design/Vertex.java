package org.getspout.spoutapi.block.design;

public class Vertex {

	SubTexture texture;
	int number;
	float x;
	float y;
	float z;
	int tx;
	int ty;

	private Vertex(int number, float x, float y, float z) {
		if(number < 1 || number > 4) {
			throw new IllegalArgumentException("Invalid vertex number: " + number);
		}
		this.number = number;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vertex(int number, float x, float y, float z, SubTexture texture) {
		this(number, x, y, z);
		
		this.setSubTexture(texture);
	}

	public Vertex(int number, float x, float y, float z, int tx, int ty) {
		this(number, x, y, z);
		this.tx = tx;
		this.ty = ty;
	}
	
	public Vertex setSubTexture(SubTexture texture) {
		this.texture = texture;
		
		switch (this.number) {
		case 1:
			this.tx = texture.getXLoc();
			this.ty = texture.getYLoc();
			break;
		case 2:
			this.tx = texture.getXLoc();
			this.ty = texture.getYTopLoc();
			break;
		case 3:
			this.tx = texture.getXTopLoc();
			this.ty = texture.getYTopLoc();
			break;
		case 4:
			this.tx = texture.getXTopLoc();
			this.ty = texture.getYLoc();
		}
		
		return this;
	}
}
