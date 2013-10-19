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
import org.getspout.spoutapi.packet.PacketComboBox;

public class ComboBox extends Button {
	private List<String> items = new ArrayList<String>();
	private boolean open = false;
	private int selection = -1;
	private String format = "%text%: %selected%";

	/**
	 * Set a list of options
	 * @param items
	 * @return
	 */
	public ComboBox setItems(List<String> items) {
		this.items = items;
		return this;
	}

	/**
	 * Get all options
	 * @return
	 */
	public List<String> getItems() {
		return items;
	}

	/**
	 * Open the combo box
	 * @return
	 */
	public ComboBox openList() {
		setOpen(true, true);
		return this;
	}

	/**
	 * Close the combo box
	 * @return
	 */
	public ComboBox closeList() {
		setOpen(false, true);
		return null;
	}

	/**
	 * Get the label from the selected option
	 * @return
	 */
	public String getSelectedItem() {
		if (selection >= 0 && selection < items.size()) {
			return items.get(selection);
		} else {
			return null;
		}
	}

	/**
	 * Get which option is selected
	 * @return
	 */
	public int getSelectedRow() {
		return this.selection;
	}

	/**
	 * Set which option is active
	 * @param row
	 * @return
	 */
	public ComboBox setSelection(int row) {
		boolean event = row != selection;
		this.selection = row;
		if (event) {
			onSelectionChanged(row, getSelectedItem());
		}
		return this;
	}

	public void onSelectionChanged(int i, String text) {
	}

	public boolean isOpen() {
		return open;
	}

	/**
	 * Sets the open status.
	 * @param open       the state
	 * @param sendPacket if true, send an update packet
	 * @return the instance
	 */
	public ComboBox setOpen(boolean open, boolean sendPacket) {
		if (sendPacket) {
			if (open != this.open) {
				this.open = open;
				PacketComboBox packet = new PacketComboBox(this);
				getScreen().getPlayer().sendPacket(packet);
			}
		}
		this.open = open;
		return this;
	}

	@Override
	public WidgetType getType() {
		return WidgetType.ComboBox;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(selection);
		output.writeInt(getItems().size());
		for (String item : getItems()) {
			output.writeString(item);
		}
		output.writeString(format);
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 1;
	}

	public String getFormat() {
		return format;
	}

	/**
	 * Sets the format of the text on the button. Default is "%text%: %selected%"
	 * <p/>
	 * %text% will be replaced with whatever can be obtained by Button.getText()
	 * %selected% will be replaced with the text of the selected item
	 * @param format the format of the text on the button
	 * @return the instance
	 */
	public ComboBox setFormat(String format) {
		this.format = format;
		return this;
	}

	@Override
	public String getText() {
		if (super.getText() == null || super.getText().isEmpty()) {
			return getSelectedItem();
		} else {
			String text = format.replaceAll("%text%", super.getText()).replaceAll("%selected%", getSelectedItem());
			return text;
		}
	}
}
