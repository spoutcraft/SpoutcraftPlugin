package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.SolidBlock;

public class DoubleSlabs extends GenericBlock implements SolidBlock {

	public DoubleSlabs(int id, int data) {
		super(id, data);
	}

	@Override
	public boolean isFallingBlock() {
		return false;
	}

}
