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
package org.getspout.spout.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

import net.minecraft.server.v1_5_R2.Connection;
import net.minecraft.server.v1_5_R2.Packet;

import org.getspout.spout.SpoutPlayerConnection;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.packet.PacketType;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.player.SpoutPlayer;

public class CustomPacket extends Packet {
	public SpoutPacket packet;
	private boolean success = false;
	private static final int[] nags;
	private static final int NAG_MSG_AMT = 0;

	static {
		nags = new int[256];
		for (int i = 0; i < 256; i++) {
			nags[i] = NAG_MSG_AMT;
		}
	}

	public CustomPacket() {
	}

	public CustomPacket(SpoutPacket packet) {
		this.packet = packet;
	}

	@Override
	public int a() {
		return 8;
	}

	@Override
	public void a(DataInputStream input) throws IOException {
		int packetId = -1;
		packetId = input.readShort();
		int version = input.readShort(); // Packet version
		int length = input.readInt(); // Packet size
		if (packetId > -1 && version > -1) {
			try {
				this.packet = PacketType.getPacketFromId(packetId).getPacketClass().newInstance();
			} catch (Exception e) {
				//System.out.println("Failed to identify packet id: " + packetId);
				//e.printStackTrace();
			}
		}
		try {
			if (this.packet == null) {
				input.skipBytes(length);
				//System.out.println("Unknown packet " + packetId + ". Skipping contents.");
				return;
			} else if (packet.getVersion() != version) {
				input.skipBytes(length);
				// Keep server admins from going insane :p
				if (nags[packetId]-- > 0) {
					//System.out.println("Invalid Packet Id: " + packetId + ". Current v: " + packet.getVersion() + " Receieved v: " + version + " Skipping contents.");
				}
			} else {
				byte[] data = new byte[length];
				input.readFully(data);

				SpoutInputStream stream = new SpoutInputStream(ByteBuffer.wrap(data));
				packet.readData(stream);

				success = true;
			}
		} catch (IOException ignore) {
		} catch (Exception e) {
			System.out.println("------------------------");
			System.out.println("Unexpected Exception: " + PacketType.getPacketFromId(packetId) + ", " + packetId);
			e.printStackTrace();
			System.out.println("------------------------");
		}
	}

	SpoutOutputStream stream = new SpoutOutputStream();
	@Override
	public void a(DataOutputStream output) throws IOException {
		if (packet == null) {
			output.writeShort(-1);
			output.writeShort(-1);
			output.writeInt(0);
			return;
		}
		//System.out.println("Writing Packet Data for " + packet.getPacketType());
		output.writeShort(packet.getPacketType().getId());
		output.writeShort(packet.getVersion());

		stream.getRawBuffer().clear();
		packet.writeData(stream);
		ByteBuffer buffer = stream.getRawBuffer();
		byte[] data = new byte[buffer.capacity() - buffer.remaining()];
		System.arraycopy(buffer.array(), 0, data, 0, data.length);

		output.writeInt(data.length);
		output.write(data, 0, data.length);
	}

	@Override
	public void handle(Connection connection) {
		if (connection instanceof SpoutPlayerConnection) {
			SpoutPlayerConnection handler = (SpoutPlayerConnection) connection;
			SpoutPlayer player = SpoutManager.getPlayerFromId(handler.getPlayer().getEntityId());
			if (player != null) {
				if (success) {
					packet.run(player.getEntityId());
				} else if (packet != null) {
					packet.failure(player.getEntityId());
				}
			}
		}
	}

	public static void addClassMapping() {
		try {
			Class<?>[] params = {int.class, boolean.class, boolean.class, Class.class};
			Method addClassMapping = Packet.class.getDeclaredMethod("a", params);
			addClassMapping.setAccessible(true);
			addClassMapping.invoke(null, 195, true, true, CustomPacket.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public static void removeClassMapping() {
		try {
			Packet.l.d(195);
			Field field = Packet.class.getDeclaredField("a");
			field.setAccessible(true);
			Map temp = (Map) field.get(null);
			temp.remove(CustomPacket.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
