/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
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
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class GenericSlot extends GenericControl implements Slot {
	private ItemStack stack = new ItemStack(0);
	private int depth = 16;

	public WidgetType getType() {
		return WidgetType.Slot;
	}

	public ItemStack getItem() {
		if (stack == null) {
			stack = new ItemStack(0);
		}
		return stack.clone();
	}

	public Slot setItem(ItemStack item) {
		if (item == null || item.getAmount() == 0) {
			stack = new ItemStack(0);
			return this;
		}
		stack = item.clone();
		setDirty(true);
		return this;
	}

	public boolean onItemPut(ItemStack item) {
		return true;
	}

	public boolean onItemTake(ItemStack item) {
		return true;
	}

	public void onItemShiftClicked() {
	}

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
