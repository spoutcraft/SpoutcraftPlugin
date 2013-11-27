/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
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

public class GenericRadioButton extends GenericButton implements RadioButton {
	boolean selected = false;
	int group = 0;

	public GenericRadioButton() {
	}

	public GenericRadioButton(String text) {
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

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public RadioButton setSelected(boolean selected) {
		if (selected) {
			for (RadioButton b : getRadiosInGroup()) {
				b.setSelected(false);
			}
		}
		this.selected = selected;
		return this;
	}

	@Override
	public int getGroup() {
		return group;
	}

	@Override
	public RadioButton setGroup(int group) {
		this.group = group;
		return this;
	}

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
