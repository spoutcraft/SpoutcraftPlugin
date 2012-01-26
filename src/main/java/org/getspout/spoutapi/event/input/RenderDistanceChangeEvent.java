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
package org.getspout.spoutapi.event.input;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.getspout.spoutapi.event.EventType;
import org.getspout.spoutapi.event.SpoutEvent;
import org.getspout.spoutapi.player.RenderDistance;
import org.getspout.spoutapi.player.SpoutPlayer;

public class RenderDistanceChangeEvent extends Event implements Cancellable, SpoutEvent {
	private static final HandlerList handlers = new HandlerList();
	private static final long serialVersionUID = 3737610397521859191L;
	protected RenderDistance newView;
	protected SpoutPlayer player;
	protected boolean cancel = false;
	private static final EventType type = EventType.Render_Distance_Changed;

	public RenderDistanceChangeEvent(SpoutPlayer player, RenderDistance newView) {
		super("RenderDistanceChangeEvent");
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
	public EventType getEventType() {
		return type;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
