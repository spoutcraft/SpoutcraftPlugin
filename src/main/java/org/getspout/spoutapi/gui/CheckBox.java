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
 * This defines a simple checkbox widget.
 */
public class CheckBox extends Button {
	boolean checked = false;

	public CheckBox() {
		super();
	}

	public CheckBox(String text) {
		super(text);
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		checked = input.readBoolean();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeBoolean(checked);
	}

	@Override
	public WidgetType getType() {
		return WidgetType.CheckBox;
	}

	@Override
	public CheckBox copy() {
		return ((CheckBox) super.copy()).setChecked(isChecked());
	}

	/**
	 * Check whether this checkbox is checked or not
	 * @return
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Set whether this checkbox is checked or not
	 * @param checked if it should be checked
	 * @return
	 */
	public CheckBox setChecked(boolean checked) {
		if (isChecked() != checked) {
			this.checked = checked;
			autoDirty();
		}
		return this;
	}
}
