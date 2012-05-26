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

import java.io.IOException;

import org.bukkit.Bukkit;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.sound.BackgroundMusicEvent;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.sound.Music;

public class PacketMusicChange implements SpoutPacket {
	protected int id;
	protected int volumePercent;
	boolean cancel = false;

	public PacketMusicChange() {

	}

	public PacketMusicChange(int music, int volumePercent) {
		this.id = music;
		this.volumePercent = volumePercent;
	}

	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		id = input.readInt();
		volumePercent = input.readInt();
		cancel = input.readBoolean();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(id);
		output.writeInt(volumePercent);
		output.writeBoolean(cancel);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		Music music = Music.getMusicFromId(id);
		if (player != null && music != null) {
			BackgroundMusicEvent event = new BackgroundMusicEvent(music, volumePercent, player);
			Bukkit.getServer().getPluginManager().callEvent(event);
			if (event.isCancelled()) {
				cancel = true;
			}
			player.sendPacket(this);
		}
	}

	@Override
	public void failure(int id) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketMusicChange;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
