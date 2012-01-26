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
package org.getspout.spoutapi.event.spout;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.getspout.spoutapi.event.EventType;
import org.getspout.spoutapi.event.SpoutEvent;

public class ServerTickEvent extends Event implements SpoutEvent {
	private static final long serialVersionUID = -3947714932775639540L;
	private static final HandlerList handlers = new HandlerList();
	protected long lastTick;
	protected long createdTime = System.currentTimeMillis();
	private static boolean first = true;
	private static long lastTickTime = System.currentTimeMillis();
	private static final EventType type = EventType.Server_Tick;

	public ServerTickEvent() {
		super("ServerTickEvent");
		if (!first) {
			lastTick = lastTickTime;
		} else {
			lastTick = createdTime - 1000;
			first = false;
		}
		lastTickTime = createdTime;
	}

	/**
	 * Returns the milliseconds since the last server tick event was created
	 * Ideally, it should be exactly 50 milliseconds, but because of server lag, it may be more
	 * @return milliseconds since last server tick
	 */
	public Long getMillisLastTick() {
		return Math.abs(createdTime - lastTick);
	}

	/**
	 * Returns the seconds since the last server tick event was created
	 * Ideally, it should be exactly 0.05 seconds, but because of server lag, it may be more
	 * @return seconds since last server tick
	 */
	public double getSecondsLastTick() {
		return ((double)Math.abs(createdTime - lastTick)) / 1000;
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
