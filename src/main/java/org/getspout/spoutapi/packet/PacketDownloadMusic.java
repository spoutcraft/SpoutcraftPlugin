/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.packet;

import java.io.IOException;

import org.bukkit.Location;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class PacketDownloadMusic implements SpoutPacket {
	int x, y, z;
	int volume, distance;
	boolean soundEffect, notify;
	String URL, plugin;

	public PacketDownloadMusic() {
	}

	public PacketDownloadMusic(String plugin, String URL, Location loc, int distance, int volume, boolean soundEffect, boolean notify) {
		this.plugin = plugin;
		this.URL = URL;
		this.volume = volume;
		this.soundEffect = soundEffect;
		this.notify = notify;
		if (loc != null) {
			x = loc.getBlockX();
			y = loc.getBlockY();
			z = loc.getBlockZ();
			this.distance = distance;
		} else {
			this.distance = -1;
		}
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		URL = input.readString();
		plugin = input.readString();
		distance = input.readInt();
		x = input.readInt();
		y = input.readInt();
		z = input.readInt();
		volume = input.readInt();
		soundEffect = input.readBoolean();
		notify = input.readBoolean();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeString(URL);
		output.writeString(plugin);
		output.writeInt(distance);
		output.writeInt(x);
		output.writeInt(y);
		output.writeInt(z);
		output.writeInt(volume);
		output.writeBoolean(soundEffect);
		output.writeBoolean(notify);
	}

	@Override
	public void run(int PlayerId) {
	}

	@Override
	public void failure(int id) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketDownloadMusic;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
