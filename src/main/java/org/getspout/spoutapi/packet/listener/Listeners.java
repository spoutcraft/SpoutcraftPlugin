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
package org.getspout.spoutapi.packet.listener;

import java.util.concurrent.ConcurrentHashMap;

import org.getspout.spoutapi.SpoutManager;

/**
 * Keeps track of packet listeners
 */
public class Listeners {
	/**
	 * Private constructor to avoid initialization
	 */
	private Listeners() {
	}

	private static ConcurrentHashMap<Listener, PacketListener> map = new ConcurrentHashMap<Listener, PacketListener>();

	public static void addListenerUncompressedChunk(Listener listener) {
		PacketListener fake = new FakeListener(listener);
		PacketListener current = map.putIfAbsent(listener, fake);
		if (current != null) {
			fake = current;
		}
		SpoutManager.getPacketManager().addListenerUncompressedChunk(fake);
	}

	public static void addListener(int packetId, Listener listener) {
		if (packetId > 255) {
			return;
		}
		PacketListener fake = new FakeListener(listener);
		PacketListener current = map.putIfAbsent(listener, fake);
		if (current != null) {
			fake = current;
		}
		SpoutManager.getPacketManager().addListener(packetId, fake);
	}

	public static boolean removeListenerUncompressedChunk(Listener listener) {
		PacketListener fake = map.get(listener);
		if (fake != null) {
			return SpoutManager.getPacketManager().removeListenerUncompressedChunk(fake);
		} else {
			return false;
		}
	}

	public static boolean removeListener(int packetId, Listener listener) {
		if (packetId > 255) {
			return false;
		}
		PacketListener fake = map.get(listener);
		if (fake != null) {
			return SpoutManager.getPacketManager().removeListener(packetId, fake);
		} else {
			return false;
		}
	}
}
