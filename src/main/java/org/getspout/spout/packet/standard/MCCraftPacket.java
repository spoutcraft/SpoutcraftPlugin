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
package org.getspout.spout.packet.standard;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import net.minecraft.server.v1_5_R3.Packet;

import org.getspout.spoutapi.packet.standard.MCPacket;
import org.getspout.spoutapi.packet.standard.MCPacket51MapChunkUncompressed;

public class MCCraftPacket implements MCPacket {
	Packet packet;
	int packetId;

	public int getId() {
		return packetId;
	}

	public void setPacket(Packet packet, int packetId) {
		this.packet = packet;
		this.packetId = packetId;
	}

	public Packet getPacket() {
		return packet;
	}

	private static Class<?>[] MCPackets = new Class<?>[257];
	private static Class<?>[] packets = new Class<?>[257];

	static {
		MCPackets[0] = MCCraftPacket0KeepAlive.class;
		MCPackets[3] = MCCraftPacket3Chat.class;
		MCPackets[17] = MCCraftPacket17EntityLocationAction.class;
		MCPackets[18] = MCCraftPacket18ArmAnimation.class;
		MCPackets[51] = MCCraftPacket51MapChunk.class;
		MCPackets[103] = MCCraftPacket103SetSlot.class;

		MCPackets[256] = MCPacket51MapChunkUncompressed.class;

		packets[0] = net.minecraft.server.v1_5_R3.Packet0KeepAlive.class;
		packets[3] = net.minecraft.server.v1_5_R3.Packet3Chat.class;
		packets[17] = net.minecraft.server.v1_5_R3.Packet17EntityLocationAction.class;
		packets[18] = net.minecraft.server.v1_5_R3.Packet18ArmAnimation.class;
		packets[51] = net.minecraft.server.v1_5_R3.Packet51MapChunk.class;
		packets[103] = net.minecraft.server.v1_5_R3.Packet103SetSlot.class;

		packets[256] = net.minecraft.server.v1_5_R3.Packet51MapChunk.class;
	}

	private static final Object[] blank = new Class[0];

	public static MCCraftPacket newInstance(Packet packet) {
		return newInstance(packet.n(), packet);
	}

	public static MCCraftPacket newInstance(int packetId, Packet packet) {
		try {
			@SuppressWarnings("unchecked")
			Class<? extends MCCraftPacket> mcp = (Class<? extends MCCraftPacket>) MCPackets[packetId];
			if (mcp == null) {
				return null;
			}
			Constructor<? extends MCCraftPacket> c = mcp.getConstructor(new Class[]{});
			MCCraftPacket r = c.newInstance(blank);
			r.setPacket(packet, packetId);
			return r;
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public static MCCraftPacket newInstance(int packetId) {
		try {
			@SuppressWarnings("unchecked")
			Class<? extends Packet> packetClass = (Class<? extends Packet>) packets[packetId];
			if (packetClass == null) {
				return null;
			}
			Constructor<? extends Packet> c = packetClass.getConstructor(new Class[]{});
			Packet r = c.newInstance(blank);

			return newInstance(packetId, r);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
