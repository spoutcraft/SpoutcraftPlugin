package org.getspout.spoutapi.event.slot;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.gui.Slot;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SlotPutEvent extends SlotEvent {
	private static final HandlerList handlers = new HandlerList();

	public SlotPutEvent(SpoutPlayer player, Slot slot, ItemStack stack, boolean cancel) {
		super(player, slot, stack, cancel);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
