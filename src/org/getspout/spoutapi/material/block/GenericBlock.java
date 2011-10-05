package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.Block;
import org.getspout.spoutapi.sound.SoundEffect;

public class GenericBlock implements Block{
	private final int id;
	private final int data;
	private final boolean subtypes;
	private SoundEffect stepSound = SoundEffect.STONE;
	
	private GenericBlock(int id, int data, boolean subtypes) {
		this.id = id;
		this.data = data;
		this.subtypes = subtypes;
	}
	
	protected GenericBlock(int id, int data) {
		this(id, data, true);
	}
	
	protected GenericBlock(int id) {
		this(id, 0, false);
	}

	@Override
	public int getRawId() {
		return id;
	}

	@Override
	public int getRawData() {
		return data;
	}

	@Override
	public boolean hasSubtypes() {
		return subtypes;
	}

	@Override
	public String getName() {
		return SpoutManager.getItemManager().getItemName(id, (short)data);
	}

	@Override
	public void setName(String name) {
		SpoutManager.getItemManager().setItemName(id, (short)data, name);
	}

	@Override
	public SoundEffect getStepSound() {
		return stepSound;
	}

	@Override
	public Block setStepSound(SoundEffect sound) {
		stepSound = sound;
		return this;
	}

	@Override
	public float getFriction() {
		return SpoutManager.getItemManager().getFriction(id, (short) data);
	}

	@Override
	public Block setFriction(float friction) {
		SpoutManager.getItemManager().setFriction(id, (short) data, friction);
		return this;
	}

	@Override
	public float getHardness() {
		return SpoutManager.getItemManager().getHardness(id, (short) data);
	}

	@Override
	public Block setHardness(float hardness) {
		SpoutManager.getItemManager().setHardness(id, (short) data, hardness);
		return this;
	}

	@Override
	public float getExplosionResistence() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Block setExplosionResistence(float resistence) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean isOpaque() {
		SpoutManager.getItemManager().isOpaque(id, (short) data);
		return false;
	}

	@Override
	public Block setOpaque(boolean opaque) {
		SpoutManager.getItemManager().setOpaque(id, (short) data, opaque);
		return this;
	}

	@Override
	public int getLightLevel() {
		return SpoutManager.getItemManager().getLightLevel(id, (short) data);
	}

	@Override
	public Block setLightLevel(int level) {
		SpoutManager.getItemManager().setLightLevel(id, (short) data, level);
		return this;
	}
}
