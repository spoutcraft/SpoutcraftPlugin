package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.SolidBlock;

public class Solid extends GenericBlock implements SolidBlock{
	private final boolean falling;
	public Solid(int id, int data, boolean falling) {
		super(id, data);
		this.falling = falling;
	}
	
	public Solid(int id, boolean falling) {
		super(id, 0);
		this.falling = falling;
	}
	
	public Solid(int id) {
		super(id, 0);
		this.falling = false;
	}
	
	public Solid(int id, int data) {
		super(id, data);
		this.falling = false;
	}

	@Override
	public boolean isFallingBlock() {
		return falling;
	}

}
