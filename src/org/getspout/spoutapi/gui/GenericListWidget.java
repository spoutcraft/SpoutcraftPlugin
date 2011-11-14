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
import java.util.ArrayList;
import java.util.List;

import org.getspout.spoutapi.packet.PacketUtil;

public class GenericListWidget extends GenericScrollable implements ListWidget {

	private List<ListWidgetItem> items = new ArrayList<ListWidgetItem>();
	private int selected = -1;
	private int cachedTotalHeight = -1;

	@Override
	public WidgetType getType() {
		return WidgetType.ListWidget;
	}

	@Override
	public ListWidgetItem[] getItems() {
		ListWidgetItem[] sample = {};
		return items.toArray(sample);
	}

	@Override
	public ListWidgetItem getItem(int i) {
		if (i == -1) {
			return null;
		}
		ListWidgetItem items[] = getItems();
		if (i >= items.length) {
			return null;
		}
		return items[i];
	}

	@Override
	public ListWidget addItem(ListWidgetItem item) {
		items.add(item);
		item.setListWidget(this);
		cachedTotalHeight = -1;
		autoDirty();
		return this;
	}

	@Override
	public boolean removeItem(ListWidgetItem item) {
		if (items.contains(item)) {
			items.remove(item);
			item.setListWidget(null);
			cachedTotalHeight = -1;
			autoDirty();
			return true;
		}
		return false;
	}

	@Override
	public ListWidgetItem getSelectedItem() {
		return getItem(selected);
	}

	@Override
	public int getSelectedRow() {
		return selected;
	}

	@Override
	public ListWidget setSelection(int n) {
		int sel = Math.max(-1, Math.min(n, items.size() - 1));
		if (getSelectedRow() != sel) {
			selected = sel;
			ensureVisible(getItemRect(selected)); // Check if selection is visible
			autoDirty();
		}
		return this;
	}

	private Rectangle getItemRect(int n) {
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

	private int getItemYOnScreen(int n) {
		return items.size() * 24;
	}

	@Override
	public ListWidget clearSelection() {
		setSelection(-1);
		return this;
	}

	@Override
	public boolean isSelected(int n) {
		return selected == n;
	}

	public ListWidget setScrollPosition(int position) {
		setScrollPosition(Orientation.VERTICAL, position);
		return this;
	}

	@Override
	public int getInnerSize(Orientation axis) {
		if (axis == Orientation.HORIZONTAL) {
			return getViewportSize(Orientation.HORIZONTAL);
		}
		if (cachedTotalHeight == -1) {
			cachedTotalHeight = items.size() * 24;
		}
		return cachedTotalHeight + 10;
	}

	public int getTotalHeight() {
		return getInnerSize(Orientation.VERTICAL);
	}

	public int getMaxScrollPosition() {
		return getMaximumScrollPosition(Orientation.VERTICAL);
	}

	@Override
	public boolean isSelected(ListWidgetItem item) {
		if (getSelectedItem() == null) {
			return false;
		}
		return getSelectedItem().equals(item);
	}

	@Override
	public ListWidget shiftSelection(int n) {
		setSelection(Math.max(0, selected + n));
		return this;
	}

	@Override
	public void onSelected(int item, boolean doubleClick) {
	}

	@Override
	public void clear() {
		items.clear();
		cachedTotalHeight = -1;
		selected = -1;
		autoDirty();
	}

	@Override
	public int getNumBytes() {
		int bytes = 0;
		for (ListWidgetItem item : items) {
			bytes += PacketUtil.getNumBytes(item.getTitle());
			bytes += PacketUtil.getNumBytes(item.getText());
			bytes += PacketUtil.getNumBytes(item.getIconUrl());
		}
		return super.getNumBytes() + 4 + 4 + bytes;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		selected = input.readInt();
		int count = input.readInt();
		for (int i = 0; i < count; i++) {
			ListWidgetItem item = new ListWidgetItem(PacketUtil.readString(input), PacketUtil.readString(input), PacketUtil.readString(input));
			addItem(item);
		}
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(selected); // Write which item is selected.
		output.writeInt(items.size()); // Write number of items first!
		for (ListWidgetItem item : items) {
			PacketUtil.writeString(output, item.getTitle());
			PacketUtil.writeString(output, item.getText());
			PacketUtil.writeString(output, item.getIconUrl());
		}
	}
	
	@Override
	public int getVersion() {
		return super.getVersion() + 1;
	}
}
