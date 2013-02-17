/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
package org.getspout.spoutapi.event.spout;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutcraftFailedEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private final SpoutPlayer player;

	public SpoutcraftFailedEvent(SpoutPlayer player) {
		this.player = player;
	}

	/**
	 * Returns the player who just had their Spout SinglePlayer Mod enabled
	 * @return player
	 */
	public SpoutPlayer getPlayer() {
		return player;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
