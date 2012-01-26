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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ScreenCloseEvent;
import org.getspout.spoutapi.event.screen.ScreenEvent;
import org.getspout.spoutapi.event.screen.ScreenOpenEvent;
import org.getspout.spoutapi.gui.PopupScreen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketScreenAction implements SpoutPacket {
	protected byte action = -1;
	protected byte screen = -1; // UnknownScreen

	@Override
	public int getNumBytes() {
		return 2;
	}

	public PacketScreenAction() { }

	public PacketScreenAction(ScreenAction action,ScreenType screen) {
		this.action = (byte) action.getId();
		this.screen = (byte) screen.getCode();
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		action = input.readByte();
		screen = input.readByte();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(action);
		output.writeByte(screen);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		ScreenEvent event;
		switch(ScreenAction.getScreenActionFromId(action)) {
			case Close:
				event = new ScreenCloseEvent(player, player.getMainScreen().getActivePopup(), ScreenType.getType(this.screen) );
				Bukkit.getServer().getPluginManager().callEvent(event);
				if (event.isCancelled()) {
					this.action = (byte) ScreenAction.Close.getId();
					PopupScreen screen = player.getMainScreen().getActivePopup();
					screen.setDirty(true);
					player.sendPacket(new PacketWidget(screen, screen.getId()));
				} else if (ScreenType.getType(this.screen) == ScreenType.CUSTOM_SCREEN) {
					player.getMainScreen().closePopup();
				}
				if (!event.isCancelled()) {
					player.openScreen(ScreenType.GAME_SCREEN, false);
				}
				break;
			case Open:
				event = new ScreenOpenEvent(player, player.getMainScreen().getActivePopup(), ScreenType.getType(this.screen));
				Bukkit.getServer().getPluginManager().callEvent(event);
				if (event.isCancelled()) {
					PopupScreen screen = player.getMainScreen().getActivePopup();
					screen.setDirty(true);
					player.sendPacket(new PacketWidget(screen, screen.getId()));
				} else {
					player.openScreen(ScreenType.getType(this.screen), false);
				}
				break;
		}
	}

	@Override
	public void failure(int id) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketScreenAction;
	}

	@Override
	public int getVersion() {
		return 2;
	}
}
