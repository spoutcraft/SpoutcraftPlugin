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

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.server.v1_5_R3.EntityHuman;
import net.minecraft.server.v1_5_R3.IInventory;
import net.minecraft.server.v1_5_R3.ItemStack;

import org.bukkit.craftbukkit.v1_5_R3.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_5_R3.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;

public class CustomMCInventory implements IInventory {
	protected ItemStack[] items;
	protected String name;
	protected int stackSize;

	public CustomMCInventory(org.bukkit.inventory.ItemStack items[], String name) {
		this.items = new ItemStack[items.length];
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null || items[i].getTypeId() == 0) {
				this.items[i] = null;
			} else {
				this.items[i] = CraftItemStack.asNMSCopy(items[i]);
			}
		}
		this.name = name;
	}

	public CustomMCInventory(Collection<org.bukkit.inventory.ItemStack> items, String name) {
		this.items = new ItemStack[items.size()];
		int pos = 0;
		for (org.bukkit.inventory.ItemStack item : items) {
			if (item == null || item.getTypeId() == 0) {
				this.items[pos] = null;
			} else {
				this.items[pos] = CraftItemStack.asNMSCopy(item);
			}
			pos++;
		}
		this.name = name;
	}

	public CustomMCInventory(int size, String name) {
		this.items = new ItemStack[size];
		for (int i = 0; i < size; i++) {
			this.items[i] = null;
		}
		this.name = name;
	}

	@Override
	public void startOpen() {
	}

	@Override
	public ItemStack[] getContents() {
		return this.items;
	}

	@Override
	public void onOpen(CraftHumanEntity who) {
	}

	@Override
	public void onClose(CraftHumanEntity who) {
	}

	@Override
	public List<HumanEntity> getViewers() {
		return new LinkedList<HumanEntity>();
	}

	@Override
	public InventoryHolder getOwner() {
		return null;
	}

	@Override
	public ItemStack getItem(int i) {
		return this.items[i];
	}

	@Override
	public ItemStack splitWithoutUpdate(int i) {
		if (this.items[i] != null) {
			ItemStack itemstack = this.items[i];

			this.items[i] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public int getMaxStackSize() {
		return stackSize;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean c() {
		return false;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getSize() {
		return items.length;
	}

	@Override
	public void setItem(int i, ItemStack item) {
		this.items[i] = item;
	}

	@Override
	public ItemStack splitStack(int i, int j) {
		if (this.items[i] != null) {
			ItemStack itemstack;

			if (this.items[i].count <= j) {
				itemstack = this.items[i];
				this.items[i] = null;
				return itemstack;
			} else {
				itemstack = this.items[i].a(j);
				if (this.items[i].count == 0) {
					this.items[i] = null;
				}

				return itemstack;
			}
		}
		return null;
	}

	@Override
	public void update() {
	}

	@Override
	public boolean a(EntityHuman entityhuman) {
		return true;
	}

	@Override
	public void g() {
	}

	@Override
	public boolean b(int i, ItemStack itemStack) {
		return true; //TODO I believe this is CanSet
	}

	@Override
	public void setMaxStackSize(int newSize) {
		stackSize = newSize;
	}
}
