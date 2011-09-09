package org.getspout.spoutapi.inventory;

public class SpoutStandardBlockDesign extends SpoutCustomBlockDesign {
	
	private int[] faces;
	private int maxFace;
	
	public SpoutStandardBlockDesign(String texturePlugin, String textureURL, int textureSize, int[] faces) {
		lowXBound = 0;
		lowYBound = 0;
		lowZBound = 0;
		highXBound = 1;
		highYBound = 1;
		highZBound = 1;
		this.textureURL = textureURL;
		this.texturePlugin = texturePlugin;
		this.renderPass = 0;
		this.faces = faces;
		createCube(textureSize);
	}
	
	private void createCube(int textureSize) {
		
		setMaxFace();
		
		int t = faceSize(faces, textureSize);
		super.setQuadNumber(6);
		
		setVertexOffset(0, 0, 0, 0, 0, 0, 0, 0, faces, textureSize);
		setVertexOffset(0, 0, 1, 1, 0, 0, t, 0, faces, textureSize);
		setVertexOffset(0, 0, 2, 1, 0, 1, t, t, faces, textureSize);
		setVertexOffset(0, 0, 3, 0, 0, 1, 0, t, faces, textureSize);
		
		setVertexOffset(1, 1, 0, 0, 0, 0, t, 0, faces, textureSize);
		setVertexOffset(1, 1, 1, 0, 1, 0, t, t, faces, textureSize);
		setVertexOffset(1, 1, 2, 1, 1, 0, 0, t, faces, textureSize);
		setVertexOffset(1, 1, 3, 1, 0, 0, 0, 0, faces, textureSize);
		
		setVertexOffset(2, 2, 0, 1, 0, 0, t, 0, faces, textureSize);
		setVertexOffset(2, 2, 1, 1, 1, 0, t, t, faces, textureSize);
		setVertexOffset(2, 2, 2, 1, 1, 1, 0, t, faces, textureSize);
		setVertexOffset(2, 2, 3, 1, 0, 1, 0, 0, faces, textureSize);
		
		setVertexOffset(3, 3, 0, 1, 0, 1, t, 0, faces, textureSize);
		setVertexOffset(3, 3, 1, 1, 1, 1, t, t, faces, textureSize);
		setVertexOffset(3, 3, 2, 0, 1, 1, 0, t, faces, textureSize);
		setVertexOffset(3, 3, 3, 0, 0, 1, 0, 0, faces, textureSize);
		
		setVertexOffset(4, 4, 0, 0, 0, 1, t, 0, faces, textureSize);
		setVertexOffset(4, 4, 1, 0, 1, 1, t, t, faces, textureSize);
		setVertexOffset(4, 4, 2, 0, 1, 0, 0, t, faces, textureSize);
		setVertexOffset(4, 4, 3, 0, 0, 0, 0, 0, faces, textureSize);
		
		setVertexOffset(5, 5, 0, 0, 1, 0, 0, 0, faces, textureSize);
		setVertexOffset(5, 5, 1, 0, 1, 1, t, 0, faces, textureSize);
		setVertexOffset(5, 5, 2, 1, 1, 1, t, t, faces, textureSize);
		setVertexOffset(5, 5, 3, 1, 1, 0, 0, t, faces, textureSize);
	
	}
	
	private void setVertexOffset(int quadNumber, int faceNumber, int vertexNumber, float x, float y, float z, int tx, int ty, int[] faces, int textureSize) {
		int[] texturePosition = new int[2];
		int faceSize;
		
		faceSize = faceSize(faces, textureSize);
		texturePosition = facePosition(texturePosition, faceNumber, faces, faceSize);
		
		super.setVertex(quadNumber, vertexNumber, x, y, z, tx + texturePosition[0], ty + texturePosition[1], textureSize, textureSize);
	}
	
	private int faceSize(int[] faces, int textureSize) {
		if (maxFace <= 1) {
			return textureSize;
		} else if (maxFace <= 4) {
			return textureSize / 2;
		} else {
			return textureSize / 4;
		}
	}
	
	private int[] facePosition(int[] buffer, int faceNumber, int[] faces, int faceSize) {
		if (faces == null) {
			buffer[0] = 0;
			buffer[1] = 0;
			return buffer;
		}
		int face = faces[faceNumber];
		
		if (maxFace <= 1) {
			buffer[0] = 0;
			buffer[1] = 0;
		} else if (maxFace <= 4) {
			buffer[0] = (face % 2) * faceSize;
			buffer[1] = (face / 2) * faceSize;
		} else {
			buffer[0] = (face % 4) * faceSize;
			buffer[1] = (face / 4) * faceSize;
		}
		return buffer;
	}

	private void setMaxFace() {
		maxFace = 0;
		if (faces != null) {
			for (int face : faces) {
				if (face > maxFace) {
					maxFace = face;
				}
			}
		}
	}
	
}
