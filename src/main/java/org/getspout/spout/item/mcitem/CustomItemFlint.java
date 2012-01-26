/*
 * This file is part of SpoutPlugin (http://www.spout.org/).
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
package org.getspout.spout.item.mcitem;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.EnumAnimation;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

import org.bukkit.Bukkit;

import org.getspout.spout.Spout;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.Food;
import org.getspout.spoutapi.material.MaterialData;

public class CustomItemFlint extends Item {
	protected CustomItemFlint() {
		super(62);
		a(6, 0).a("flint");
	}

	@Override
	public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman) {
		CustomItem item = MaterialData.getCustomItem(itemstack.getData());
		if (item instanceof Food) {
			--itemstack.count;
			entityhuman.getFoodData().eat(((Food) item).getHungerRestored(), 0.6F);
		}
		return itemstack;
	}

	@Override
	public EnumAnimation d(ItemStack itemstack) {
		CustomItem item = MaterialData.getCustomItem(itemstack.getData());
		if (item instanceof Food) {
			return EnumAnimation.b;
		}
		return super.d(itemstack);
	}

	@Override
	public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
		CustomItem item = MaterialData.getCustomItem(itemstack.getData());
		if (item instanceof Food) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Spout.getInstance(), new FoodUpdate(entityhuman, itemstack), 2);
		}

		return itemstack;
	}

	public static void replaceFlint() {
		Item.byId[MaterialData.flint.getRawId()] = null;
		Item.byId[MaterialData.flint.getRawId()] = new CustomItemFlint();
	}
}

class FoodUpdate implements Runnable {
	EntityHuman human;
	ItemStack item;

	public FoodUpdate(EntityHuman human, ItemStack item) {
		this.human = human;
		this.item = item;
	}

	@Override
	public void run() {
		if (human.b(false)) {
			human.a(item, 32);
		}
	}
}
