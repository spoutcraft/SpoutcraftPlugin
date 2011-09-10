package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.SolidBlock;

public class Wool extends GenericBlock implements SolidBlock {

	public Wool(int id, int data) {
		super(id, data);
	}

	@Override
	public boolean isFallingBlock() {
		return false;
	}

}
