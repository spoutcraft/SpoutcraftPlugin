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

import org.getspout.spoutapi.packet.listener.PacketListener;
import org.getspout.spoutapi.packet.standard.MCPacket;

public interface PacketManager {
	/**
	 * Returns a MCPacket instance with the default constructor.
	 * <p/>
	 * An id of 256 will give an uncompressed Map Chunk packet
	 * @param packetId the id of the desired packet
	 * @return an empty MCPacket of type packetId
	 */
	MCPacket getInstance(int packetId);

	/**
	 * adds a packet listener for uncompressed map chunk packets
	 * <p/>
	 * These listeners are NOT called from within the main thread.
	 * @param listener the listener instance
	 */
	public void addListenerUncompressedChunk(PacketListener listener);

	/**
	 * adds a packet listener for packets of the given id
	 * <p/>
	 * These listeners are called from the main server thread
	 * @param packetId the packet id
	 * @param listener the listener instance
	 */
	public void addListener(int packetId, PacketListener listener);

	/**
	 * removes a packet listener for uncompressed map chunk packets
	 * @param listener the listener instance
	 * @return true if listener was removed
	 */
	public boolean removeListenerUncompressedChunk(PacketListener listener);

	/**
	 * removes a packet listener for packets of the given id
	 * @param listener the listener instance
	 * @return true if listener was removed
	 */
	public boolean removeListener(int packetId, PacketListener listener);

	/**
	 * removes all packet listeners
	 * @param listener the listener instance
	 * @return true if listener was removed
	 */
	public void clearAllListeners();
}
