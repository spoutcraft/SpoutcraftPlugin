package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.SolidBlock;

public class Slab extends GenericBlock implements SolidBlock{

	public Slab(String name, int id, int data) {
		super(name, id, data);
	}

	@Override
	public boolean isFallingBlock() {
		return false;
	}

}
