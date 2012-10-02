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
package org.getspout.spout.entity;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.server.EntityZombie;

import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.util.UnsafeList;

import org.getspout.spout.entity.ai.SpoutcraftAI;
import org.getspout.spoutapi.entity.ai.SpoutAI;

public class EntityBase extends EntityZombie{
	private int aiID = 0;

	public EntityBase(World world) {
		super(((CraftWorld) world).getHandle());
		// Clear AI
		try {
			Field goalList = this.goalSelector.getClass().getDeclaredField("a");
			goalList.setAccessible(true);
			goalList.set(goalSelector, new UnsafeList());
		} catch (Exception ex) {
			Logger.getLogger(EntityBase.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void addTask(SpoutAI ai) { // Add our custom AI
		this.goalSelector.a(aiID++, (SpoutcraftAI) ai);
	}

	public List<SpoutAI> getTasks() {
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
