package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.Liquid;

public class GenericLiquid extends GenericBlock implements Liquid{
	private final boolean flowing;
	public GenericLiquid(int id, boolean flowing) {
		super(id);
		this.flowing = flowing;
	}

	@Override
	public boolean isFlowing() {
		return flowing;
	}

}
