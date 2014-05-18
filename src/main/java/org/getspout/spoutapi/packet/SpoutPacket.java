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
import org.getspout.spout.packet.builtin.PacketType;

public abstract class SpoutPacket {
	/**
	 * Reads the incoming data from the client.
	 * <p/>
	 * Note: Data should be read in exactly the same order as it was written.
	 * @param input stream to read data from
	 */
	public abstract void readData(SpoutInputStream input) throws IOException;

	/**
	 * Writes the outgoing data to the output stream.
	 * @param output to write data to
	 */
	public abstract void writeData(SpoutOutputStream output) throws IOException;

	/**
	 * Performs any tasks for the packet after data has been successfully read into the packet.
	 * @param playerId for the packet
	 */
	public abstract void run(int playerId);

	/**
	 * Performs any tasks for the packet after the data has NOT been successfully read into the packet.
	 * All values will be at defaults (0, null, etc) and are unsafe.
	 * <p/>
	 * Failure is run when the packet versions mismatch and data could not be safely read.
	 * <p/>
	 * It may not be called for all cases of failure.
	 * @param playerId
	 */
	public abstract void failure(int playerId);

	/**
	 * The type of packet represented. Used to rebuild the correct packet on the client.
	 * @return packet type.
	 */
	public abstract PacketType getPacketType();

	/**
	 * Version of the packet this represents. Version numbers should start with 0.
	 * Versions should be incremented any time the member variables or serialization of the packet changes, to prevent crashing.
	 * Mismatched packet versions are discarded, and {@link #failure(int)} is called.
	 * @return version
	 */
	public int getVersion() {
		return 0;
	}
}
