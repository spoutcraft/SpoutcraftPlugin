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
import java.util.ArrayList;
import java.util.List;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * This is a simple radio button, which does not know anything about any other
 * radio buttons on the screen.
 */
public class RadioButton extends Button {
	boolean selected = false;
	int group = 0;

	public RadioButton() {
	}

	public RadioButton(String text) {
		super(text);
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		selected = input.readBoolean();
		group = input.readInt();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeBoolean(isSelected());
		output.writeInt(getGroup());
	}

	@Override
	public WidgetType getType() {
		return WidgetType.RadioButton;
	}

	/**
	 * Is this button selected
	 * @return
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Set if this button is selected
	 * @param selected
	 * @return
	 */
	public RadioButton setSelected(boolean selected) {
		if (selected) {
			for (RadioButton b : getRadiosInGroup()) {
				b.setSelected(false);
			}
		}
		this.selected = selected;
		return this;
	}

	/**
	 * Set which group this button is part of
	 * @return
	 */
	public int getGroup() {
		return group;
	}

	/**
	 * Set which group this button is part of
	 * @param group
	 * @return
	 */
	public RadioButton setGroup(int group) {
		this.group = group;
		return this;
	}

	/**
	 * Get a list of all buttons in this group
	 * @return
	 */
	public List<RadioButton> getRadiosInGroup() {
		List<RadioButton> ret = new ArrayList<RadioButton>();
		for (Widget w : getScreen().getAttachedWidgets()) {
			if (w instanceof RadioButton) {
				if (((RadioButton) w).getGroup() == group) {
					ret.add((RadioButton) w);
				}
			}
		}
		return ret;
	}
}
