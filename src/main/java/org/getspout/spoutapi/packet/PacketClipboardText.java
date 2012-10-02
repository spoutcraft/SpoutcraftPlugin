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
package org.getspout.spoutapi.packet;

import java.io.IOException;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketClipboardText implements SpoutPacket {
	protected String text;

	public PacketClipboardText() {
	}

	public PacketClipboardText(String text) {
		this.text = text;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		text = input.readString();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		if (text.length() > Short.MAX_VALUE) {
			text = text.substring(0, Short.MAX_VALUE - 1);
		}
		output.writeString(text);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		player.setClipboardText(text, false);
	}

	@Override
	public void failure(int id) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketClipboardText;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
