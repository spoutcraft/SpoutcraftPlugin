package org.getspout.spoutapi.material.item;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.Item;

public class GenericItem implements Item {

	private final int id;
	private final int data;
	private final boolean subtypes;
	private final String name;
	private String customName;

	public GenericItem(String name, int id, int data, boolean subtypes) {
		this.name = name;
		this.id = id;
		this.data = data;
		this.subtypes = subtypes;
	}

	public GenericItem(String name, int id, int data) {
		this(name, id, data, false);
	}

	public GenericItem(String name, int id) {
		this(name, id, 0, false);
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
	public boolean hasSubtypes() {
		return subtypes;
	}

	@Override
	public String getName() {
		if(customName != null) {
			return customName;
		}
		return name;
	}
	
	@Override
	public String getNotchianName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.customName = name;
		SpoutManager.getMaterialManager().setItemName(this, name);
	}

}
