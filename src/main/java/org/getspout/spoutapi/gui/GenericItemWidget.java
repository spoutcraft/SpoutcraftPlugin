/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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

import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class GenericItemWidget extends GenericWidget implements ItemWidget {
	protected int material = -1;
	protected short data = -1;
	protected int depth = 8;

	public GenericItemWidget() {
	}

	public GenericItemWidget(ItemStack item) {
		this.material = item.getTypeId();
		this.data = item.getDurability();
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 0;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		this.setTypeId(input.readInt());
		this.setData(input.readShort());
		this.setDepth(input.readInt());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(getTypeId());
		output.writeShort(getData());
		output.writeInt(getDepth());
	}

	@Override
	public ItemWidget setTypeId(int id) {
		if (getTypeId() != id) {
			this.material = id;
			autoDirty();
		}
		return this;
	}

	@Override
	public int getTypeId() {
		return material;
	}

	@Override
	public ItemWidget setData(short data) {
		if (getData() != data) {
			this.data = data;
			autoDirty();
		}
		return this;
	}

	@Override
	public short getData() {
		return data;
	}

	@Override
	public ItemWidget setDepth(int depth) {
		if (getDepth() != depth) {
			this.depth = depth;
			autoDirty();
		}
		return this;
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public ItemWidget setHeight(int height) {
		super.setHeight(height);
		return this;
	}

	@Override
	public ItemWidget setWidth(int width) {
		super.setWidth(width);
		return this;
	}

	@Override
	public WidgetType getType() {
		return WidgetType.ItemWidget;
	}

	@Override
	public ItemWidget copy() {
		return ((ItemWidget) super.copy()).setTypeId(getTypeId()).setData(getData()).setDepth(getDepth());
	}
}
