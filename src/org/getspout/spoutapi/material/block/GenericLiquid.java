package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.material.Liquid;

public class GenericLiquid extends GenericBlock implements Liquid{
	private final boolean flowing;
	public GenericLiquid(String name, int id, boolean flowing) {
		super(name, id);
		this.flowing = flowing;
	}

	@Override
	public boolean isFlowing() {
		return flowing;
	}

}
