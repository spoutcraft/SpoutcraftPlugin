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
package org.getspout.spout.item.mcitem;

import java.lang.reflect.Field;

import net.minecraft.server.v1_5_R3.Block;
import net.minecraft.server.v1_5_R3.EnumToolMaterial;
import net.minecraft.server.v1_5_R3.Item;
import net.minecraft.server.v1_5_R3.ItemSpade;
import net.minecraft.server.v1_5_R3.ItemTool;

public class CustomItemSpade extends ItemSpade {
	public CustomItemSpade(int i, EnumToolMaterial etm) {
		super(i, etm);
	}

	@Override
	public boolean canDestroySpecialBlock(Block paramBlock) {
		if (paramBlock.id == Block.SNOW.id) {
			return true;
		}
		return paramBlock.id == Block.SNOW_BLOCK.id;
	}

	/**
	 * Fixes a bug in nms where Notch compares reference to snow and snow blocks instead of IDs for the snow and snow block
	 */
	public static void replaceSpades() {
		for (int i = 0; i < Item.byId.length; i++) {
			if (Item.byId[i] != null) {
				if (Item.byId[i] instanceof ItemSpade) {
					ItemSpade spade = (ItemSpade)Item.byId[i];
					EnumToolMaterial etm = null;
					Field tool = null;
					try {
						tool = ItemTool.class.getDeclaredField("b");
						tool.setAccessible(true);
						etm = (EnumToolMaterial) tool.get(spade);
						Item.byId[i] = null;
						Item.byId[i] = new CustomItemSpade(spade.id-256, etm);
					} catch (Exception e) {
						System.out.println("Unexpected error replacing the spade material");
						System.out.println("Current item: " + i + " Total Items: " + Item.byId.length);
						System.out.println("Crashed replacing: " + spade.getClass() + " " + spade.toString());
						System.out.println("Was using reflection with: " + (tool != null ? tool.getName() : "null") + " " + tool);
						e.printStackTrace();
					}
				}
			}
		}
	}
}
