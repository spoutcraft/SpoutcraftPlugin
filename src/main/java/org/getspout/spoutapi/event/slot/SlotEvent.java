/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.gui.Slot;
import org.getspout.spoutapi.player.SpoutPlayer;

public abstract class SlotEvent extends Event {
	protected final Slot slot;
	protected final SpoutPlayer player;
	protected final ItemStack stack;
	protected boolean cancel = false;

	protected SlotEvent(SpoutPlayer player, Slot slot, ItemStack stack, boolean cancelled) {
		this.slot = slot;
		this.player = player;
		this.stack = stack;
		cancel = cancelled;
	}

	public Slot getSlot() {
		return slot;
	}

	public SpoutPlayer getPlayer() {
		return player;
	}

	public ItemStack getItemStack() {
		return stack;
	}

	public boolean isCancelled() {
		return cancel;
	}
}
