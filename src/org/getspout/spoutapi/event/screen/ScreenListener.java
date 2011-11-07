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

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.SpoutEvent;

public class ScreenListener extends CustomEventListener implements Listener{
	
	public void onScreenOpen(ScreenOpenEvent event) {
		
	}
	
	public void onButtonClick(ButtonClickEvent event) {
		
	}
	
	public void onSliderDrag(SliderDragEvent event) {
		
	}
	
	public void onTextFieldChange(TextFieldChangeEvent event) {
		
	}
	
	public void onScreenClose(ScreenCloseEvent event) {
		
	}

    public void onScreenshotReceived(ScreenshotReceivedEvent event) {

    }

	@Override
	public void onCustomEvent(Event event) {
		if (event instanceof SpoutEvent) {
			switch (((SpoutEvent) event).getEventType()) {
				case Screen_Open:
					onScreenOpen((ScreenOpenEvent)event);
					break;
				case Button_Click:
					onButtonClick((ButtonClickEvent)event);
					break;
				case Slider_Drag:
					onSliderDrag((SliderDragEvent)event);
					break;
				case TextField_Change:
					onTextFieldChange((TextFieldChangeEvent)event);
					break;
				case Screen_Close:
					onScreenClose((ScreenCloseEvent)event);
					break;
                case Screenshot_Received:
                    onScreenshotReceived((ScreenshotReceivedEvent)event);
                    break;
			}
		}
	}

}
