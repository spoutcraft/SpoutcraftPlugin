/*
 * This file is part of SpoutPluginAPI.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPluginAPI is licensed under the GNU Lesser General Public License.
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
package org.getspout.spoutapi.inventory;

import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.Material;
import org.getspout.spoutapi.material.MaterialData;

public class SpoutItemStack extends ItemStack {
	public SpoutItemStack(ItemStack item) {
		super(item.getTypeId(), item.getAmount(), (short) item.getDurability());
	}

	public SpoutItemStack(int typeId) {
		super(typeId);
	}

	public SpoutItemStack(int typeId, short data) {
		super(typeId, 1, data);
	}

	public SpoutItemStack(int typeId, int amount, short data) {
		super(typeId, amount, data);
	}

	public SpoutItemStack(CustomItem item) {
		super(item.getRawId(), 1, (short) item.getRawData());
	}

	public SpoutItemStack(CustomItem item, int amount) {
		super(item.getRawId(), amount, (short) item.getRawData());
	}

	public SpoutItemStack(CustomBlock block) {
		this(block.getBlockItem());
	}

	public SpoutItemStack(CustomBlock block, int amount) {
		this(block.getBlockItem(), amount);
	}

	public SpoutItemStack(Material material) {
		super(material.getRawId(), 1, (short) material.getRawData());
	}

	public SpoutItemStack(Material material, int amount) {
		super(material.getRawId(), amount, (short) material.getRawData());
	}

	public Material getMaterial() {
		return MaterialData.getMaterial(this.getTypeId(), this.getDurability());
	}

	public boolean isCustomItem() {
		return getMaterial() instanceof CustomItem;
	}
}
