/*
 * This file is part of SpoutPluginAPI.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPluginAPI is licensed under the GNU Lesser General Public License.
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
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

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketPreCacheFile implements SpoutPacket {
	private boolean cached = false;
	private boolean url = false;
	private long expectedCRC;
	private String file;
	private String plugin;

	public PacketPreCacheFile() {

	}

	public PacketPreCacheFile(String plugin, String file, long expectedCRC, boolean url) {
		this.file = file;
		this.plugin = plugin;
		this.expectedCRC = expectedCRC;
		this.url = url;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		this.cached = input.readBoolean();
		this.url = input.readBoolean();
		this.expectedCRC = input.readLong();
		this.file = input.readString();
		this.plugin = input.readString();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeBoolean(this.cached);
		output.writeBoolean(this.url);
		output.writeLong(this.expectedCRC);
		output.writeString(this.file);
		output.writeString(this.plugin);
	}

	@Override
	public void run(int playerId) {
		if (!cached) {
			SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
			if (player != null) {
				File file = new File(this.file);
				if (file.exists()) {
					player.sendPacket(new PacketCacheFile(plugin, file));
				}
			}
		}
	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketPreCacheFile;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
