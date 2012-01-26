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

public class PacketAlert implements SpoutPacket {
	public PacketAlert() {

	}

	public PacketAlert(String title, String message, int itemId) {
		this.title = title;
		this.message = message;
		this.itemId = itemId;
	}

	@Override
	public int getNumBytes() {
		return 4 + PacketUtil.getNumBytes(title) + PacketUtil.getNumBytes(message);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		title = PacketUtil.readString(input, 78);
		message = PacketUtil.readString(input, 78);
		itemId = input.readInt();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, title);
		PacketUtil.writeString(output, message);
		output.writeInt(itemId);
	}

	@Override
	public void run(int PlayerId) {

	}

	@Override
	public void failure(int id) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketAlert;
	}

	@Override
	public int getVersion() {
		return 1;
	}

	String message;
	String title;
	int itemId;
}
