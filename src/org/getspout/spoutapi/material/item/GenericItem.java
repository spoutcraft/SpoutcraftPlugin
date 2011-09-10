package org.getspout.spoutapi.material.item;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.Item;

public class GenericItem implements Item {

	private final int id;
	private final int data;
	private final boolean subtypes;

	protected GenericItem(int id, int data, boolean subtypes) {
		this.id = id;
		this.data = data;
		this.subtypes = subtypes;
	}

	protected GenericItem(int id, int data) {
		this(id, data, false);
	}

	public GenericItem(int id) {
		this(id, 0, false);
	}

	@Override
	public int getRawId() {
		return id;
	}

	@Override
	public int getRawData() {
		return data;
	}

	@Override
	public boolean isHasSubtypes() {
		return subtypes;
	}

	@Override
	public String getName() {
		return SpoutManager.getItemManager().getItemName(id, (short) data);
	}

	@Override
	public void setName(String name) {
		SpoutManager.getItemManager().setItemName(id, (short) data, name);
	}

}
