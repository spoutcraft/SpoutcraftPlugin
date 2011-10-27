package org.getspout.spoutapi.material;

public interface Plant extends Block{
	
	public boolean isHasGrowthStages();
	
	public int getNumGrowthStages();
		
	public int getMinimumLightToGrow();

}
