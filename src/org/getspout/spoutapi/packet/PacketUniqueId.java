package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.entity.Entity;

public class PacketUniqueId implements CompressablePacket{
	private boolean alive = false;
	private boolean compressed = false;
	private byte[] data = null;
	private List<Entity> tempData;
	
	public PacketUniqueId() {
		
	}
	
	public PacketUniqueId(List<Entity> entities, boolean alive) {
		tempData = entities;
		this.alive = alive;
		ByteBuffer tempbuffer = ByteBuffer.allocate(tempData.size() * 20); //4 bytes for entity id, 16 for uuid
		for (Entity e : tempData) {
			tempbuffer.putLong(e.getUniqueId().getLeastSignificantBits());
			tempbuffer.putLong(e.getUniqueId().getMostSignificantBits());
			tempbuffer.putInt(e.getEntityId());
		}
		data = tempbuffer.array();
	}

	@Override
	public int getNumBytes() {
		return 2 + (data != null ? data.length : 0) + 4;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		int size = input.readInt();
		if (size > 0){
			data = new byte[size];
			input.readFully(data);
		}
		alive = input.readBoolean();
		compressed = input.readBoolean();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		if (data != null) {
			output.writeInt(data.length);
			output.write(data);
		}
		else {
			output.writeInt(0);
		}
		output.writeBoolean(alive);
		output.writeBoolean(compressed);
	}

	@Override
	public void run(int playerId) {
		
	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketUniqueId;
	}

	@Override
	public int getVersion() {
		return 2;
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
