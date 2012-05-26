/*
 * This file is part of SpoutPluginAPI.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPluginAPI is licensed under the GNU Lesser General Public License.
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
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
import org.getspout.spoutapi.particle.Particle;

public class PacketParticle implements SpoutPacket {
	Particle p;

	public PacketParticle() {
	}

	public PacketParticle(Particle p) {
		this.p = p;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {

	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeString(p.getName());
		output.writeLocation(p.getLocation());
		output.writeVector(p.getMotion());
		output.writeFloat(p.getScale());
		output.writeFloat(p.getGravity());
		output.writeFloat(p.getParticleRed());
		output.writeFloat(p.getParticleBlue());
		output.writeFloat(p.getParticleGreen());
		output.writeInt(p.getMaxAge());
		output.writeInt(p.getAmount());
	}

	@Override
	public void run(int playerId) {

	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketParticle;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
