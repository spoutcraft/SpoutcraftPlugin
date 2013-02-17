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
package org.getspout.spoutapi.material.item;

import java.io.IOException;

import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.material.Food;
import org.getspout.spoutapi.packet.PacketType;

public class GenericCustomFood extends GenericCustomItem implements Food {
	private int hunger;

	public GenericCustomFood(Plugin plugin, String name, String texture, int hungerRestored) {
		super(plugin, name, texture);
		hunger = hungerRestored;
	}

	@Override
	public int getHungerRestored() {
		return hunger;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		hunger = input.read();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.write(getHungerRestored());
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomFood;
	}
}
