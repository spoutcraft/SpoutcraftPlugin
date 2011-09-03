package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.Plant;

public class Grass extends GenericBlock implements Plant{
	
	public Grass(){
		super(2);
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
		return 9;
	}

}
