/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.event.screen.TextFieldChangeEvent;
import org.getspout.spoutapi.packet.PacketUtil;

public class GenericTextField extends GenericControl implements TextField{
	
	private static final char MASK_MAXLINES = 0x7F; // bits 1–7
	private static final char MASK_TABINDEX = 0x3F80; // bits 8–14
	private static final char FLAG_PASSWORD = 0x4000; // bit 15
	private static final char FLAG_FOCUS = 0x8000; // bit 16
	
	protected String text = "";
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
		return super.getVersion() + 2;
	}
	
	@Override
	public int getNumBytes() {
		return super.getNumBytes() + 16 + PacketUtil.getNumBytes(text);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		setFieldColor(PacketUtil.readColor(input));
		setBorderColor(PacketUtil.readColor(input));
		char c = input.readChar();
		setPasswordField((c & FLAG_PASSWORD) > 0);
		setMaximumLines(c & MASK_MAXLINES);
		setTabIndex((c & MASK_TABINDEX) >>> 7);
		setFocus((c & FLAG_FOCUS) > 0);
		setCursorPosition(input.readChar());
		setMaximumCharacters(input.readChar());
		setText(PacketUtil.readString(input));
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		PacketUtil.writeColor(output, getFieldColor());
		PacketUtil.writeColor(output, getBorderColor());
		output.writeChar((char) (getMaximumLines() & MASK_MAXLINES | (getTabIndex() << 7) & MASK_TABINDEX  | (isPasswordField() ? FLAG_PASSWORD : 0) | (focus ? FLAG_FOCUS : 0)));
		output.writeChar(getCursorPosition());
		output.writeChar(getMaximumCharacters());
		PacketUtil.writeString(output, getText());
	}

	@Override
	public int getCursorPosition() {
		return cursor;
	}

	@Override
	public TextField setCursorPosition(int position) {
		this.cursor = position;
		return this;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public TextField setText(String text) {
		this.text = text;
		return this;
	}
	
	@Override
	public int getMaximumCharacters() {
		return maxChars;
	}
	
	@Override
	public TextField setMaximumCharacters(int max) {
		this.maxChars = max;
		return this;
	}
	
	@Override
	public int getMaximumLines() {
		return maxLines;
	}
	
	@Override
	public TextField setMaximumLines(int max) {
		this.maxLines = max;
		return this;
	}
	
	@Override
	public Color getFieldColor() {
		return fieldColor;
	}

	@Override
	public TextField setFieldColor(Color color) {
		this.fieldColor = color;
		return this;
	}

	@Override
	public Color getBorderColor() {
		return borderColor;
	}

	@Override
	public TextField setBorderColor(Color color) {
		this.borderColor = color;
		return this;
	}
	
	@Override
	public int getTabIndex() {
		return tabIndex;
	}

	@Override
	public TextField setTabIndex(int index) {
		this.tabIndex = index;
		return this;
	}
	
	@Override
	public boolean isPasswordField() {
		return password;
	}

	@Override
	public TextField setPasswordField(boolean password) {
		this.password = password;
		return null;
	}

	@Override
	public boolean isFocused() {
		return focus;
	}

	@Override
	public TextField setFocus(boolean focus) {
		this.focus = focus;
		return this;
	}
	
	@Override
	public WidgetType getType() {
		return WidgetType.TextField;
	}
	
	@Override
	public TextField copy() {
		// ignore focus parameter which would lead to strange behaviour!
		return ((TextField)super.copy()).setText(getText()).setCursorPosition(getCursorPosition()).setMaximumCharacters(getMaximumCharacters()).setFieldColor(getFieldColor()).setBorderColor(getBorderColor()).setMaximumLines(getMaximumLines()).setTabIndex(getTabIndex()).setPasswordField(isPasswordField());
	}

	@Override
	public void onTextFieldChange(TextFieldChangeEvent event) {
		this.callEvent(event);
	}
}
