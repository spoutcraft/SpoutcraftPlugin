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

import org.getspout.spoutapi.event.screen.TextFieldChangeEvent;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * This is a box where the user can input a string.
 */
public class TextField extends Control {
	private static final char MASK_MAXLINES = 0x7F; // bits 1–7
	private static final char MASK_TABINDEX = 0x3F80; // bits 8–14
	private static final char FLAG_PASSWORD = 0x4000; // bit 15
	//private static final char FLAG_FOCUS = 0x8000; // bit 16 - Focus is already set in Control.
	protected String text = "";
	protected String placeholder = "";
	protected int cursor = 0;
	protected int maxChars = 16;
	protected int maxLines = 1;
	protected int tabIndex = 0;
	protected boolean focus = false;
	protected boolean password = false;
	protected Color fieldColor = new Color(0, 0, 0);
	protected Color borderColor = new Color(0.625F, 0.625F, 0.625F);

	public TextField() {
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 3;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		setFieldColor(input.readColor());
		setBorderColor(input.readColor());
		char c = input.readChar();
		setPasswordField((c & FLAG_PASSWORD) > 0);
		setMaximumLines(c & MASK_MAXLINES);
		setTabIndex((c & MASK_TABINDEX) >>> 7);
		setCursorPosition(input.readChar());
		setMaximumCharacters(input.readChar());
		setText(input.readString());
		setPlaceholder(input.readString());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeColor(getFieldColor());
		output.writeColor(getBorderColor());
		output.writeChar((char) (getMaximumLines() & MASK_MAXLINES | (getTabIndex() << 7) & MASK_TABINDEX | (isPasswordField() ? FLAG_PASSWORD : 0)));
		output.writeChar((char) getCursorPosition());
		output.writeChar((char) getMaximumCharacters());
		output.writeString(getText());
		output.writeString(getPlaceholder());
	}

	/**
	 * Gets the position of the cursor in the text field. Position zero is the start of the text.
	 * @return position
	 */
	public int getCursorPosition() {
		return cursor;
	}

	/**
	 * Sets the position of the cursor in the text field.
	 * @param position to set to
	 * @return textfield
	 */
	public TextField setCursorPosition(int position) {
		if (getCursorPosition() != position) {
			this.cursor = position;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the text typed in this text field
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text visible in this text field
	 * @param text inside of the text field
	 * @return textfield
	 */
	public TextField setText(String text) {
		if (text != null && !getText().equals(text)) {
			this.text = text;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the maximum characters that can be typed into this text field
	 * @return maximum characters
	 */
	public int getMaximumCharacters() {
		return maxChars;
	}

	/**
	 * Sets the maximum characters that can be typed into this text field
	 * @param max characters that can be typed
	 * @return max chars
	 */
	public TextField setMaximumCharacters(int max) {
		if (getMaximumCharacters() != max) {
			this.maxChars = max;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the maximum line this text field can hold
	 * @return max lines
	 */
	public int getMaximumLines() {
		return maxLines;
	}

	/**
	 * Sets the maximum lines this text field can hold. If zero is passed, the text field will hold as many lines as it can depending on its size.
	 * @param max lines (0 – 127)
	 * @return textfield
	 */
	public TextField setMaximumLines(int max) {
		if (getMaximumLines() != max) {
			this.maxLines = max;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the color of the inner field area of the text box.
	 * @return field color
	 */
	public Color getFieldColor() {
		return fieldColor;
	}

	/**
	 * Sets the field color of the inner field area of the text box.
	 * @param color to render as
	 * @return textfield
	 */
	public TextField setFieldColor(Color color) {
		if (color != null && !getFieldColor().equals(color)) {
			this.fieldColor = color;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the outside color of the field area of the text box.
	 * @return border color
	 */
	public Color getBorderColor() {
		return borderColor;
	}

	/**
	 * Sets the outside color of the field area of the text box.
	 * @param color to render as
	 * @return textfield
	 */
	public TextField setBorderColor(Color color) {
		if (color != null && !getBorderColor().equals(color)) {
			this.borderColor = color;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the tab index for this text field
	 * @return tab index
	 */
	public int getTabIndex() {
		return tabIndex;
	}

	/**
	 * Sets the tab index for this text field. When the player presses
	 * the tabulator key the text field with index+1 will obtain the focus.
	 * Text fields using the same index may not obtain focus when pressing the tabulator key.
	 * The behaviour discontinuous index sequences is undefined.
	 * @param index Tab index (0 – 127)
	 * @return textfield
	 */
	public TextField setTabIndex(int index) {
		if (getTabIndex() != index) {
			tabIndex = index;
			autoDirty();
		}
		return this;
	}

	/**
	 * Determines if this text field is a password field
	 * @return password field
	 */
	public boolean isPasswordField() {
		return password;
	}

	/**
	 * Sets whether the text will be obfuscated by asterisk (*) characters.
	 * Setting to true forces the maximum lines to be 1.
	 * @param password
	 * @return textfield
	 */
	public TextField setPasswordField(boolean password) {
		if (isPasswordField() != password) {
			this.password = password;
			autoDirty();
		}
		return null;
	}

	/**
	 * Determines if this text field is focused
	 * @return focused
	 */
	public boolean isFocused() {
		return focus;
	}

	/**
	 * Sets whether this text field shall obtain focus.
	 * Make sure only one text field gets the focus at a time.
	 * @param focus
	 * @return textfield
	 */
	public TextField setFocus(boolean focus) {
		super.setFocus(focus);
		return this;
	}

	@Override
	public WidgetType getType() {
		return WidgetType.TextField;
	}

	@Override
	public TextField copy() {
		// Ignore focus parameter which would lead to strange behaviour!
		return ((TextField) super.copy())
				.setText(getText())
				.setCursorPosition(getCursorPosition())
				.setMaximumCharacters(getMaximumCharacters())
				.setFieldColor(getFieldColor())
				.setBorderColor(getBorderColor())
				.setMaximumLines(getMaximumLines())
				.setTabIndex(getTabIndex())
				.setPasswordField(isPasswordField())
				.setPlaceholder(getPlaceholder());
	}

	/**
	 * Fires when this text field is typed into on the screen.
	 * This event will also be sent to the screen listener.
	 * @param event
	 */
	public void onTextFieldChange(TextFieldChangeEvent event) {
	}

	/**
	 * Fires when the user presses Enter.
	 */
	public void onTypingFinished() {
	}

	/**
	 * Sets the placeholder to text.
	 * The placeholder will be displayed when no text is in the TextField
	 * @param text to set as placeholder
	 * @return textfield
	 */
	public TextField setPlaceholder(String text) {
		if (text != null && !getPlaceholder().equals(text)) {
			placeholder = text;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the placeholder
	 * @return the placeholder
	 */
	public String getPlaceholder() {
		return placeholder;
	}
}
