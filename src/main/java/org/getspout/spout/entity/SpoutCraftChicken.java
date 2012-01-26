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
package org.getspout.spout.entity;

import net.minecraft.server.EntityChicken;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftChicken;

import org.getspout.spoutapi.entity.SpoutChicken;

public class SpoutCraftChicken extends CraftChicken implements SpoutChicken{
	public SpoutCraftChicken(CraftServer server, EntityChicken entity) {
		super(server, entity);
	}
}
