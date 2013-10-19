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

import java.io.IOException;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * The Popup class creates an mouseable area where you can put multiple
 * Widgets.
 * <p/>
 * Optionally the background of the popup can be darkened to make it more
 * obvious that it is a popup.
 */
public class PopupScreen extends Screen {
	protected boolean transparent = false;

	public PopupScreen() {
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 0;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		this.setTransparent(input.readBoolean());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeBoolean(isTransparent());
	}

	/**
	 * Is true if the popup screen has no transparency layer
	 * @return transparency
	 */
	public boolean isTransparent() {
		return transparent;
	}

	/**
	 * Sets the transparency layer
	 * @param value to set
	 * @return popupscreen
	 */
	public PopupScreen setTransparent(boolean value) {
		this.transparent = value;
		return this;
	}

	@Override
	public Widget setScreen(Plugin plugin, Screen screen) {
		if (this.screen != null && screen != null && screen != this.screen) {
			((InGameHUD) this.screen).closePopup();
		}
		return super.setScreen(plugin, screen);
	}

	@Override
	public WidgetType getType() {
		return WidgetType.PopupScreen;
	}

	/**
	 * Closes the screen. Functionally equivelent to InGameHUD.closePopup()
	 * @return true if the screen was closed
	 */
	public boolean close() {
		if (getScreen() instanceof InGameHUD) {
			return ((InGameHUD) getScreen()).closePopup();
		}
		return false;
	}

	@Override
	public ScreenType getScreenType() {
		return ScreenType.CUSTOM_SCREEN;
	}

	/**
	 * When the screen is closed, this method is called to handle the
	 * ItemStack that the player is dragging.
	 * @param itemOnCursor ItemStack on the cursor.
	 */
	public void handleItemOnCursor(ItemStack itemOnCursor) {
		// Do nothing with the item by default.
	}
}
