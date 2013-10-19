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

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * The Button class represents a Minecraft button with a label placed on it.
 */
public class Button extends Control {
	protected Label label = new Label().setAlign(WidgetAnchor.TOP_CENTER);
	protected String disabledText = "";
	protected Color hoverColor = new Color(1, 1, 0.627F);
	protected float scale = 1.0F;

	public Button() {
	}

	public Button(String text) {
		setText(text);
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 4;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		label.readData(input);
		setDisabledText(input.readString());
		setHoverColor(input.readColor());
		scale = input.readFloat();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		label.writeData(output);
		output.writeString(getDisabledText());
		output.writeColor(getHoverColor());
		output.writeFloat(scale);
	}

	/**
	 * Gets the text of the label
	 * @return text
	 */
	public String getText() {
		return label.getText();
	}

	/**
	 * Sets the text of the label
	 * @param text to set
	 * @return label
	 */
	public Button setText(String text) {
		label.setText(text);
		return this;
	}

	/**
	 * Gets the color for the text
	 * @return color
	 */
	public Color getTextColor() {
		return label.getTextColor();
	}

	/**
	 * Sets the color for the text
	 * @param color to set
	 * @return label
	 */
	public Button setTextColor(Color color) {
		label.setTextColor(color);
		return this;
	}

	/**
	 * Gets the text that is displayed when the control is disabled
	 * @return disabled text
	 */
	public String getDisabledText() {
		return disabledText;
	}

	/**
	 * Sets the text that is displayed when the control is disabled
	 * @param text to display
	 * @return Button
	 */
	public Button setDisabledText(String text) {
		if (text != null && !getDisabledText().equals(text)) {
			disabledText = text;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the color of the control while the mouse is hovering over it
	 * @return color
	 */
	public Color getHoverColor() {
		return hoverColor;
	}

	/**
	 * Sets the color of the control while the mouse is hovering over it
	 * @param color
	 * @return Button
	 */
	public Button setHoverColor(Color color) {
		if (color != null && !getHoverColor().equals(color)) {
			this.hoverColor = color;
			autoDirty();
		}
		return this;
	}

	@Override
	public WidgetType getType() {
		return WidgetType.Button;
	}

	/**
	 * Determines if text expands to fill width and height
	 * @param auto
	 * @return label
	 */
	public Button setAuto(boolean auto) {
		label.setAuto(auto);
		return this;
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
	 * Set the text alignment
	 * @param pos
	 * @return
	 */
	public Button setAlign(WidgetAnchor pos) {
		label.setAlign(pos);
		return this;
	}

	@Override
	public Button copy() {
		return (Button) ((Button) super.copy())
				.setDisabledText(getDisabledText())
				.setText(getText())
				.setAuto(isAuto())
				.setTextColor(getTextColor())
				.setHoverColor(getHoverColor())
				.setAuto(isAuto())
				.setResize(isResize());
	}

	/**
	 * Fires when this button is clicked on the screen.
	 * <p/>
	 * If this is not overridden in a subclass then this event will be sent
	 * to the screen listener.
	 * @param event
	 */
	public void onButtonClick(ButtonClickEvent event) {
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
	public Button setResize(boolean resize) {
		label.setResize(resize);
		return this;
	}

	/**
	 * Actually resize the Label with the current text size
	 * @return
	 */
	public Label doResize() {
		return label.doResize();
	}

	/**
	 * Set the scale of the text
	 * @param scale to set
	 */
	public Button setScale(float scale) {
		this.scale = scale;
		return this;
	}

	/**
	 * Gets the scale of the text
	 * @return scale of text
	 */
	public float getScale() {
		return scale;
	}

	/**
	 * Sets whether or not the label has a shadow.
	 * @param shadow
	 */
	public Button setShadow(boolean shadow) {
		label.setShadow(shadow);
		return this;
	}

	/**
	 * Gets whether or not the label has a shadow.
	 * @return
	 */
	public boolean hasShadow() {
		return label.hasShadow();
	}
}
