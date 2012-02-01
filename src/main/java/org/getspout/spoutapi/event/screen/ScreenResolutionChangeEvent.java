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
package org.getspout.spoutapi.event.screen;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ScreenResolutionChangeEvent extends Event {
	private static final long serialVersionUID = -113218697573843579L;
	private static final HandlerList handlers = new HandlerList();
	protected SpoutPlayer player;
	int oldX, oldY;
	int x, y;
	
	public ScreenResolutionChangeEvent(SpoutPlayer player, int oldX, int oldY, int x, int y) {
		super("ScreenResolutionChangeEvent");
		this.player = player;
		this.oldX = oldX;
		this.oldY = oldY;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the new screen size in the x direction
	 * @return x 
	 */
	public int getScreenResolutionX() {
		return x;
	}
	
	/**
	 * Gets the new screen size in the y direction
	 * @return y
	 */
	public int getScreenResolutionY() {
		return y;
	}
	
	/**
	 * Gets the previous screen size in the x direction
	 * @return x
	 */
	public int getPrevScreenResolutionX() {
		return oldX;
	}
	
	/**
	 * Gets the previous screen size in the x direction
	 * @return x
	 */
	public int getPrevScreenResolutionY() {
		return oldY;
	}

	/**
	 * Gets the player involved with the alterated screen size
	 * @return
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
