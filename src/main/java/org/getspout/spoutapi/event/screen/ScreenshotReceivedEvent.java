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
package org.getspout.spoutapi.event.screen;

import java.awt.image.BufferedImage;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import org.getspout.spoutapi.event.EventType;
import org.getspout.spoutapi.event.SpoutEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ScreenshotReceivedEvent extends Event implements SpoutEvent {
	private static final long serialVersionUID = 117L;
	private static final HandlerList handlers = new HandlerList();
	private static final EventType type = EventType.Screenshot_Received;
	private BufferedImage screenshot;
	private SpoutPlayer player;

	public ScreenshotReceivedEvent(SpoutPlayer sp, BufferedImage image) {
		super("ScreenshotReceivedEvent");
		screenshot = image;
		player = sp;
	}

	@Override
	public EventType getEventType() {
		return type;
	}

	public BufferedImage getScreenshot() {
		return screenshot;
	}

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
