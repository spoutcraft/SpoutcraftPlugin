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

import org.getspout.spoutapi.SpoutManager;

public class PacketFullVersion implements SpoutPacket {
	private String versionString;

	public PacketFullVersion() {
	}

	public PacketFullVersion(String versionString) {
		this.versionString = versionString;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		System.out.println("Reading data");
		versionString = PacketUtil.readString(input);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, versionString);
	}

	@Override
	public int getNumBytes() {
		return PacketUtil.getNumBytes(versionString);
	}

	@Override
	public void run(int playerId) {
		SpoutManager.getPlayerManager().setVersionString(playerId, versionString);
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketFullVersion;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
