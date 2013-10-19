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

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * This is the base class for all user input widgets.
 */
public abstract class Control extends Widget {
	protected boolean focus = false;
	protected boolean enabled = true;
	protected Color color = new Color(0.878F, 0.878F, 0.878F);
	protected Color disabledColor = new Color(0.625F, 0.625F, 0.625F);

	public Control() {
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 3;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		setEnabled(input.readBoolean());
		setColor(input.readColor());
		setDisabledColor(input.readColor());
		setFocus(input.readBoolean());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeBoolean(isEnabled());
		output.writeColor(getColor());
		output.writeColor(getDisabledColor());
		output.writeBoolean(isFocus());
	}

	/**
	 * True if the control is enabled and can receive input
	 * @return enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Disables input to the control, but still allows it to be visible
	 * @param enable
	 * @return Control
	 */
	public Control setEnabled(boolean enable) {
		if (isEnabled() != enable) {
			enabled = enable;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the color of this control
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of this control
	 * @param color to set
	 * @return Control
	 */
	public Control setColor(Color color) {
		if (color != null && !getColor().equals(color)) {
			this.color = color;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the color of this control when it is disabled
	 * @return disabled color
	 */
	public Color getDisabledColor() {
		return disabledColor;
	}

	/**
	 * Sets the color of this control when it is disabled
	 * @param color to set
	 * @return Control
	 */
	public Control setDisabledColor(Color color) {
		if (color != null && !getDisabledColor().equals(color)) {
			this.disabledColor = color;
			autoDirty();
		}
		return this;
	}

	public boolean isFocus() {
		return focus;
	}

	public Control setFocus(boolean focus) {
		if (isFocus() != focus) {
			this.focus = focus;
			autoDirty();
		}
		return this;
	}

	@Override
	public Control copy() {
		return ((Control) super.copy()).setEnabled(isEnabled()) // for easier reading
				  .setColor(getColor())
				  .setDisabledColor(getDisabledColor());
	}
}
