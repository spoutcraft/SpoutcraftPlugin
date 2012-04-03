package org.getspout.spoutapi.event.slot;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.gui.Slot;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SlotExchangeEvent extends SlotEvent {

	private static final HandlerList handlers = new HandlerList();
	
	private final ItemStack cursor;

	public SlotExchangeEvent(SpoutPlayer player, Slot slot, ItemStack current, ItemStack cursor, boolean cancel) {
		super(player, slot, current, cancel);
		this.cursor = cursor;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	/**
	 * This gets the ItemStack currently in the Slot.
	 */
	@Override
	public ItemStack getItemStack() {
		return super.getItemStack();
	}
	
	public ItemStack getCursorItemStack() {
		return cursor;
	}

}
