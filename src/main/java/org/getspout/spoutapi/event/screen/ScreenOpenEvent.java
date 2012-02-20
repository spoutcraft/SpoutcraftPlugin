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

import org.bukkit.event.HandlerList;

import org.getspout.spoutapi.event.EventType;
import org.getspout.spoutapi.event.SpoutEvent;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ScreenOpenEvent extends ScreenEvent implements SpoutEvent {

	private static final HandlerList handlers = new HandlerList();
	private static final EventType type = EventType.Screen_Open;

	public ScreenOpenEvent(SpoutPlayer player, Screen screen, ScreenType type) {
		super("ScreenOpenEvent", player, screen, type);
		if (type == ScreenType.PLAYER_INVENTORY || type == ScreenType.CHEST_INVENTORY || type == ScreenType.DISPENSER_INVENTORY || type == ScreenType.FURNACE_INVENTORY || type == ScreenType.WORKBENCH_INVENTORY) {
			this.setCancelled(player.sendInventoryEvent());
		}
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