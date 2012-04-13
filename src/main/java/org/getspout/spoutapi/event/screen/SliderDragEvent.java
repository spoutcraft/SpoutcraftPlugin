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

import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.gui.Slider;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SliderDragEvent extends ScreenEvent{

	private static final HandlerList handlers = new HandlerList();
	private final Slider slider;
	private float position;
	private final float old;

	public SliderDragEvent(SpoutPlayer player, Screen screen, Slider slider, float position) {
		super("SliderDragEvent", player, screen, ScreenType.CUSTOM_SCREEN);
		this.slider = slider;
		this.position = position;
		this.old = slider.getSliderPosition();
	}

	public Slider getSlider() {
		return slider;
	}

	public float getOldPosition() {
		return old;
	}

	public float getNewPosition() {
		return position;
	}

	public void setNewPosition(float position) {
		this.position = position;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}