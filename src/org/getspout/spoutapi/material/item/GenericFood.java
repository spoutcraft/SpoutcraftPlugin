package org.getspout.spoutapi.material.item;

import org.getspout.spoutapi.material.Food;

public class GenericFood extends GenericItem implements Food {
	private final int hunger;
	public GenericFood(String name, int id, int hunger) {
		super(name, id);
		this.hunger = hunger;
	}

	@Override
	public int getHungerRestored() {
		return hunger;
	}

}
