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
package org.getspout.spoutapi.event.spout;

import org.bukkit.event.Event;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutCraftEnableEvent extends Event{
	private static final long serialVersionUID = 5172371431162815630L;
	private SpoutPlayer player;
	public SpoutCraftEnableEvent(SpoutPlayer player) {
		super("SpoutCraftEnableEvent");
		this.player = player;
	}

	/**
	 * Returns the player who just had their Spout SinglePlayer Mod enabled
	 * @return player
	 */
	public SpoutPlayer getPlayer() {
		return player;
	}
}
