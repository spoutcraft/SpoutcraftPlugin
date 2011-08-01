package org.getspout.spoutapi.inventory;

import java.util.Collection;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface InventoryBuilder {
	
	public Inventory construct(ItemStack[] items, String name);
	
	public Inventory construct(Collection<ItemStack> items, String name);
	
	public Inventory construct(int size, String name);
}
