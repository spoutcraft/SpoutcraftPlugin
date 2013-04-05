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
package org.getspout.spoutapi.packet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.io.output.ByteArrayOutputStream;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutChunk;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

import org.bukkit.Chunk;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;

public class PacketCustomBlockChunkOverride implements CompressiblePacket {
	private int chunkX;
	private int chunkZ;
	private boolean hasData = false;
	private byte[] data;
	private boolean compressed = false;

	public PacketCustomBlockChunkOverride() {
	}

	public PacketCustomBlockChunkOverride(short[] customIds, byte[] customData, int x, int z) {
		chunkX = x;
		chunkZ = z;
		if (customIds != null) {
			ByteBuffer buffer = ByteBuffer.allocate(customIds.length * 3);
			for (int i = 0; i < customIds.length; i++) {
				buffer.putShort(customIds[i]);
				buffer.put(customData == null ? 0 : customData[i]);
			}
			data = buffer.array();
			hasData = true;
		}
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		chunkX = input.readInt();
		chunkZ = input.readInt();
		hasData = input.readBoolean();
		if (hasData) {
			int size = input.readInt();
			data = new byte[size];
			input.read(data);
		}
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(chunkX);
		output.writeInt(chunkZ);
		output.writeBoolean(hasData);
		if (hasData) {
			output.writeInt(data.length);
			output.write(data);
		}
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if (player != null) {
			CraftWorld cw = ((CraftWorld)player.getWorld());
			if (cw.getHandle().chunkProviderServer.unloadQueue.contains(chunkX, chunkZ)) {
				return;
			}
			if (!cw.getHandle().chunkProviderServer.isChunkLoaded(chunkX, chunkZ)) {
				return;
			}
			Chunk c = player.getWorld().getChunkAt(chunkX, chunkZ);
			if (c instanceof SpoutChunk) {
				SpoutChunk chunk = (SpoutChunk)c;
				player.sendPacket(new PacketCustomBlockChunkOverride(chunk.getCustomBlockIds(), chunk.getCustomBlockData(), chunkX, chunkZ));
			}
		}
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomBlockChunkOverride;
	}

	@Override
	public int getVersion() {
		return 2;
	}

	@Override
	public void compress() {
		if (!compressed) {
			if (data != null && hasData) {
				Deflater deflater = new Deflater();
				deflater.setInput(data);
				deflater.setLevel(Deflater.BEST_COMPRESSION);
				deflater.finish();
				ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
				byte[] buffer = new byte[1024];
				while (!deflater.finished()) {
					int bytesCompressed = deflater.deflate(buffer);
					bos.write(buffer, 0, bytesCompressed);
				}
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				data = bos.toByteArray();
			}
			compressed = true;
		}
	}

	@Override
	public void decompress() {
		if (compressed && hasData) {
			Inflater decompressor = new Inflater();
			decompressor.setInput(data);

			ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);

			byte[] buf = new byte[1024];
			while (!decompressor.finished()) {
				try {
					int count = decompressor.inflate(buf);
					bos.write(buf, 0, count);
				} catch (DataFormatException e) {
				}
			}
			try {
				bos.close();
			} catch (IOException e) {
			}

			data = bos.toByteArray();
		}
	}

	@Override
	public boolean isCompressed() {
		return compressed;
	}
}
