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
package org.getspout.spoutapi.event.input;

import org.bukkit.event.Event;
import org.getspout.spoutapi.event.EventType;
import org.getspout.spoutapi.event.SpoutEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class KeyReleasedEvent extends Event implements SpoutEvent {
	
	private static final long serialVersionUID = 6700643745353278739L;
	private SpoutPlayer player;
	private Keyboard key;
	private ScreenType screenType;
	private static final EventType type = EventType.Key_Released;
	
	public KeyReleasedEvent(int keyPress, SpoutPlayer player, ScreenType screenType) {
		super("KeyReleasedEvent");
		this.player = player;
		this.key = Keyboard.getKey(keyPress);
		this.screenType = screenType;
	}
	
	public SpoutPlayer getPlayer() {
		return player;
	}
	
	public Keyboard getKey() {
		return key;
	}
	
	public ScreenType getScreenType(){
		return screenType;
	}
	
	@Override
	public EventType getEventType() {
		return type;
	}
}
