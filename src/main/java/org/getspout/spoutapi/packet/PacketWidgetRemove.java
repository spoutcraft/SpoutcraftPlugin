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
package org.getspout.spoutapi.packet;

import java.io.IOException;
import java.util.UUID;

import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.gui.WidgetType;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class PacketWidgetRemove implements SpoutPacket {
	protected Widget widget;

	public PacketWidgetRemove() {
	}

	public PacketWidgetRemove(Widget widget, UUID screen) {
		this.widget = widget;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeUUID(widget.getId());
	}

	@Override
	public void run(int PlayerId) {
	}

	@Override
	public void failure(int id) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketWidgetRemove;
	}

	@Override
	public int getVersion() {
		return 1;
	}
}
