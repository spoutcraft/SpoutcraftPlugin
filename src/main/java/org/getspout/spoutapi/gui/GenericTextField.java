/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
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

public class GenericTextField extends GenericControl implements TextField {
	private static final char MASK_MAXLINES = 0x7F; // bits 1–7
	private static final char MASK_TABINDEX = 0x3F80; // bits 8–14
	private static final char FLAG_PASSWORD = 0x4000; // bit 15
	//private static final char FLAG_FOCUS = 0x8000; // bit 16 // Focus is already set in Control.
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

	public GenericTextField() {
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

	@Override
	public int getCursorPosition() {
		return cursor;
	}

	@Override
	public TextField setCursorPosition(int position) {
		if (getCursorPosition() != position) {
			this.cursor = position;
			autoDirty();
		}
		return this;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public TextField setText(String text) {
		if (text != null && !getText().equals(text)) {
			this.text = text;
			autoDirty();
		}
		return this;
	}

	@Override
	public int getMaximumCharacters() {
		return maxChars;
	}

	@Override
	public TextField setMaximumCharacters(int max) {
		if (getMaximumCharacters() != max) {
			this.maxChars = max;
			autoDirty();
		}
		return this;
	}

	@Override
	public int getMaximumLines() {
		return maxLines;
	}

	@Override
	public TextField setMaximumLines(int max) {
		if (getMaximumLines() != max) {
			this.maxLines = max;
			autoDirty();
		}
		return this;
	}

	@Override
	public Color getFieldColor() {
		return fieldColor;
	}

	@Override
	public TextField setFieldColor(Color color) {
		if (color != null && !getFieldColor().equals(color)) {
			this.fieldColor = color;
			autoDirty();
		}
		return this;
	}

	@Override
	public Color getBorderColor() {
		return borderColor;
	}

	@Override
	public TextField setBorderColor(Color color) {
		if (color != null && !getBorderColor().equals(color)) {
			this.borderColor = color;
			autoDirty();
		}
		return this;
	}

	@Override
	public int getTabIndex() {
		return tabIndex;
	}

	@Override
	public TextField setTabIndex(int index) {
		if (getTabIndex() != index) {
			tabIndex = index;
			autoDirty();
		}
		return this;
	}

	@Override
	public boolean isPasswordField() {
		return password;
	}

	@Override
	public TextField setPasswordField(boolean password) {
		if (isPasswordField() != password) {
			this.password = password;
			autoDirty();
		}
		return null;
	}

	@Override
	public boolean isFocused() {
		return focus;
	}

	@Override
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
		// ignore focus parameter which would lead to strange behaviour!
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

	@Override
	public void onTextFieldChange(TextFieldChangeEvent event) {

	}

	@Override
	public void onTypingFinished() {
	}

	@Override
	public TextField setPlaceholder(String text) {
		if (text != null && !getPlaceholder().equals(text)) {
			placeholder = text;
			autoDirty();
		}
		return this;
	}

	@Override
	public String getPlaceholder() {
		return placeholder;
	}
}
