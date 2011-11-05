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
package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.io.AddonPacket;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketAddonData implements CompressablePacket {
	private AddonPacket packet = null;
	private boolean needsCompression;
	private boolean compressed = false;
	private byte[] data;
	public PacketAddonData() {
		
	}
	
	public PacketAddonData(AddonPacket packet) {
		this.packet = packet;
		SpoutOutputStream stream = new SpoutOutputStream();
		try {
			packet.write(stream);
		}
		catch (Exception e) {
			
		}
		ByteBuffer buffer = stream.getRawBuffer();
		byte[] raw = new byte[buffer.capacity() - buffer.remaining()];
		for (int i = 0; i < raw.length; i++) {
			raw[i] = buffer.get(i);
		}
		data = raw;
		needsCompression = data.length > 512;
	}

	@Override
	public int getNumBytes() {
		return data.length + 4 + 1 + PacketUtil.getNumBytes(AddonPacket.getPacketId(packet.getClass()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readData(DataInputStream input) throws IOException {
		String packetName = PacketUtil.readString(input);
		try {
			Class<? extends AddonPacket> packetClass = AddonPacket.getPacketFromId(packetName);
			Constructor<? extends AddonPacket> constructor = null;
			Constructor<? extends AddonPacket>[] constructors = (Constructor<? extends AddonPacket>[]) packetClass.getConstructors();
			for (Constructor<? extends AddonPacket> c : constructors) {
				if (c.getGenericParameterTypes().length == 0) {
					constructor = c;
					break;
				}
			}
			packet = constructor.newInstance();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		int size = input.readInt();
		compressed = input.readBoolean();
		data = new byte[size];
		input.readFully(data);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, AddonPacket.getPacketId(packet.getClass()));
		output.writeInt(data.length);
		output.writeBoolean(compressed);
		output.write(data);
	}

	@Override
	public void run(int playerId) {
		if (packet != null) {
			SpoutInputStream stream = new SpoutInputStream(ByteBuffer.wrap(data));
			try {
				packet.read(stream);
				SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
				if (player != null) {
					packet.run(player);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketAddonData;
	}

	@Override
	public int getVersion() {
		return 0;
	}

	@Override
	public void compress() {
		if (!compressed) {
			if (data != null) {
				Deflater deflater = new Deflater();
				deflater.setInput(data);
				deflater.setLevel(Deflater.BEST_COMPRESSION);
				deflater.finish();
				ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
				byte[] buffer = new byte[1024];
				while(!deflater.finished())
				{
					int bytesCompressed = deflater.deflate(buffer);
					bos.write(buffer, 0, bytesCompressed);
				}
				try {
					bos.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				data = bos.toByteArray();
			}
			compressed = true;
		}
	}

	@Override
	public void decompress() {
		if (compressed) {
			Inflater decompressor = new Inflater();
			decompressor.setInput(data);

			ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);

			byte[] buf = new byte[1024];
			while (!decompressor.finished()) {
				try {
					int count = decompressor.inflate(buf);
					bos.write(buf, 0, count);
				}
				catch (DataFormatException e) {
					
				}
			}
			try {
				bos.close();
			}
			catch (IOException e) {
				
			}

			data = bos.toByteArray();
			compressed = false;
		}
	}

	@Override
	public boolean isCompressed() {
		return !needsCompression || compressed;
	}

}
