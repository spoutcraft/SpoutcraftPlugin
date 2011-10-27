package org.getspout.spoutapi.material.item;

import org.getspout.spoutapi.material.Tool;

public class GenericTool extends GenericItem implements Tool {

	public GenericTool(String name, int id) {
		super(name, id);
	}

	@Override
	public int getToolPower() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setToolPower() {
		// TODO Auto-generated method stub
		
	}

}
