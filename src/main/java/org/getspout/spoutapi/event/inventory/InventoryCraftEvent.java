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
package org.getspout.spoutapi.event.inventory;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.inventory.CraftingInventory;

@Deprecated
public class InventoryCraftEvent extends InventoryEvent {
	private static final HandlerList handlers = new HandlerList();
	private ItemStack result, cursor;
	private final int amount;
	private final InventorySlotType slotType;
	private final int slotNum;
	private final ItemStack[][] matrix;
	private final int width, height;
	private final boolean left;
	private final boolean shift;

	public InventoryCraftEvent(Player player, CraftingInventory inventory, Location location, InventorySlotType slotType, int slot, ItemStack[][] recipe, ItemStack result, int amount, ItemStack cursor, boolean leftClick, boolean shift) {
		super("InventoryCraftEvent", player, inventory, location);
		this.matrix = recipe;
		this.width = recipe.length;
		this.height = recipe[0].length;
		this.result = result;
		this.amount = amount;
		this.slotType = slotType;
		this.slotNum = slot;
		this.cursor = cursor;
		this.left = leftClick;
		this.shift = shift;
	}

	/**
	 * Gets the inventory where the crafting is taking place
	 * @return inventory
	 */
	public CraftingInventory getInventory() {
		return (CraftingInventory) this.inventory;
	}

	/**
	 * Gets the width of the inventory crafting area
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the height of the inventory crafting area
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the recipe at the inventory crafting area
	 * @return recipe
	 */
	public ItemStack[][] getRecipe() {
		return matrix;
	}

	/**
	 * Gets the itemstack at the cursor
	 * @return cursor
	 */
	public ItemStack getCursor() {
		return cursor;
	}

	/**
	 * Sets the itemstack at the cursor
	 * @param cursor to set
	 */
	public void setCursor(ItemStack cursor) {
		this.cursor = cursor;
	}

	/**
	 * Gets the current (new) item at the slot
	 * @return current item
	 */
	public ItemStack getResult() {
		return result;
	}

	/**
	 * Gets the amount of new items when shift click was used
	 * @return amount
	 */
	public int getResultAmount() {
		return amount;
	}

	/**
	 * Sets the current item at the slot
	 * @param result to set
	 */
	public void setResult(ItemStack result) {
		this.result = result;
	}

	/**
	 * Gets the slot index being interacted with
	 * @return slot index
	 */
	public int getSlot() {
		return slotNum;
	}

	/**
	 * Gets the slot type being interacted with
	 * @return slot type
	 */
	public InventorySlotType getSlotType() {
		return slotType;
	}

	/**
	 * Returns true if the click on the inventory crafting slot was a left click. If false, it was a right click.
	 * @return true if left click
	 */
	public boolean isLeftClick() {
		return left;
	}

	/**
	 * Returns true if the click on the inventory crafting slot was a shift click.
	 * @return true if shift click
	 */
	public boolean isShiftClick() {
		return shift;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
