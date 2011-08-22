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

public class PacketAllowVisualCheats implements SpoutPacket{
	private boolean cheating = false;
	public PacketAllowVisualCheats() {

	}
	
	public PacketAllowVisualCheats(boolean allow) {
		this.cheating = allow;
	}

	@Override
	public int getNumBytes() {
		return 1;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		cheating = input.readBoolean();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeBoolean(cheating);
	}

	@Override
	public void run(int playerId) {
		
	}

	@Override
	public void failure(int id) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketAllowVisualCheats;
	}
	
	@Override
	public int getVersion() {
		return 0;
	}

}
