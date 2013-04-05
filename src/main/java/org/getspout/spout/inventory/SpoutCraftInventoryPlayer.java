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

import net.minecraft.server.v1_5_R2.IInventory;
import net.minecraft.server.v1_5_R2.PlayerInventory;

import org.bukkit.craftbukkit.v1_5_R2.inventory.CraftInventoryPlayer;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.inventory.SpoutPlayerInventory;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.Tool;

public class SpoutCraftInventoryPlayer extends CraftInventoryPlayer implements SpoutPlayerInventory {
	protected SpoutCraftingInventory crafting;
	protected String name = null;
	public SpoutCraftInventoryPlayer(PlayerInventory inventory, SpoutCraftingInventory crafting) {
		super(inventory);
		this.crafting = crafting;
	}

	public PlayerInventory getHandle() {
		return (PlayerInventory)this.inventory;
	}

	public IInventory getMatrixHandle() {
		return this.crafting.getInventory();
	}

	public IInventory getResultHandle() {
		return this.crafting.getResultHandle();
	}

	public ItemStack getResult() {
		return crafting.getResult();
	}

	public ItemStack[] getMatrix() {
		return crafting.getMatrix();
	}

	public void setResult(ItemStack newResult) {
		crafting.setResult(newResult);
	}

	public void setMatrix(ItemStack[] contents) {
		crafting.setMatrix(contents);
	}

	public String getName() {
		if (name == null) {
			return this.inventory.getName();
		}
		return name;
	}

	public void setName(String title) {
		this.name = title;
	}

	public int getItemInHandSlot() {
		return this.getHandle().itemInHandIndex;
	}

	@Override
	public void remove(org.bukkit.Material material) {
		ItemStack[] items = getContents();
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				continue;
			}
			org.getspout.spoutapi.material.Material myMat = MaterialData.getMaterial(items[i].getTypeId(), items[i].getDurability());
			if (myMat instanceof CustomItem || myMat instanceof Tool || myMat instanceof CustomBlock) {
				continue;
			}
			if (items[i].getType() == material) {
				clear(i);
			}
		}
	}

	@Override
	public void remove(org.getspout.spoutapi.material.Material material) {
		ItemStack[] items = getContents();
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null && MaterialData.getMaterial(items[i].getTypeId(), items[i].getDurability()) == material) {
				clear(i);
			}
		}
	}
}
