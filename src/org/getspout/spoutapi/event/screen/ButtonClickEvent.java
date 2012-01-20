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
package org.getspout.spoutapi.event.screen;

import org.bukkit.event.HandlerList;
import org.getspout.spoutapi.event.EventType;
import org.getspout.spoutapi.event.SpoutEvent;
import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ButtonClickEvent extends ScreenEvent implements SpoutEvent {
	
	private static final long serialVersionUID = -113218697573843579L;
	private static final HandlerList handlers = new HandlerList();
	protected Button control;
	private static final EventType type = EventType.Button_Click;
	
	public ButtonClickEvent(SpoutPlayer player, Screen screen, Button control) {
		super("ButtonClickEvent", player, screen, ScreenType.CUSTOM_SCREEN);
		this.control = control;
	}
	
	public Button getButton() {
		return control;
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
