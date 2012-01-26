/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.gui.ScreenType;

public class PacketOpenScreen implements SpoutPacket {
	ScreenType type = null;

	public PacketOpenScreen(ScreenType type) {
		this.type = type;
	}

	@Override
	public int getNumBytes() {
		return 4;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		type = ScreenType.getType(input.readInt());
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(type.getCode());
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketOpenScreen;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
