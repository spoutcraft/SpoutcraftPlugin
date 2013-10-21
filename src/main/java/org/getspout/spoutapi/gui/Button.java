/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.gui;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;

/**
 * The GenericButton class represents a Minecraft button with a label placed on it.
 */
public interface Button extends Control, Label {
	/**
	 * Gets the text that is displayed when the control is disabled
	 * @return disabled text
	 */
	public String getDisabledText();

	/**
	 * Sets the text that is displayed when the control is disabled
	 * @param text to display
	 * @return Button
	 */
	public Button setDisabledText(String text);

	/**
	 * Gets the color of the control while the mouse is hovering over it
	 * @return color
	 */
	public Color getHoverColor();

	/**
	 * Sets the color of the control while the mouse is hovering over it
	 * @param color
	 * @return Button
	 */
	public Button setHoverColor(Color color);

	@Override
	public Button setText(String text);

	@Override
	public Button setTextColor(Color color);

	@Override
	public Button setAuto(boolean auto);

	/**
	 * Fires when this button is clicked on the screen.
	 * <p/>
	 * If this is not overridden in a subclass then this event will be sent
	 * to the screen listener.
	 * @param event
	 */
	public void onButtonClick(ButtonClickEvent event);
}
