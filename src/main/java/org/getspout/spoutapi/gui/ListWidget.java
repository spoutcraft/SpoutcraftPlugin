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
 * This defines a list of ListWidgetItems which can be scrolled into visibility.
 */
public class ListWidget extends Scrollable {
	private final List<ListWidgetItem> items = new ArrayList<ListWidgetItem>();
	private int selected = -1;
	protected int cachedTotalHeight = -1;

	@Override
	public WidgetType getType() {
		return WidgetType.ListWidget;
	}

	/**
	 * Get all the items from the list widget
	 * @return the assigned ListWidgetItems
	 */
	public ListWidgetItem[] getItems() {
		ListWidgetItem[] sample = {};
		return items.toArray(sample);
	}

	/**
	 * Returns the nth item from the listwidget
	 * @param n which item to get
	 * @return nth item from the list
	 */
	public ListWidgetItem getItem(int i) {
		if (i < 0 || i >= items.size()) {
			return null;
		}
		return items.get(i);
	}

	/**
	 * Adds an item to the list
	 * @param item the item to add.
	 * @return instance of the ListWidget
	 */
	public ListWidget addItem(ListWidgetItem item) {
		items.add(item);
		item.setListWidget(this);
		cachedTotalHeight = -1;
		return this;
	}

	/**
	 * Add items to the list
	 * @param items to add
	 * @return instance of the ListWidget
	 */
	public ListWidget addItems(ListWidgetItem... items) {
		for (ListWidgetItem item : items) {
			this.addItem(item);
		}
		return this;
	}

	/**
	 * Removes an item from the list.
	 * @param item to remove
	 * @return if item was found.
	 */
	public boolean removeItem(ListWidgetItem item) {
		if (items.contains(item)) {
			items.remove(item);
			item.setListWidget(null);
			cachedTotalHeight = -1;
			return true;
		}
		return false;
	}

	/**
	 * @return the currently selected item.
	 * @returns null when no item is selected.
	 */
	public ListWidgetItem getSelectedItem() {
		return getItem(selected);
	}

	/**
	 * @return the currently selected row.
	 */
	public int getSelectedRow() {
		return selected;
	}

	/**
	 * Sets the selected item to be the nth in the list.
	 * @param n the number of the item or -1 to clear the selection
	 * @return instance of the ListWidget
	 */
	public ListWidget setSelection(int n) {
		selected = n;
		if (selected < -1) {
			selected = -1;
		}
		if (selected > items.size() - 1) {
			selected = items.size() - 1;
		}

		// Check if selection is visible
		ensureVisible(getItemRect(selected));
		return this;
	}

	protected Rectangle getItemRect(int n) {
		ListWidgetItem item = getItem(n);
		Rectangle result = new Rectangle(0, 0, 0, 0);
		if (item == null) {
			return result;
		}
		result.setX(0);
		result.setY(getItemYOnScreen(n));
		result.setHeight(24);
		result.setWidth(getInnerSize(Orientation.VERTICAL));
		return result;
	}

	protected int getItemYOnScreen(int n) {
		return n * 24;
	}

	/**
	 * Get the number of list items
	 * @return
	 */
	public int getSize() {
		return items.size();
	}

	/**
	 * Clears the selection
	 * @return instance of the ListWidget
	 */
	public ListWidget clearSelection() {
		setSelection(-1);
		return this;
	}

	/**
	 * @param n item to check
	 * @returns if the nth item is selected
	 */
	public boolean isSelected(int n) {
		return selected == n;
	}

	public ListWidget setScrollPosition(int position) {
		setScrollPosition(Orientation.VERTICAL, position);
		return this;
	}

	public int getScrollPosition() {
		return getScrollPosition(Orientation.VERTICAL);
	}

	@Override
	public int getInnerSize(Orientation axis) {
		if (axis == Orientation.HORIZONTAL) {
			return getViewportSize(Orientation.HORIZONTAL);
		}
		if (cachedTotalHeight == -1) {
			cachedTotalHeight = getItems().length * 24;
		}
		return cachedTotalHeight + 10;
	}

	public int getTotalHeight() {
		return getInnerSize(Orientation.VERTICAL);
	}

	public int getMaxScrollPosition() {
		return getMaximumScrollPosition(Orientation.VERTICAL);
	}

	/**
	 * @param item to check
	 * @returns if the item is selected
	 */
	public boolean isSelected(ListWidgetItem item) {
		if (getSelectedItem() == null) {
			return false;
		}
		return getSelectedItem().equals(item);
	}

	/**
	 * Moves the selection up or down by n
	 * @param n
	 * @return
	 */
	public ListWidget shiftSelection(int n) {
		if (selected + n < 0) {
			setSelection(0);
		} else {
			setSelection(selected + n);
		}
		return this;
	}

	/**
	 * Will be called on each selection change.
	 * @param item        the number of the item that was clicked/selected by keypress. Can be -1 that means that no item is selected
	 * @param doubleClick if true, item has been doubleclicked.
	 */
	public void onSelected(int item, boolean doubleClick) {
	}

	/**
	 * Clears all attached items.
	 */
	public void clear() {
		items.clear();
		cachedTotalHeight = -1;
		selected = -1;
		autoDirty();
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		selected = input.readInt();
		int count = input.readInt();
		for (int i = 0; i < count; i++) {
			ListWidgetItem item = new ListWidgetItem(input.readString(), input.readString(), input.readString());
			addItem(item);
		}
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(selected); // Write which item is selected.
		output.writeInt(getItems().length); // Write number of items first!
		for (ListWidgetItem item : getItems()) {
			output.writeString(item.getTitle());
			output.writeString(item.getText());
			output.writeString(item.getIconUrl());
		}
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 1;
	}
}
