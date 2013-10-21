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
package org.getspout.spoutapi.packet;

import java.io.IOException;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class PacketSetVelocity implements SpoutPacket {
	private double motX = 0;
	private double motY = 0;
	private double motZ = 0;
	private int entityId = 0;

	public PacketSetVelocity() {
	}

	public PacketSetVelocity(int entityId, double motX, double motY, double motZ) {
		this.entityId = entityId;
		this.motX = motX;
		this.motY = motY;
		this.motZ = motZ;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		entityId = input.readInt();
		motX = input.readDouble();
		motY = input.readDouble();
		motZ = input.readDouble();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(entityId);
		output.writeDouble(motX);
		output.writeDouble(motY);
		output.writeDouble(motZ);
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketSetVelocity;
	}

	@Override
	public int getVersion() {
		return 1;
	}
}
