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
package org.getspout.spoutapi.packet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.getspout.spout.precache.PrecacheTuple;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

import org.bukkit.plugin.Plugin;

public class PacketValidatePrecache implements SpoutPacket {
	int count;
	PrecacheTuple[] plugins;

	public PacketValidatePrecache(HashMap<Plugin, Long> pluginMap) {
		count = pluginMap.size();
		plugins = new PrecacheTuple[count];
		int i = 0;
		for (Entry entry : pluginMap.entrySet()) {
			Plugin p = (Plugin) entry.getKey();
			plugins[i] = new PrecacheTuple(
					p.getDescription().getName(),
					p.getDescription().getVersion(),
					(Long) entry.getValue()
			);
			i++;
		}
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		count = input.readInt();
		if (count > 0) {
			plugins = new PrecacheTuple[count];
			for (int i = 0; i < count; i++) {
				String plugin = input.readString();
				String version = input.readString();
				long crc = input.readLong();
				plugins[i] = new PrecacheTuple(plugin, version, crc);
			}
		}
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(count);
		for (int i = 0; i < count; i++) {
			output.writeString(plugins[i].getPlugin());
			output.writeString(plugins[i].getVersion());
			output.writeLong(plugins[i].getCrc());
		}
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketValidatePrecache;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
