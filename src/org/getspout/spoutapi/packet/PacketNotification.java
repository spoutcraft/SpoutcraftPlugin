/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
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

public class PacketNotification extends PacketAlert{
	protected int time;
	protected short data;
	public PacketNotification() {
		
	}
	
	public PacketNotification(String title, String message, int itemId, short data, int time) {
		super(title, message, itemId);
		this.time = time;
		this.data = data;
	}
	
	@Override
	public int getNumBytes() {
		return super.getNumBytes() + 6;
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		this.data = input.readShort();
		this.time = input.readInt();
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		output.writeShort(data);
		output.writeInt(time);
	}
	
	@Override
	public PacketType getPacketType() {
		return PacketType.PacketNotification;
	}
	
	@Override
	public int getVersion() {
		return 0;
	}

}
