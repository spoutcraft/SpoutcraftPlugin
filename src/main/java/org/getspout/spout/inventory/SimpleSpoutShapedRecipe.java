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

import java.util.HashMap;

import net.minecraft.server.v1_5_R3.CraftingManager;

import org.bukkit.craftbukkit.v1_5_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.Material;

public class SimpleSpoutShapedRecipe extends SpoutShapedRecipe implements SpoutRecipe {
	public SimpleSpoutShapedRecipe(ItemStack result) {
		super(result);
	}

	public static SimpleSpoutShapedRecipe fromSpoutRecipe(SpoutShapedRecipe recipe) {
		if (recipe instanceof SimpleSpoutShapedRecipe) {
			return (SimpleSpoutShapedRecipe) recipe;
		}
		SimpleSpoutShapedRecipe ret = new SimpleSpoutShapedRecipe(recipe.getResult());
		String[] shape = recipe.getShape();
		ret.shape(shape);
		for (char c : recipe.getIngredientMap().keySet()) {
			ret.setIngredient(c, recipe.getIngredientMap().get(c));
		}
		return ret;
	}

	public void addToCraftingManager() {
		Object[] data;
		String[] shape = this.getShape();
		HashMap<Character, Material> ingred = this.getIngredientMap();
		int datalen = shape.length;
		datalen += ingred.size() * 2;
		int i = 0;
		data = new Object[datalen];
		for (; i < shape.length; i++) {
			data[i] = shape[i];
		}
		for (char c : ingred.keySet()) {
			data[i] = c;
			i++;
			Material mdata = ingred.get(c);

			int id = mdata.getRawId();
			int dmg = mdata.getRawData();

			data[i] = new net.minecraft.server.v1_5_R3.ItemStack(id, 1, dmg);
			i++;
		}
		CraftingManager.getInstance().registerShapedRecipe(CraftItemStack.asNMSCopy(getResult())/*Convert SpoutItemStack to MC ItemStack*/, data);
	}
}
