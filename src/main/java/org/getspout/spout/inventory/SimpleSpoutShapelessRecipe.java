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

import java.util.ArrayList;

import net.minecraft.server.v1_5_R2.CraftingManager;

import org.bukkit.craftbukkit.v1_5_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.inventory.SpoutShapelessRecipe;
import org.getspout.spoutapi.material.Material;

public class SimpleSpoutShapelessRecipe extends SpoutShapelessRecipe implements SpoutRecipe {
	public SimpleSpoutShapelessRecipe(ItemStack result) {
		super(result);
	}

	public static SimpleSpoutShapelessRecipe fromSpoutRecipe(SpoutShapelessRecipe recipe) {
		if (recipe instanceof SimpleSpoutShapelessRecipe) {
			return (SimpleSpoutShapelessRecipe) recipe;
		}
		SimpleSpoutShapelessRecipe ret = new SimpleSpoutShapelessRecipe(recipe.getResult());
		for (Material ingred : recipe.getIngredientList()) {
			ret.addIngredient(ingred);
		}
		return ret;
	}

	public void addToCraftingManager() {
		ArrayList<Material> ingred = this.getIngredientList();
		Object[] data = new Object[ingred.size()];
		int i = 0;
		for (Material mdata : ingred) {
			int id = mdata.getRawId();
			int dmg = mdata.getRawData();
			data[i] = new net.minecraft.server.v1_5_R2.ItemStack(id, 1, dmg);
			i++;
		}
		CraftingManager.getInstance().registerShapelessRecipe(CraftItemStack.asNMSCopy(getResult())/*Convert SpoutItemStack to MC ItemStack*/, data);
	}
}
