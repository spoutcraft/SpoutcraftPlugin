/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
