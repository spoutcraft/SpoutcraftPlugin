package org.getspout.spoutapi.block.design;

import java.util.ArrayList;
import java.util.List;

public class Texture {

	public String texture;
	public int wholeSize;
	public int spriteSize;

	public List<SubTexture> subTextures;

	public Texture(String texture, int wholeSize, int spriteSize) {
		this.texture = texture;
		this.wholeSize = wholeSize;
		this.spriteSize = spriteSize;

		int amount = (wholeSize / spriteSize) ^ 2;
		System.out.println(amount);

		subTextures = new ArrayList<SubTexture>(amount);

		int count = 0;
		for (int y = (wholeSize / spriteSize) - 1; y >= 0; y--) {
			for (int x = 0; x < wholeSize / spriteSize; x++) {
				subTextures.add(count, new SubTexture(x * spriteSize, y * spriteSize, spriteSize));
				count++;
			}
		}
	}

	public SubTexture getSubTexture(int textureId) {

		return subTextures.get(textureId);
	}

	public String getTexture() {
		return texture;
	}
	
	public int getSpriteSize() {
		return spriteSize;
	}
	
	public int getSize() {
		return wholeSize;
	}
}
