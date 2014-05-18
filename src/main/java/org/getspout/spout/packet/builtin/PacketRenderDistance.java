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
package org.getspout.spout.packet.builtin;

import java.io.IOException;

import org.bukkit.Bukkit;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.RenderDistanceChangeEvent;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.player.RenderDistance;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketRenderDistance extends SpoutPacket {
	protected byte view = -1;
	protected byte max = -1;
	protected byte min = -1;

	public PacketRenderDistance() {
	}

	public PacketRenderDistance(boolean resetMax, boolean resetMin) {
		if (resetMax) {
			max = -2;
		}
		if (resetMin) {
			min = -2;
		}
	}

	public PacketRenderDistance(RenderDistance view, RenderDistance max, RenderDistance min) {
		if (view != null) {
			this.view = (byte) view.getValue();
		}
		if (max != null) {
			this.max = (byte) max.getValue();
		}
		if (min != null) {
			this.min = (byte) min.getValue();
		}
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		view = (byte) input.read();
		max = (byte) input.read();
		min = (byte) input.read();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.write(view);
		output.write(max);
		output.write(min);
	}

	@Override
	public void run(int playerId) {
		RenderDistance currentRender = RenderDistance.getRenderDistanceFromValue(view);
		if (currentRender != null) {
			SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
			if (player != null) {
				RenderDistanceChangeEvent event = new RenderDistanceChangeEvent(player, currentRender);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if (!event.isCancelled()) {
					player.setRenderDistance(currentRender, false);
				} else {
					player.sendPacket(new PacketRenderDistance(player.getRenderDistance(), null, null));
				}
			}
		}
	}

	@Override
	public void failure(int id) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketRenderDistance;
	}

	@Override
	public int getVersion() {
		return 1;
	}
}
