package org.getspout.spoutapi.inventory;

import org.bukkit.inventory.PlayerInventory;

public interface SpoutPlayerInventory extends PlayerInventory, CraftingInventory{
	
	public int getItemInHandSlot();

}
