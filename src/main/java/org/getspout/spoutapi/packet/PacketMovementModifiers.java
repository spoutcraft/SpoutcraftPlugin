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

public class PacketMovementModifiers implements SpoutPacket {
	double gravityMod = 1;
	double walkingMod = 1;
	double swimmingMod = 1;
	double jumpingMod = 1;
	double airspeedMod = 1;

	public PacketMovementModifiers() {

	}

	public PacketMovementModifiers(double gravity, double walking, double swimming, double jumping, double airspeed) {
		this.gravityMod = gravity;
		this.walkingMod = walking;
		this.swimmingMod = swimming;
		this.jumpingMod = jumping;
		this.airspeedMod = airspeed;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		gravityMod = input.readDouble();
		walkingMod = input.readDouble();
		swimmingMod = input.readDouble();
		jumpingMod = input.readDouble();
		airspeedMod = input.readDouble();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeDouble(gravityMod);
		output.writeDouble(walkingMod);
		output.writeDouble(swimmingMod);
		output.writeDouble(jumpingMod);
		output.writeDouble(airspeedMod);
	}

	@Override
	public void run(int playerId) {

	}

	@Override
	public void failure(int playerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketMovementModifiers;
	}

	@Override
	public int getVersion() {
		return 2;
	}
}
