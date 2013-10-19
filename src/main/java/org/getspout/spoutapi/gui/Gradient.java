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
 * The Gradient represents a vertical gradient only.
 * <p/>
 * You can specify the same colour for the top and bottom in order to get a
 * solid block of colour, and can set the alpha-level of the Color in order
 * to make it translucent.
 */
public class Gradient extends Widget {
	protected Color color1 = new Color(0, 0, 0, 0), color2 = new Color(0, 0, 0, 0);
	protected Orientation axis = Orientation.VERTICAL;

	public Gradient() {
	}

	public Gradient(Color both) {
		this.color1 = this.color2 = both;
	}

	public Gradient(Color top, Color bottom) {
		this.color1 = top;
		this.color2 = bottom;
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 2;
	}

	/**
	 * Sets the top colour of the gradient to render.
	 * @param color
	 * @return gradient
	 */
	public Gradient setTopColor(Color color) {
		if (color != null && !getTopColor().equals(color)) {
			this.color1 = color;
			autoDirty();
		}
		return this;
	}

	/**
	 * Sets the bottom colour of the gradient to render.
	 * @param color
	 * @return gradient
	 */
	public Gradient setBottomColor(Color color) {
		if (color != null && !getBottomColor().equals(color)) {
			this.color2 = color;
			autoDirty();
		}
		return this;
	}

	/**
	 * Set both top and bottom gradient color in one call.
	 * @param color
	 * @return gradient
	 */
	public Gradient setColor(Color color) {
		setTopColor(color);
		setBottomColor(color);
		return this;
	}

	/**
	 * Gets the top colour of the gradient to render.
	 * @return color
	 */
	public Color getTopColor() {
		return this.color1;
	}

	/**
	 * Gets the bottom colour of the gradient to render.
	 * @return color
	 */
	public Color getBottomColor() {
		return this.color2;
	}

	@Override
	public WidgetType getType() {
		return WidgetType.Gradient;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		this.setTopColor(input.readColor());
		this.setBottomColor(input.readColor());
		this.setOrientation(Orientation.getOrientationFromId(input.read()));
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeColor(getTopColor());
		output.writeColor(getBottomColor());
		output.write(getOrientation().getId());
	}

	@Override
	public Gradient copy() {
		return ((Gradient) super.copy()).setTopColor(getTopColor()).setBottomColor(getBottomColor());
	}

	/**
	 * Set the direction the gradient is drawn.
	 * Default is VERTICAL, if using HORIZONTAL then read top as left and bottom as right.
	 * @param axis the orientation to draw in
	 * @return
	 */
	public Gradient setOrientation(Orientation axis) {
		if (getOrientation() != axis) {
			this.axis = axis;
			autoDirty();
		}
		return this;
	}

	/**
	 * Get the direction the gradient is drawn.
	 * Default is VERTICAL, if using HORIZONTAL then read top as left and bottom as right.
	 * @return the orientation being used
	 */
	public Orientation getOrientation() {
		return axis;
	}
}
