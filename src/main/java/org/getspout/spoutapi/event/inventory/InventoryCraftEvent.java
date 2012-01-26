/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
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

import org.getspout.spoutapi.event.EventType;
import org.getspout.spoutapi.event.SpoutEvent;
import org.getspout.spoutapi.inventory.CraftingInventory;

public class InventoryCraftEvent extends InventoryEvent implements SpoutEvent {
	private static final long serialVersionUID = 2252453296883258337L;
	private static final HandlerList handlers = new HandlerList();
	private ItemStack result, cursor;
	private InventorySlotType slotType;
	private int slotNum;
	private ItemStack[][] matrix;
	private int width, height;
	private boolean left;
	private boolean shift;
	private static final EventType type = EventType.Inventory_Craft;

	public InventoryCraftEvent(Player player, CraftingInventory inventory, Location location, InventorySlotType slotType, int slot, ItemStack[][] recipe, ItemStack result, ItemStack cursor, boolean leftClick, boolean shift) {
		super("InventoryCraftEvent", player, inventory, location);
		this.matrix = recipe;
		this.width = recipe.length;
		this.height = recipe[0].length;
		this.result = result;
		this.slotType = slotType;
		this.slotNum = slot;
		this.cursor = cursor;
		this.left = leftClick;
		this.shift = shift;
	}

	/**
	 * Get's the inventory where the crafting is taking place
	 * @return inventory
	 */
	public CraftingInventory getInventory() {
		return (CraftingInventory)this.inventory;
	}

	/**
	 * Get's the width of the inventory crafting area
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get's the height of the inventory crafting area
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get's the recipe at the inventory crafting area
	 * @return recipe
	 */
	public ItemStack[][] getRecipe() {
		return matrix;
	}

	/**
	 * Get's the itemstack at the cursor
	 * @return cursor
	 */
	public ItemStack getCursor() {
		return cursor;
	}

	/**
	 * Set's the itemstack at the cursor
	 * @param cursor to set
	 */
	public void setCursor(ItemStack cursor) {
		this.cursor = cursor;
	}

	/**
	 * Get's the current (new) item at the slot
	 * @return current item
	 */
	public ItemStack getResult() {
		return result;
	}

	/**
	 * Set's the current item at the slot
	 * @param result to set
	 */
	public void setResult(ItemStack result) {
		this.result = result;
	}

	/**
	 * Get's the slot index being interacted with
	 * @return slot index
	 */
	public int getSlot() {
		return slotNum;
	}

	/**
	 * Get's the slot type being interacted with
	 * @return slot type
	 */
	public InventorySlotType getSlotType() {
		return slotType;
	}

	/**
	 * Return's true if the click on the inventory crafting slot was a left click. If false, it was a right click.
	 * @return true if left click
	 */
	public boolean isLeftClick() {
		return left;
	}

	/**
	 * Return's true if the click on the inventory crafting slot was a shift click.
	 * @return true if shift click
	 */
	public boolean isShiftClick() {
		return shift;
	}

	@Override
	public EventType getEventType() {
		return type;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
