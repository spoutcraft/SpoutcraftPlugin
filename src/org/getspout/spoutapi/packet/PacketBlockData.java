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
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.getspout.spoutapi.material.Block;
import org.getspout.spoutapi.material.MaterialData;

public class PacketBlockData implements CompressablePacket{
	byte[] data;
	boolean compressed = false;
	public PacketBlockData() {
		
	}
	
	public PacketBlockData(Set<Block> modifiedData) {
		if (modifiedData.size() > 0) {
			ByteBuffer rawData = ByteBuffer.allocate(modifiedData.size() * (15));
			
			Iterator<Block> i = modifiedData.iterator();
			while(i.hasNext()) {
				Block next = i.next();
				
				rawData.put((byte) next.getRawId());
				rawData.put((byte) next.getRawData());
				rawData.putFloat(next.getHardness());
				rawData.putInt(next.getLightLevel());
				rawData.putFloat(next.getFriction());
				rawData.put((byte) (next.isOpaque() ? 1 : 0));
			}
		}
	}
	
	public void compress() {
		if (!compressed) {
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
			compressed = true;
		}
	}
	
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
	
	public boolean isCompressed() {
		return compressed;
	}

	@Override
	public int getNumBytes() {
		return data.length + 4;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		int size = input.readInt();
		if (size > 0) {
			data = new byte[size];
			input.readFully(data);
		}
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(data == null ? 0 : data.length);
		if (data != null) {
			output.write(data);
		}
	}

	@Override
	public void run(int playerId) {
		if (data != null) {
			for (int i = 0; i < data.length / 15; i++) {
				int id = data[i];
				short rawData = data[i+1];
				Block block = MaterialData.getBlock(id, rawData);
				if (block != null) {
					block.setHardness(Float.intBitsToFloat(toInt(data, i+2)));
					block.setLightLevel(toInt(data, i+6));
					block.setFriction(Float.intBitsToFloat(toInt(data, i+10)));
					block.setOpaque(data[i+14] != 0);
				}
			}
		}
	}
	
	private static int toInt(byte[] bytes, int offset) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + (int)bytes[offset + i];
		}
		return result;
	}

	@Override
	public void failure(int playerId) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketBlockData;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
