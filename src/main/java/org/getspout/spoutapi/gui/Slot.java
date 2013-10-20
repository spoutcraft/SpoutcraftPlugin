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
package org.getspout.spoutapi.gui;

import java.io.IOException;
import java.util.Map.Entry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * Represents one item slot where items can be put in and out.
 */
public class Slot extends Control {
	private ItemStack stack = new ItemStack(0);
	private int depth = 16;

	@Override
	public WidgetType getType() {
		return WidgetType.Slot;
	}

	/**
	 * Gets the item of this slot
	 * @return the item of the slot
	 */
	public ItemStack getItem() {
		if (stack == null) {
			stack = new ItemStack(0);
		}
		return stack.clone();
	}

	/**
	 * Sets a new item to this slot
	 * @param item the new item
	 * @return the instance
	 */
	public Slot setItem(ItemStack item) {
		if (item == null || item.getAmount() == 0) {
			stack = new ItemStack(0);
			return this;
		}
		stack = item.clone();
		setDirty(true);
		return this;
	}

	/**
	 * Called when the user puts an item into the slot.
	 * @param item the stack that the user wants to put in.
	 *             The amount property in the stack will be calculated correctly, for example when the user right-clicks on the slot, it will only give one item.
	 * @return false if you want to cancel that.
	 */
	public boolean onItemPut(ItemStack item) {
		return true;
	}

	/**
	 * Called when the user takes the item out of the slot
	 * @param item the stack that the user will get.
	 *             The amount property in the stack will be calculated correctly,for example when the user right-clicks on the slot, it would split the amount.
	 * @return false if you want to cancel that.
	 */
	public boolean onItemTake(ItemStack item) {
		return true;
	}

	/**
	 * Called when the user shift-clicks on the slot.
	 * If the user holds shift while left-clicking, no other actions will be done.
	 */
	public void onItemShiftClicked() {
	}

	/**
	 * Called when the user wants to exchange the item on his cursor with that in the slot.
	 * @param current the item that is currently in the slot
	 * @param cursor  the item that is on the cursor
	 * @return false, if you want to cancel the exchange
	 */
	public boolean onItemExchange(ItemStack current, ItemStack cursor) {
		return true;
	}

	public int getDepth() {
		return depth;
	}

	public Slot setDepth(int depth) {
		this.depth = depth;
		setDirty(true);
		return this;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		stack.setTypeId(input.readInt());
		stack.setAmount((int) input.readShort());
		stack.setDurability(input.readShort());
		depth = input.readInt();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(stack.getTypeId());
		output.writeShort((short) stack.getAmount());
		output.writeShort(stack.getDurability());
		output.writeInt(depth);

		if (stack.hasItemMeta() && stack.getItemMeta().hasDisplayName()) {
			output.writeBoolean(true);
			output.writeString(stack.getItemMeta().getDisplayName());
		} else {
			output.writeBoolean(false);
		}

		if (stack.hasItemMeta() && stack.getItemMeta().hasLore()) {
			output.writeBoolean(true);
			output.writeInt(stack.getItemMeta().getLore().size());
			for (String l : stack.getItemMeta().getLore()) {
				output.writeString(l);
			}
		} else {
			output.writeBoolean(false);
		}

		if (stack.hasItemMeta() && stack.getItemMeta().hasEnchants()) {
			output.writeBoolean(true);
			output.writeInt(stack.getItemMeta().getEnchants().size());
			for (Entry e : stack.getItemMeta().getEnchants().entrySet()) {
				output.writeInt(((Enchantment)e.getKey()).getId());
				output.writeInt(((Integer)e.getValue()).intValue());
			}
		} else {
			output.writeBoolean(false);
		}
	}
}
