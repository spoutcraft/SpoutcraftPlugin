/*
 * This file is part of SpoutPluginAPI.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPluginAPI is licensed under the GNU Lesser General Public License.
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.packet;

import java.io.IOException;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class PacketCustomBlockOverride implements SpoutPacket {
	private int x;
	private short y;
	private int z;
	private short blockId;
	private byte data;

	public PacketCustomBlockOverride() {

	}

	public PacketCustomBlockOverride(int x, int y, int z, Integer blockId, Byte data) {
		this.x = x;
		this.y = (short) (y & 0xFFFF);
		this.z = z;
		setBlockId(blockId);
		setBlockData(data);
	}

	private void setBlockId(Integer blockId) {
		if (blockId == null) {
			this.blockId = -1;
		} else {
			this.blockId = blockId.shortValue();
		}
	}

	protected Short getBlockId() {
		return blockId == -1 ? null : blockId;
	}

	private void setBlockData(Byte rot) {
		if (rot == null) {
			this.data = -1;
		} else {
			this.data = rot.byteValue();
		}
	}

	protected Byte getBlockData() {
		return data == -1 ? null : data;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		x = input.readInt();
		y = input.readShort();
		z = input.readInt();
		setBlockId((int) input.readShort());
		setBlockData((byte)input.read());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(x);
		output.writeShort(y);
		output.writeInt(z);
		output.writeShort(blockId);
		output.write(data);
	}

	@Override
	public void run(int PlayerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomBlockOverride;
	}

	@Override
	public int getVersion() {
		return 3;
	}

	@Override
	public void failure(int playerId) {
	}
}
