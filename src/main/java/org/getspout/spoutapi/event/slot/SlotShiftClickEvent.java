package org.getspout.spoutapi.event.slot;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.gui.Slot;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SlotShiftClickEvent extends SlotEvent {
	private static final HandlerList handlers = new HandlerList();

	public SlotShiftClickEvent(SpoutPlayer player, Slot slot) {
		super(player, slot, null, false);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * This returns null since shift-clicks are independent
	 * of the ItemStack in the cursor.
	 */
	@Override
	public ItemStack getItemStack() {
		return super.getItemStack();
	}
}
