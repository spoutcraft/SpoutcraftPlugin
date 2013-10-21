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
package org.getspout.spoutapi.event.screen;

import org.bukkit.event.HandlerList;

import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.gui.TextField;
import org.getspout.spoutapi.player.SpoutPlayer;

public class TextFieldChangeEvent extends ScreenEvent {
	private static final HandlerList handlers = new HandlerList();
	private final TextField field;
	private final String oldVal;
	private String newVal;

	public TextFieldChangeEvent(SpoutPlayer player, Screen screen, TextField field, String newVal) {
		super("TextFieldChangeEvent", player, screen, ScreenType.CUSTOM_SCREEN);
		this.field = field;
		this.oldVal = field.getText();
		this.newVal = newVal;
	}

	public TextField getTextField() {
		return field;
	}

	public String getOldText() {
		return oldVal;
	}

	public String getNewText() {
		return newVal;
	}

	public void setNewText(String newVal) {
		if (newVal == null) {
			newVal = "";
		}
		this.newVal = newVal;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
