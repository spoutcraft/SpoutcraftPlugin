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
package org.getspout.spoutapi.inventory;

import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomArmor;

import net.minecraft.server.Block;
import net.minecraft.server.ContainerPlayer;
import net.minecraft.server.IInventory;
import net.minecraft.server.ItemArmor;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Slot;

public class SpoutSlotArmor extends Slot {
	public int a;
	public ContainerPlayer b;

	public SpoutSlotArmor(ContainerPlayer containerplayer, IInventory iinventory, int i, int j, int k, int l) {
		super(iinventory, i, j, k);
		this.b = containerplayer;
		this.a = l;
	}

	public int a() {
		return 1;
	}

	public boolean isAllowed(ItemStack itemstack) {
		// Spout start
		if (itemstack.id == 318) {
			CustomItem item = MaterialData.getCustomItem(itemstack.i());
			if (item != null) {
				if (item instanceof GenericCustomArmor) {
					return (((GenericCustomArmor) item).getType() == this.a);
				}
				return false;
			}
		}
		// Spout end
		return itemstack.getItem() instanceof ItemArmor ? ((ItemArmor) itemstack
				.getItem()).a == this.a
				: (itemstack.getItem().id == Block.PUMPKIN.id ? this.a == 0
						: false);
	}
}
