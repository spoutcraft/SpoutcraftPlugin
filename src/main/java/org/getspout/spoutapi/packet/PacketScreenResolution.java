/*
 * This file is part of SpoutAPI (http://wiki.getspout.org/).
 * 
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
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
import org.getspout.spoutapi.event.screen.ScreenResolutionChangeEvent;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketScreenResolution implements SpoutPacket{
	private int x, y;
	
	public PacketScreenResolution() {
		
	}
	
	public PacketScreenResolution(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getNumBytes() {
		return 8;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		x = input.readInt();
		y = input.readInt();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(x);
		output.writeInt(y);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if (player != null) {
			ScreenResolutionChangeEvent event = new ScreenResolutionChangeEvent(player, player.getMainScreen().getX(), player.getMainScreen().getY(), x, y);
			player.getMainScreen().setScreenX(x);
			player.getMainScreen().setScreenY(y);
			for (Widget w : player.getMainScreen().getAttachedWidgets()) {
				w.onResize(event);
			}
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketScreenResolution;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
