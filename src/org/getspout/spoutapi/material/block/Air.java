package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.Block;
import org.getspout.spoutapi.sound.SoundEffect;

public class Air extends GenericBlock implements Block {

	public Air(String name) {
		super(name, 0);
	}
	
	@Override
	public SoundEffect getStepSound() {
		return null;
	}

	@Override
	public Block setStepSound(SoundEffect sound) {
		return null;
	}

	@Override
	public float getFriction() {
		return 0;
	}

	@Override
	public Block setFriction(float slip) {
		return this;
	}

	@Override
	public float getHardness() {
		return 0;
	}

	@Override
	public Block setHardness(float hardness) {
		return this;
	}

	@Override
	public float getExplosionResistence() {
		return 0;
	}

	@Override
	public Block setExplosionResistence(float resistence) {
		return this;
	}

	@Override
	public boolean isOpaque() {
		return false;
	}

	@Override
	public Block setOpaque(boolean opaque) {
		return this;
	}

	@Override
	public int getLightLevel() {
		return 0;
	}

	@Override
	public Block setLightLevel(int level) {
		return this;
	}

}
