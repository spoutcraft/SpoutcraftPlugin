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
package org.getspout.spoutapi.event.inventory;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.inventory.SpoutPlayerInventory;

@Deprecated
public class InventoryClickEvent extends InventoryEvent {
	private static final HandlerList handlers = new HandlerList();
	private final InventorySlotType type;
	private ItemStack item;
	private ItemStack cursor;
	private final int slot;
	private final int convertedSlot;
	private Event.Result result = Event.Result.DEFAULT;
	private boolean leftClick;
	private boolean shift;

	@Deprecated
	public InventoryClickEvent(Player player, Inventory inventory, InventorySlotType type, ItemStack item, ItemStack cursor, int slot, boolean leftClick, boolean shift) {
		super("InventoryClickEvent", player, inventory);
		this.type = type;
		this.item = item;
		this.cursor = cursor;
		this.slot = slot;
		this.convertedSlot = convertSlot(this.slot);
		this.leftClick = leftClick;
		this.shift = shift;
	}

	@Deprecated
	public InventoryClickEvent(Player player, Inventory inventory, InventorySlotType type, ItemStack item, ItemStack cursor, int slot, boolean leftClick, boolean shift, Location location) {
		super("InventoryClickEvent", player, inventory, location);
		this.type = type;
		this.item = item;
		this.cursor = cursor;
		this.slot = slot;
		this.convertedSlot = convertSlot(this.slot);
		this.leftClick = leftClick;
		this.shift = shift;
	}

	@Deprecated
	protected InventoryClickEvent(String name, Player player, Inventory inventory, InventorySlotType type, ItemStack item, ItemStack cursor, int slot, boolean leftClick, boolean shift, Location location) {
		super(name, player, inventory, location);
		this.type = type;
		this.item = item;
		this.cursor = cursor;
		this.slot = slot;
		this.convertedSlot = convertSlot(this.slot);
		this.leftClick = leftClick;
		this.shift = shift;
	}

	@Override
	@Deprecated
	public void setCancelled(boolean cancel) {
		if (cancel) {
			this.result = Event.Result.DENY;
		}
		super.setCancelled(cancel);
	}

	/**
	 * Get's the result of this event.
	 * Default: Allow for Minecraft to handle the inventory click normally
	 * Allow: Allow the inventory click to continue, regardless of the consequences
	 * Deny: Block the inventory click from occuring, reset the inventory state to the pre-click state
	 * @return result
	 */
	@Deprecated
	public Event.Result getResult() {
		return this.result;
	}

	/**
	 * Set's the result of this event.
	 * Default: Allow for Minecraft to handle the inventory click normally
	 * Allow: Allow the inventory click to continue, regardless of the consequences
	 * Deny: Block the inventory click from occuring, reset the inventory state to the pre-click state
	 */
	@Deprecated
	public void setResult(Event.Result result) {
		this.result = result;
		if (result == Event.Result.DENY) {
			setCancelled(true);
		}
	}

	/**
	 * Get's the type of slot that is being interacted with
	 * @return slot type
	 */
	@Deprecated
	public InventorySlotType getSlotType() {
		return this.type;
	}

	/**
	 * Get's the item at the slow being interacted with, or null if empty
	 * @return item
	 */
	@Deprecated
	public ItemStack getItem() {
		return this.item;
	}

	/**
	 * Set's the slot being interacted with. Use null for an empty slot.
	 * Note: The inventory slot can not be changed unless the result has been set to Allow.
	 * @param item to set
	 */
	@Deprecated
	public void setItem(ItemStack item) {
		if (this.result != Event.Result.ALLOW) {
			throw new UnsupportedOperationException("Can not alter stack contents without allowing any result");
		}
		this.item = item;
	}

	/**
	 * Get's the cursor being interacted with, or null if empty.
	 * @return cursor
	 */
	@Deprecated
	public ItemStack getCursor() {
		return this.cursor;
	}

	/**
	 * Set's the cursor being interacted with. Use null for an empty slot.
	 * Note: The cursor can not be changed unless the result has been set to Allow.
	 * @param cursor to set
	 */
	@Deprecated
	public void setCursor(ItemStack cursor) {
		if (this.result != Event.Result.ALLOW) {
			throw new UnsupportedOperationException("Can not alter cursor stack contents without allowing any result");
		}
		this.cursor = cursor;
	}

	/**
	 * Get's the slot index being interacted with
	 * If the slot is -999, the clicked region is outside of the inventory
	 * @return slot index
	 */
	@Deprecated
	public int getSlot() {
		return this.convertedSlot;
	}

	/**
	 * Get's the raw slot index that the packet sent
	 * If the slot is -999, the clicked region is outside of the inventory
	 * @return raw slot
	 */
	@Deprecated
	public int getRawSlot() {
		return this.slot;
	}

	/**
	 * Return's true if the click on the inventory window was a left click. If false, it was a right click.
	 * @return true if left click
	 */
	@Deprecated
	public boolean isLeftClick() {
		return leftClick;
	}

	/**
	 * Return's true if the click on the inventory crafting slow was a shift click.
	 * @return true if shift click
	 */
	@Deprecated
	public boolean isShiftClick() {
		return shift;
	}

	protected int convertSlot(int slot) {
		if (getInventory() instanceof SpoutPlayerInventory) {
			int size = getInventory().getSize();
			//Armour slot
			switch (slot) {
				case 5:
					return 39;
				case 6:
					return 38;
				case 7:
					return 37;
				case 8:
					return 36;
			}
			//Quickslots
			if (slot >= size) {
				slot -= size;
			}

			return slot;
		}
		return slot;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}