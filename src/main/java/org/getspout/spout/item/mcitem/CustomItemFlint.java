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

import net.minecraft.server.v1_5_R2.EntityHuman;
import net.minecraft.server.v1_5_R2.EnumAnimation;
import net.minecraft.server.v1_5_R2.INetworkManager;
import net.minecraft.server.v1_5_R2.Item;
import net.minecraft.server.v1_5_R2.ItemStack;
import net.minecraft.server.v1_5_R2.NetworkManager;
import net.minecraft.server.v1_5_R2.World;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_5_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import org.getspout.spout.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.Food;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.player.SpoutPlayer;

public class CustomItemFlint extends Item {
	private Class<?> clazz = null;
	private Field a = null;

	protected CustomItemFlint() {
		super(62);
		b("flint");
		Class clazz = null;
		try {
			clazz = Class.forName("net.minecraft.server.v1_5_R2.NetworkWriterThread");
		} catch (ClassNotFoundException e) {
			// Libigot/Nover compatibility. Those two replace the classloader and as such forname fails on them.
			// This may be temporary as we fix it in Libigot. For now, this will do.
			try {
				clazz = Class.forName("net.minecraft.server.NetworkWriterThread");
			} catch (ClassNotFoundException e1) {
				e.printStackTrace();
			}
		}
		try {
			a = clazz.getDeclaredField("a");
			a.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public EnumAnimation b_(ItemStack itemstack) {
		CustomItem item = MaterialData.getCustomItem(itemstack.getData());
		if (item instanceof Food) {
			return EnumAnimation.EAT;
		}
		return super.b_(itemstack);
	}

	@Override
	public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
		CustomItem item = MaterialData.getCustomItem(itemstack.getData());
		if (item instanceof Food) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Spout.getInstance(), new FoodUpdate(entityhuman, itemstack), 2);
		}

		return itemstack;
	}

	@Override
	public boolean w() {
		if (clazz == null || a == null) {
			return false;
		}
		Thread t = Thread.currentThread();
		if (!clazz.isInstance(t)) {
			return false;
		}
		NetworkManager nm = null;
		try {
			nm = (NetworkManager) a.get(t);
		} catch (Exception e) {
		}
		if (nm == null) {
			return false;
		}
		SpoutPlayer player = lookupPlayer(nm);
		if (player == null) {
			return false;
		}
		return player.isSpoutCraftEnabled();
	}

	private SpoutPlayer lookupPlayer(NetworkManager nm) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (!(p instanceof CraftPlayer)) {
				continue;
			}
			INetworkManager n = ((CraftPlayer) p).getHandle().playerConnection.networkManager;
			if (n == nm) {
				return SpoutManager.getPlayer(p);
			}
		}
		return null;
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
		if (human.i(false)) {
			human.a(item, 32);
		}
	}
}
