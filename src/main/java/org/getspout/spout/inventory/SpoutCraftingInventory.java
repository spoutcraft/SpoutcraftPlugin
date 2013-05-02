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
package org.getspout.spout.inventory;

import java.util.Arrays;

import net.minecraft.server.v1_5_R3.IInventory;
import net.minecraft.server.v1_5_R3.InventoryCrafting;

import org.bukkit.craftbukkit.v1_5_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.inventory.CraftingInventory;

public class SpoutCraftingInventory extends SpoutCraftInventory implements CraftingInventory {
	protected IInventory result;
	public SpoutCraftingInventory(InventoryCrafting inventory, IInventory result) {
		super(inventory);
		this.result = result;
	}

	public InventoryCrafting getMatrixHandle() {
		return (InventoryCrafting)getInventory();
	}

	public IInventory getResultHandle() {
		return this.result;
	}

	@Override
	public int getSize() {
		return this.result.getSize() + this.inventory.getSize();
	}

	@Override
	public void setContents(ItemStack[] items) {
		int resultLen = this.result.getContents().length;
		int len = this.inventory.getContents().length + resultLen;
		if (len != items.length) {
			throw new IllegalArgumentException("Invalid inventory size; expected " + len);
		}
		setContents(items[0], Arrays.copyOfRange(items, 1, items.length));
	}

	@Override
	public CraftItemStack[] getContents() {
		CraftItemStack[] items = new CraftItemStack[getSize()];
		net.minecraft.server.v1_5_R3.ItemStack[] mcResultItems = this.result.getContents();

		int i = 0;
		for (i = 0; i < mcResultItems.length; i++ ) {
			items[i] = CraftItemStack.asCraftMirror(mcResultItems[i]);
		}

		net.minecraft.server.v1_5_R3.ItemStack[] mcItems = this.inventory.getContents();

		for (int j = 0; j < mcItems.length; j++) {
			items[i + j] = CraftItemStack.asCraftMirror(mcItems[j]);
		}

		return items;
	}

	public void setContents(ItemStack result, ItemStack[] contents) {
		setResult(result);
		setMatrix(contents);
	}

	@Override
	public CraftItemStack getItem(int index) {
		if (index == 0) {
			if (this.result.getItem(index) != null) {
				return CraftItemStack.asCraftMirror(this.result.getItem(index));
			}
			return CraftItemStack.asCraftCopy(new ItemStack(0,1,(short)0));
		} else if (this.inventory.getItem(index - this.result.getSize()) != null) {
			return CraftItemStack.asCraftMirror(this.inventory.getItem(index - this.result.getSize()));
		}
		return CraftItemStack.asCraftCopy(new ItemStack(0,1,(short)0));
	}

	@Override
	public void setItem(int index, ItemStack item) {
		if (item != null && item.getTypeId() == 0) {
			item = null;
		}
		if (index == 0) {
			this.result.setItem(index, (item == null ? null : CraftItemStack.asNMSCopy(item)));
		} else {
			this.inventory.setItem((index - this.result.getSize()), (item == null ? null : CraftItemStack.asNMSCopy(item)));
		}
	}

	@Override
	public CraftItemStack[] getMatrix() {
		CraftItemStack[] items = new CraftItemStack[getSize()];
		//net.minecraft.server.ItemStack[] matrix = this.inventory.getContents();

		for (int i = 0; i < getSize(); i++ ) {
			items[i] = CraftItemStack.asCraftMirror(this.inventory.getItem(i));
		}

		return items;
	}

	@Override
	public CraftItemStack getResult() {
		net.minecraft.server.v1_5_R3.ItemStack item = this.result.getItem(0);
		return CraftItemStack.asCraftMirror(item);
	}

	@Override
	public void setMatrix(ItemStack[] contents) {
		if (this.inventory.getContents().length != contents.length) {
			throw new IllegalArgumentException("Invalid inventory size; expected " + this.inventory.getContents().length);
		}

		net.minecraft.server.v1_5_R3.ItemStack[] mcItems = this.inventory.getContents();

		for (int i = 0; i < contents.length; i++ ) {
			ItemStack item = contents[i];
			if (item == null || item.getTypeId() <= 0) {
				mcItems[i] = null;
			} else {
				mcItems[i] = CraftItemStack.asNMSCopy(item);
			}
		}
	}

	@Override
	public void setResult(ItemStack item) {
		net.minecraft.server.v1_5_R3.ItemStack[] contents = this.result.getContents();
		if (item == null || item.getTypeId() <= 0) {
			contents[0] = null;
		} else {
			contents[0] = CraftItemStack.asNMSCopy(item);
		}
	}
}
