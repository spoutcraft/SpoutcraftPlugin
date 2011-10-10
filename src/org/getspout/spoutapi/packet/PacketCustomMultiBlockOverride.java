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

import gnu.trove.list.array.TIntArrayList;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.io.output.ByteArrayOutputStream;

public class PacketCustomMultiBlockOverride implements CompressablePacket{
	private int chunkX;
	private int chunkZ;
	private boolean compressed = false;
	private byte[] data;
	public PacketCustomMultiBlockOverride(TIntArrayList xCoords, TIntArrayList yCoords, TIntArrayList zCoords, TIntArrayList blockTypeIds, TIntArrayList blockMetadata) {
		short size = (short) xCoords.size();
		ByteBuffer rawData = ByteBuffer.allocate(size * 7);
		chunkX = xCoords.get(0) >> 4;
		chunkZ = zCoords.get(0) >> 4;
		for (int i = 0; i < size; i++) {
			rawData.put((byte) (xCoords.get(i) - chunkX * 16));
			rawData.put((byte) yCoords.get(i));
			rawData.put((byte) (zCoords.get(i) - chunkZ * 16));
			rawData.putShort((short)blockTypeIds.get(i));
			rawData.putShort((short)blockMetadata.get(i));
		}
		data = rawData.array();
	}
	

	@Override
	public int getNumBytes() {
		return 10 + data.length;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		chunkX = input.readInt();
		chunkZ = input.readInt();
		int size = input.readShort();
		data = new byte[size];
		input.readFully(data);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(chunkX);
		output.writeInt(chunkZ);
		output.writeShort(data.length);
		output.write(data);
	}

	@Override
	public void run(int playerId) {
		/*ByteBuffer result = ByteBuffer.allocate(data.length).put(data);
		for (int i = 0; i < data.length / 7; i++) {
			int index = i * 7;
			int x = result.get(index) + chunkX * 16;
			int y = result.get(index+1);
			int z = result.get(index+2) + chunkZ * 16;
			int id = result.get(index+3);
			int data = result.get(index+5);
			SpoutItemBlock.overrideBlock(x, y, z, id, data);
		}*/
	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomMultiBlockOverride;
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
		}
	}

	@Override
	public boolean isCompressed() {
		return compressed;
	}

}
