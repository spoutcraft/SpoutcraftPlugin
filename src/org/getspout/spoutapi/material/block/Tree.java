package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.Plant;

public class Tree extends GenericBlock implements Plant{

	public Tree(int id, int data) {
		super(id, data);
	}

	@Override
	public boolean isHasGrowthStages() {
		return false;
	}

	@Override
	public int getNumGrowthStages() {
		return 0;
	}

	@Override
	public int getMinimumLightToGrow() {
		return 0;
	}

}
