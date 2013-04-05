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

import net.minecraft.server.v1_5_R2.Block;
import net.minecraft.server.v1_5_R2.EnumToolMaterial;
import net.minecraft.server.v1_5_R2.Item;
import net.minecraft.server.v1_5_R2.ItemPickaxe;
import net.minecraft.server.v1_5_R2.ItemTool;
import net.minecraft.server.v1_5_R2.Material;

public class CustomItemPickaxe extends ItemPickaxe {
	public CustomItemPickaxe(int i, EnumToolMaterial etm) {
		super(i, etm);
	}

	@Override
	public boolean canDestroySpecialBlock(Block block) {
		return block.id == Block.OBSIDIAN.id ? this.b.d() == 3 : (block.id != Block.DIAMOND_BLOCK.id && block.id != Block.DIAMOND_ORE.id ? (block.id != Block.GOLD_BLOCK.id && block.id != Block.GOLD_ORE.id ? (block.id != Block.IRON_BLOCK.id && block.id != Block.IRON_ORE.id ? (block.id != Block.LAPIS_BLOCK.id && block.id != Block.LAPIS_ORE.id ? (block.id != Block.REDSTONE_ORE.id && block.id != Block.GLOWING_REDSTONE_ORE.id ? (block.material == Material.STONE ? true : block.material == Material.ORE) : this.b.d() >= 2) : this.b.d() >= 1) : this.b.d() >= 1) : this.b.d() >= 2) : this.b.d() >= 2);
	}

	/**
	 * Fixes a bug in nms where Notch compares references to ores instead of ore IDs
	 */
	public static void replacePickaxes() {
		for (int i = 0; i < Item.byId.length; i++) {
			if (Item.byId[i] != null) {
				if (Item.byId[i] instanceof ItemPickaxe) {
					ItemPickaxe pickaxe = (ItemPickaxe)Item.byId[i];
					EnumToolMaterial etm = null;
					try {
						Field tool = ItemTool.class.getDeclaredField("b");
						tool.setAccessible(true);
						etm = (EnumToolMaterial) tool.get(pickaxe);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Item.byId[i] = null;
					Item.byId[i] = new CustomItemPickaxe(pickaxe.id-256, etm);
				}
			}
		}
	}
}
