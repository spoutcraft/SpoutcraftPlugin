/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
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

import java.io.File;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class PacketSendPrecache implements CompressiblePacket {
	private byte[] fileData;
	private String plugin;
	private String version;
	private boolean compressed = false;
	
	public PacketSendPrecache() {
	}
	
	public PacketSendPrecache(Plugin plugin, File file) {
		try {
			this.fileData = FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.plugin = plugin.getDescription().getName();
		this.version = plugin.getDescription().getVersion();
	}
	
	// TODO Move to separate thread?
	public void compress() {
		if (!compressed) {
			Deflater deflater = new Deflater();
			deflater.setInput(fileData);
			deflater.setLevel(Deflater.BEST_COMPRESSION);
			deflater.finish();
			ByteArrayOutputStream bos = new ByteArrayOutputStream(fileData.length);
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
			fileData = bos.toByteArray();
			compressed = true;
		}
	}
	
	public void decompress() {
		if (compressed) {
			Inflater decompressor = new Inflater();
			decompressor.setInput(fileData);
				ByteArrayOutputStream bos = new ByteArrayOutputStream(fileData.length);
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
				fileData = bos.toByteArray();
		}
	}
	
	public boolean isCompressed() {
		return compressed;
	}
	
	@Override
	public void readData(SpoutInputStream input) throws IOException {
		this.plugin = input.readString();
		this.version = input.readString();
		compressed = input.readBoolean();
		int size = input.readInt();
		this.fileData = new byte[size];
		input.read(fileData);
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeString(plugin);
		output.writeString(version);
		output.writeBoolean(compressed);
		output.writeInt(fileData.length);
		output.write(fileData);
	}
	
	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketSendPrecache;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
