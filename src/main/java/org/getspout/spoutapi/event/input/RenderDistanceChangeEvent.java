/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
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
package org.getspout.spoutapi.event.input;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.getspout.spoutapi.player.RenderDistance;
import org.getspout.spoutapi.player.SpoutPlayer;

public class RenderDistanceChangeEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private final RenderDistance newView;
	private final SpoutPlayer player;
	private boolean cancel = false;

	public RenderDistanceChangeEvent(SpoutPlayer player, RenderDistance newView) {
		this.player = player;
		this.newView = newView;
	}

	public RenderDistance getCurrentRenderDistance() {
		return player.getRenderDistance();
	}

	public RenderDistance getNewRenderDistance() {
		return newView;
	}

	public boolean isCancelled() {
		return cancel;
	}

	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
