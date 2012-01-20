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

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.SpoutEvent;

@Deprecated
public class InputListener extends CustomEventListener implements Listener {

	public InputListener() {

	}

	public void onKeyPressedEvent(KeyPressedEvent event) {

	}

	public void onKeyReleasedEvent(KeyReleasedEvent event) {

	}

	public void onRenderDistanceChange(RenderDistanceChangeEvent event) {

	}
	
	public void onKeyBinding(KeyBindingEvent event) {

	}

	@Override
	public void onCustomEvent(Event event) {
		if (event instanceof SpoutEvent) {
			switch (((SpoutEvent) event).getEventType()) {
				case Key_Pressed:
					onKeyPressedEvent((KeyPressedEvent) event);
					break;
				case Key_Released:
					onKeyReleasedEvent((KeyReleasedEvent) event);
					break;
				case Key_Binding:
					onKeyBinding((KeyBindingEvent)event);
					break;
				case Render_Distance_Changed:
					onRenderDistanceChange((RenderDistanceChangeEvent) event);
					break;
			}
		}
	}
}
