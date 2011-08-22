/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
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
import org.bukkit.Location;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketPluginReload implements SpoutPacket{
	public int activeInventoryX;
	public int activeInventoryY;
	public int activeInventoryZ;
	public String worldName;
	
	public PacketPluginReload() {
		
	}
	
	public PacketPluginReload(SpoutPlayer player) {
		if (player.getActiveInventoryLocation() != null) {
			activeInventoryX = player.getActiveInventoryLocation().getBlockX();
			activeInventoryY = player.getActiveInventoryLocation().getBlockY();
			activeInventoryZ = player.getActiveInventoryLocation().getBlockZ();
			worldName = player.getActiveInventoryLocation().getWorld().getName();
		}
		else {
			activeInventoryX = -1;
			activeInventoryY = Integer.MAX_VALUE; //invalid coord
			activeInventoryZ = -1;
			worldName = "null";
		}
	}

	@Override
	public int getNumBytes() {
		return 12 + PacketUtil.getNumBytes(worldName);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		activeInventoryX = input.readInt();
		activeInventoryY = input.readInt();
		activeInventoryZ = input.readInt();
		worldName = PacketUtil.readString(input, 64);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(activeInventoryX);
		output.writeInt(activeInventoryY);
		output.writeInt(activeInventoryZ);
		PacketUtil.writeString(output, worldName);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if (player != null) {
			if (activeInventoryX != -1 && activeInventoryY != Integer.MAX_VALUE && activeInventoryZ != -1) {
				Location active = new Location(Bukkit.getServer().getWorld(worldName), activeInventoryX, activeInventoryY, activeInventoryZ);
				player.setActiveInventoryLocation(active);
			}
		}
	}

	@Override
	public void failure(int id) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketPluginReload;
	}
	
	@Override
	public int getVersion() {
		return 0;
	}

}
