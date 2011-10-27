package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.SolidBlock;

public class Wool extends GenericBlock implements SolidBlock {

	public Wool(String name, int id, int data) {
		super(name, id, data);
	}

	@Override
	public boolean isFallingBlock() {
		return false;
	}

}
