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

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.getspout.spout.Spout;
import org.getspout.spout.precache.PrecacheManager;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketRequestPrecache implements SpoutPacket {
	private String plugin;

	public PacketRequestPrecache() {
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		plugin = input.readString();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeString(plugin);
	}

	@Override
	public void run(int playerId) {
		Plugin plugin = Bukkit.getPluginManager().getPlugin(this.plugin);

		File preCache = PrecacheManager.getPluginCacheZip(plugin);
		if (preCache.exists()) {
			SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
			if (player != null) {
				player.sendPacket(new PacketSendPrecache(plugin, preCache));
			}
		}
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketRequestPrecache;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
