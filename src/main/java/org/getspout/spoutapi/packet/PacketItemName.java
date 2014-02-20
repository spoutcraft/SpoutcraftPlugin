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
package org.getspout.spoutapi.packet;

import java.io.IOException;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class PacketItemName implements SpoutPacket {
	private int id;
	private short data;
	private String name;

	public PacketItemName() {
	}

	public PacketItemName(int id, short data, String name) {
		this.id = id;
		this.data = data;
		this.name = name;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		id = input.readInt();
		data = input.readShort();
		name = input.readString();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(id);
		output.writeShort(data);
		output.writeString(name);
	}

	@Override
	public void run(int PlayerId) {
	}

	@Override
	public void failure(int id) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketItemName;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
