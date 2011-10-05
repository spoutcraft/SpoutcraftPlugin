package org.getspout.spoutapi.block.design;

public class Quad {

	SubTexture texture;
	Vertex[] vertexes = new Vertex[4];
	
	public Quad(SubTexture texture, Vertex v1, Vertex v2, Vertex v3, Vertex v4) {
		this.texture = texture;
		vertexes[0] = v1;
		vertexes[1] = v2;
		vertexes[2] = v3;
		vertexes[3] = v4;
	}
	
	public Quad(SubTexture texture) {
		this.texture = texture;
	}
	public Quad addVertex(int number, float x, float y, float z) {
		if(number < 1 || number > 4) {
			throw new IllegalArgumentException("Invalid vertex number: " + number);
		}
		vertexes[number - 1] = new Vertex(number,x,y,z,texture);
		return this;
	}
}
