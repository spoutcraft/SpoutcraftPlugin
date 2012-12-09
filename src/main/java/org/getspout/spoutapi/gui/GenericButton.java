/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
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

public class GenericButton extends GenericControl implements Button {
	protected GenericLabel label = (GenericLabel) new GenericLabel().setAlign(WidgetAnchor.TOP_CENTER);
	protected String disabledText = "";
	protected Color hoverColor = new Color(1, 1, 0.627F);
	protected float scale = 1.0F;

	public GenericButton() {

	}

	@Override
	public int getVersion() {
		return super.getVersion() + 4;
	}

	public GenericButton(String text) {
		setText(text);
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

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public Button setText(String text) {
		label.setText(text);
		return this;
	}

	@Override
	public Color getTextColor() {
		return label.getTextColor();
	}

	@Override
	public Button setTextColor(Color color) {
		label.setTextColor(color);
		return this;
	}

	@Override
	public String getDisabledText() {
		return disabledText;
	}

	@Override
	public Button setDisabledText(String text) {
		if (text != null && !getDisabledText().equals(text)) {
			disabledText = text;
			autoDirty();
		}
		return this;
	}

	@Override
	public Color getHoverColor() {
		return hoverColor;
	}

	@Override
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

	@Override
	public Button setAuto(boolean auto) {
		label.setAuto(auto);
		return this;
	}

	@Override
	public boolean isAuto() {
		return label.isAuto();
	}

	@Override
	public WidgetAnchor getAlign() {
		return label.getAlign();
	}

	@Override
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

	@Override
	public void onButtonClick(ButtonClickEvent event) {

	}

	@Override
	public boolean isResize() {
		return label.isResize();
	}

	@Override
	public Label setResize(boolean resize) {
		return label.setResize(resize);
	}

	@Override
	public Label doResize() {
		return label.doResize();
	}

	@Override
	public Label setScale(float scale) {
		this.scale = scale;
		return this;
	}

	@Override
	public float getScale() {
		return scale;
	}

	public Label setShadow(boolean shadow) {
		label.setShadow(shadow);
		return this;
	}

	public boolean hasShadow() {
		return label.hasShadow();
	}
}
