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
package org.getspout.spout;

import org.bukkit.World;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;

import org.getspout.spoutapi.WorldManager;

public class SimpleWorldManager implements WorldManager {
	@Override
	public int getWorldHeightBits(World world) {
		if (world instanceof CraftWorld) {
			return 0x8;
		}
		return 7;
	}

	@Override
	public int getWorldXShiftBits(World world) {
		if (world instanceof CraftWorld) {
			return 0xC;
		}
		return 11;
	}

	@Override
	public int getWorldZShiftBits(World world) {
		return getWorldHeightBits(world);
	}
}
