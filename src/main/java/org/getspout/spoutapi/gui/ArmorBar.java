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
package org.getspout.spoutapi.gui;

import java.io.IOException;
import java.util.UUID;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * The Spout implementation of the default Armor Bar.
 */
public class ArmorBar extends GenericWidget {
	private int icons = 10;
	private boolean alwaysVisible = false;
	private int iconOffset = 8;

	public ArmorBar() {
		setDirty(false);
		setX(427 / 2 - 91); // 122
		setY(191);
		setWidth(getWidth()); // Don't know the default - ignored, but prevents warnings...
		setAnchor(WidgetAnchor.BOTTOM_CENTER);
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		setMaxNumShields(input.readInt());
		setAlwaysVisible(input.readBoolean());
		setIconOffset(input.readInt());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(getMaxNumShields());
		output.writeBoolean(isAlwaysVisible());
		output.writeInt(getIconOffset());
	}

	@Override
	public WidgetType getType() {
		return WidgetType.ArmorBar;
	}

	@Override
	public UUID getId() {
		return new UUID(0, 0);
	}

	/**
	 * Gets the maximum number of shields displayed on the HUD.
	 * <p/>
	 * Armor is scaled to fit the number of shields appropriately.
	 * @return shields displayed
	 */
	public int getMaxNumShields() {
		return icons;
	}

	/**
	 * Sets the maximum number of shields displayed on the HUD.
	 * <p/>
	 * Armor is scaled to fit the number of shields appropriately.
	 * @param shields to display
	 * @return this
	 */
	public ArmorBar setMaxNumShields(int icons) {
		this.icons = icons;
		return this;
	}

	/**
	 * True if the armor bar will appear even when the player has no armor equipped.
	 * @return always visible
	 */
	public boolean isAlwaysVisible() {
		return alwaysVisible;
	}

	/**
	 * Forces the armor bar to appear, even when the player has no armor equipped.
	 * @param visible
	 * @return this
	 */
	public ArmorBar setAlwaysVisible(boolean visible) {
		alwaysVisible = visible;
		return this;
	}

	/**
	 * Gets the number of pixels each shield is offset when drawing the next shield.
	 * @return pixel offset
	 */
	public int getIconOffset() {
		return iconOffset;
	}

	/**
	 * Sets the number of pixels each shield is offset when drawing the next shield.
	 * @param offset when drawing shields
	 * @return this
	 */
	public ArmorBar setIconOffset(int offset) {
		iconOffset = offset;
		return this;
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 1;
	}
}
