/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011, Spout LLC <http://www.spout.org/>
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

import java.lang.reflect.Field;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class SpoutEnchantment extends Enchantment {
	public static final Enchantment UNSTACKABLE = new SpoutEnchantment(253);
	public static final Enchantment DURABILITY = new SpoutEnchantment(254);
	public static final Enchantment MAX_DURABILITY = new SpoutEnchantment(255);

	public SpoutEnchantment(int id) {
		super(id);
		try {
			boolean val = Enchantment.isAcceptingRegistrations();
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(this);
			f.set(null, val);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return "Spout Enchantment " + getId();
	}

	@Override
	public int getMaxLevel() {
		return 1001;
	}

	@Override
	public int getStartLevel() {
		return 1000; // Should be impossible to get :P
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ALL;
	}

	@Override
	public boolean conflictsWith(Enchantment paramEnchantment) {
		return false;
	}

	@Override
	public boolean canEnchantItem(ItemStack paramItemStack) {
		return true;
	}
}
