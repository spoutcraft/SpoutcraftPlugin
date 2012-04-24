/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
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
package org.getspout.spoutapi.io;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;

import org.getspout.spoutapi.packet.PacketAddonData;
import org.getspout.spoutapi.player.SpoutPlayer;

/**
 * AddonPacket provides a concise and safe way for server plugins to communicate with client side addons.
 */
public abstract class AddonPacket {
	private static HashMap<String, Class<? extends AddonPacket>> packets = new HashMap<String, Class<? extends AddonPacket>>();
	private static HashMap<Class<? extends AddonPacket>, String> packetsIds = new HashMap<Class<? extends AddonPacket>, String>();

	public AddonPacket() {

	}

	/**
	 * Called when this packet has been successfully deserialized from the client
	 * @param player that sent this packet to the server
	 */
	public abstract void run(SpoutPlayer player);

	/**
	 * Reads the incoming data from the client.
	 * <p/>
	 * Note: Data should be read in exactly the same order as it was written.
	 * @param input stream to read data from
	 */
	public abstract void read(SpoutInputStream input);

	/**
	 * Writes the outgoing data to the output stream.
	 * @param output to write data to
	 */
	public abstract void write(SpoutOutputStream output);

	/**
	 * Sends this packet to the collection of players
	 * @param players to send this packet to
	 */
	public final void send(Collection<SpoutPlayer> players) {
		for (SpoutPlayer player : players) {
			send(player);
		}
	}

	/**
	 * Sends this packet to the given player
	 * @param player to send this packet to
	 */
	public final void send(SpoutPlayer player) {
		player.sendPacket(new PacketAddonData(this));
	}

	@SuppressWarnings("unchecked")
	/**
	 * Registers the packet with the server packet list.
	 *
	 * Any packets you register <b>must</b> include an empty constructor.
	 *
	 * Note: The packet requires an identical packet on the client side with the same unique string in order to work.
	 * @param packet class to register
	 * @param uniqueId for this packet
	 */
	public static void register(Class<? extends AddonPacket> packet, String uniqueId) {
		boolean emptyConstructor = false;
		Constructor<? extends AddonPacket>[] constructors = (Constructor<? extends AddonPacket>[]) packet.getConstructors();
		for (Constructor<? extends AddonPacket> c : constructors) {
			if (c.getGenericParameterTypes().length == 0) {
				emptyConstructor = true;
				break;
			}
		}
		if (!emptyConstructor) {
			throw new IllegalArgumentException("Any AddonPacket Must Provide An Empty Constructor");
		}

		packets.put(uniqueId, packet);
		packetsIds.put(packet, uniqueId);
	}

	/**
	 * Gets the unique identifying string for the given packet class, or null if none has been registered.
	 * @param packet class
	 * @return unique string
	 */
	public static String getPacketId(Class<? extends AddonPacket> packet) {
		return packetsIds.get(packet);
	}

	/**
	 * Gets the the packet from the unique identifying string, or null if there is no packet associated with it.
	 * @param uniqueId to get the class for
	 * @return class
	 */
	public static Class<? extends AddonPacket> getPacketFromId(String uniqueId) {
		return packets.get(uniqueId);
	}
}
