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

import org.getspout.spoutapi.event.screen.SliderDragEvent;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * The Slider is a bar with which a user can set a value.
 * <p/>
 * The value is a float between 0f to 1f representing how far from the left
 * the slider is.
 */
public class Slider extends Control {
	protected Label label = new Label();
	protected float slider = 0.5f;

	public Slider() {
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 2;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		setSliderPosition(input.readFloat());
		label.readData(input);
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeFloat(getSliderPosition());
		label.writeData(output);
	}

	/**
	 * Gets the slider position (between 0.0f and 1.0f)
	 * @return slider position
	 */
	public float getSliderPosition() {
		return slider;
	}

	/**
	 * Sets the slider position. Values below 0.0f are rounded to 0, and values above 1.0f are rounded to 1
	 * @param value to set
	 * @return slider
	 */
	public Slider setSliderPosition(float value) {
		float val = Math.max(0f, Math.min(value, 1f));
		if (getSliderPosition() != val) {
			slider = val;
			autoDirty();
		}
		return this;
	}

	@Override
	public WidgetType getType() {
		return WidgetType.Slider;
	}

	@Override
	public Slider copy() {
		return (Slider) ((Slider) super.copy())
				.setSliderPosition(getSliderPosition())
				.setText(getText())
				.setTextColor(getTextColor())
				.setAuto(isAuto())
				.setAlign(getAlign())
				.setScale(getScale())
				.setResize(isResize());
	}

	/**
	 * Fires when this slider is dragged on the screen.
	 * <p/>
	 * This event is also sent to the screen listener, afterwards.
	 * @param event
	 */
	public void onSliderDrag(SliderDragEvent event) {
	}

	/**
	 * Gets the text of the label
	 * @return text
	 */
	public String getText() {
		return label.getText();
	}

	/**
	 * Gets the color for the text
	 * @return color
	 */
	public Color getTextColor() {
		return label.getTextColor();
	}

	/**
	 * True if the text will expand to fill width and height
	 * @return
	 */
	public boolean isAuto() {
		return label.isAuto();
	}

	/**
	 * Get the text alignment
	 * @return
	 */
	public WidgetAnchor getAlign() {
		return label.getAlign();
	}

	/**
	 * Set the scale of the text
	 * @param scale to set
	 */
	public Slider setScale(float scale) {
		label.setScale(scale);
		return this;
	}

	/**
	 * Gets the scale of the text
	 * @return scale of text
	 */
	public float getScale() {
		return label.getScale();
	}

	/**
	 * Sets the text of the label
	 * @param text to set
	 * @return label
	 */
	public Slider setText(String text) {
		label.setText(text);
		return this;
	}

	/**
	 * Sets the color for the text
	 * @param color to set
	 * @return label
	 */
	public Slider setTextColor(Color color) {
		label.setTextColor(color);
		return this;
	}

	/**
	 * Determines if text expands to fill width and height
	 * @param auto
	 * @return label
	 */
	public Slider setAuto(boolean auto) {
		label.setAuto(auto);
		return this;
	}

	/**
	 * Set the text alignment
	 * @param pos
	 * @return
	 */
	public Slider setAlign(WidgetAnchor align) {
		label.setAlign(align);
		return this;
	}

	/**
	 * Does this widget automatically resize with its contents
	 * @return
	 */
	public boolean isResize() {
		return label.isResize();
	}

	/**
	 * Tell this widget to resize with its contents
	 * @param resize
	 * @return
	 */
	public Slider setResize(boolean resize) {
		label.setResize(resize);
		return this;
	}

	/**
	 * Actually resize the Label with the current text size
	 * @return
	 */
	public Slider doResize() {
		label.doResize();
		return this;
	}

	public Slider setShadow(boolean shadow) {
		label.setShadow(shadow);
		return this;
	}

	public boolean hasShadow() {
		return label.hasShadow();
	}
}
