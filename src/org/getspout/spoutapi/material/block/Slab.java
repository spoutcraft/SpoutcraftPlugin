package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.SolidBlock;

public class Slab extends GenericBlock implements SolidBlock{

	public Slab(int id, int data) {
		super(id, data);
	}

	@Override
	public boolean isFallingBlock() {
		return false;
	}

}
