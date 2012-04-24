/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
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
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketKeyPress implements SpoutPacket {
	public boolean pressDown;
	public byte key;
	public byte settingKeys[] = new byte[10];
	public int screenType = -1;

	public PacketKeyPress() {
	}

	public PacketKeyPress(byte key, boolean pressDown) {
		this.key = key;
		this.pressDown = pressDown;
	}

	public void readData(SpoutInputStream datainputstream) throws IOException {
		this.key = (byte) datainputstream.read();
		this.pressDown = datainputstream.readBoolean();
		this.screenType = datainputstream.readInt();
		for (int i = 0; i < 10; i++) {
			this.settingKeys[i] = (byte) datainputstream.read();
		}
	}

	public void writeData(SpoutOutputStream dataoutputstream) throws IOException {
		dataoutputstream.write(this.key);
		dataoutputstream.writeBoolean(this.pressDown);
		dataoutputstream.writeInt(this.screenType);
		for (int i = 0; i < 10; i++) {
			dataoutputstream.write(this.settingKeys[i]);
		}
	}

	public void run(int id) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(id);
		if (player != null) {
			player.updateKeys(settingKeys);
			if (pressDown) {
				Bukkit.getServer().getPluginManager().callEvent(new KeyPressedEvent(this.key, player, ScreenType.getType(screenType)));
			} else {
				Bukkit.getServer().getPluginManager().callEvent(new KeyReleasedEvent(this.key, player, ScreenType.getType(screenType)));
			}
		}
	}

	@Override
	public void failure(int id) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketKeyPress;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
