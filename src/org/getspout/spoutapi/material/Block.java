package org.getspout.spoutapi.material;

import org.getspout.spoutapi.sound.SoundEffect;

public interface Block extends Material{
	
	public SoundEffect getStepSound();
	
	public Block setStepSound(SoundEffect sound);
	
	public float getFriction();
	
	public Block setFriction(float slip);
	
	public float getHardness();
	
	public Block setHardness(float hardness);

	public boolean isOpaque();
	
	public Block setOpaque(boolean opaque);
	
	public int getLightLevel();
	
	public Block setLightLevel(int level);

}
