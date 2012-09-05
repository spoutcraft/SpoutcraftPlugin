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
package org.getspout.spout.item.mcitem;

import java.lang.reflect.Field;

import net.minecraft.server.EnumArmorMaterial;
import net.minecraft.server.Item;
import net.minecraft.server.ItemArmor;
import net.minecraft.server.ItemSpade;
import net.minecraft.server.ItemTool;

public class CustomItemArmor extends ItemArmor {

	public CustomItemArmor(int arg0, EnumArmorMaterial arg1, int arg2,
			int arg3) {
		super(arg0, arg1, arg2, arg3);
	}


	/**
	 * Fixes a bug in nms where Notch compares reference to snow and snow blocks instead of id's for the snow and snow block
	 */
	public static void replaceArmors() {
		for (int i = 0; i < Item.byId.length; i++) {
			if (Item.byId[i] != null) {
				if (Item.byId[i] instanceof ItemArmor) {
					ItemArmor armor = (ItemArmor)Item.byId[i];
					EnumArmorMaterial eam = null;
					Field tool = null;
					try {
						tool = ItemArmor.class.getDeclaredField("bZ");
						tool.setAccessible(true);
						eam = (EnumArmorMaterial) tool.get(armor);
						Item.byId[i] = null;
						Item.byId[i] = new CustomItemArmor(armor.id-256, eam, armor.c, armor.a);
					} catch (Exception e) {
						System.out.println("Unexpected error replacing the armor material");
						System.out.println("Current item: " + i + " Total Items: " + Item.byId.length);
						System.out.println("Crashed replacing: " + armor.getClass() + " " + armor.toString());
						System.out.println("Was using reflection with: " + (tool != null ? tool.getName() : "null") + " " + tool);
						e.printStackTrace();
					}

				}
			}
		}
	}
}
