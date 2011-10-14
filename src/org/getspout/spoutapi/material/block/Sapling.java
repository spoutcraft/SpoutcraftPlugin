package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.Plant;

public class Sapling extends GenericBlock implements Plant{

	public Sapling(String name, int data) {
		super(name, 6, data);
	}

	@Override
	public boolean isHasGrowthStages() {
		return true;
	}

	@Override
	public int getNumGrowthStages() {
		return 3;
	}

	@Override
	public int getMinimumLightToGrow() {
		return 8;
	}

}
