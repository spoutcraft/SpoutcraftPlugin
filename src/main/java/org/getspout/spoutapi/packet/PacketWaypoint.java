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

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class PacketWaypoint implements SpoutPacket {
	private double x, y, z;
	private String name;
	private boolean death = false;

	public PacketWaypoint() {
	}

	public PacketWaypoint(double x, double y, double z, String name) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.name = name;
	}

	public PacketWaypoint(double x, double y, double z, String name, boolean death) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.name = name;
		this.death = death;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		x = input.readDouble();
		y = input.readDouble();
		z = input.readDouble();
		name = input.readString();
		death = input.readBoolean();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeDouble(x);
		output.writeDouble(y);
		output.writeDouble(z);
		output.writeString(name);
		output.writeBoolean(death);
	}

	@Override
	public void run(int playerId) {

	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketWaypoint;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
