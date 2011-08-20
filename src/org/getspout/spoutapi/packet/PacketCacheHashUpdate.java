package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.SpoutManager;

public class PacketCacheHashUpdate implements SpoutPacket {
	public long[] hashes;
	public boolean add;
	public boolean reset = false;
	
	public PacketCacheHashUpdate() {
		hashes = new long[0];
		add = false;
	}
	
	public PacketCacheHashUpdate(boolean add, long[] hashes) {
		this.add = add;
		this.hashes = new long[hashes.length];
		System.arraycopy(hashes, 0, this.hashes, 0, hashes.length);
	}
	
	public int getNumBytes() {
		return 6 + 8 * hashes.length;
	}

	public void readData(DataInputStream input) throws IOException {
		this.add = input.readBoolean();
		this.reset = input.readBoolean();
		int length = input.readInt();
		this.hashes = new long[length];
		for(int i = 0; i < length; i++) {
			this.hashes[i] = input.readLong();
		}
	}

	public void writeData(DataOutputStream output) throws IOException {
		output.writeBoolean(this.add);
		output.writeBoolean(this.reset);
		output.writeInt(hashes.length);
		for(int i = 0; i < hashes.length; i++) {
			output.writeLong(hashes[i]);
		}
	}

	public void run(int id) {
		SpoutManager.getCacheManager().handle(id, add, hashes);
	}

	@Override
	public void failure(int id) {
		
	}

	public PacketType getPacketType() {
		return PacketType.PacketCacheHashUpdate;
	}
	
	@Override
	public int getVersion() {
		return 0;
	}

}
