/*
 * This file is part of SpoutAPI (http://wiki.getspout.org/).
 * 
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
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

import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.player.SpoutPlayer;

public abstract class AddonPacket {
	private static HashMap<String, Class<? extends AddonPacket>> packets = new HashMap<String, Class<? extends AddonPacket>>();
	private static HashMap<Class<? extends AddonPacket>, String> packetsIds = new HashMap<Class<? extends AddonPacket>, String>();
	
	public AddonPacket() {
		
	}

	public abstract void run(SpoutPlayer player);

	public abstract void read(SpoutInputStream input);

	public abstract void write(SpoutOutputStream output);

	public final void send(Collection<SpoutPlayer> players) {
		for (SpoutPlayer player : players) {
			send(player);
		}
	}

	public final void send(SpoutPlayer player) {
		
	}
	
	public static void recieve(SpoutPacket packet) {
		
	}

	@SuppressWarnings("unchecked")
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
	
	public static String getPacketId(Class<? extends AddonPacket> packet) {
		return packetsIds.get(packet);
	}
	
	public static Class<? extends AddonPacket> getPacketFromId(String className) {
		return packets.get(className);
	}
}
