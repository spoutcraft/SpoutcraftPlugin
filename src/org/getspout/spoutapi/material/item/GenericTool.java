package org.getspout.spoutapi.material.item;

import org.getspout.spoutapi.material.Block;
import org.getspout.spoutapi.material.Tool;

public class GenericTool extends GenericItem implements Tool {

	public GenericTool(String name, int id) {
		super(name, id);
	}

	@Override
	public short getDurability() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tool setDurability(short durability) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public float getStrengthModifier(Block block) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tool setStrengthModifier(Block block, float modifier) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Block[] getStrengthModifiedBlocks() {
		// TODO Auto-generated method stub
		return null;
	}

}
