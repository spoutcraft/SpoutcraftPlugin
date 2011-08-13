package org.getspout.spoutapi.packet.listener;

import java.util.concurrent.ConcurrentHashMap;

import org.getspout.spoutapi.SpoutManager;

/**
 * Keeps track of packet listeners
 * 
 */

@Deprecated
public class Listeners {

	/**
	 * Private constructor to avoid initialization
	 */
	private Listeners() {}
	
	private static ConcurrentHashMap<Listener,PacketListener> map = new ConcurrentHashMap<Listener,PacketListener>();

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
