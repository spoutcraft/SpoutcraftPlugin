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
import java.util.Map;

import org.getspout.spoutapi.SpoutManager;

public class PacketClientAddons implements SpoutPacket {
	private String[] addons;
	private String[] versions;
	public PacketClientAddons() {

	}

	public PacketClientAddons(Map<String,String> addons) {
		this.addons = addons.keySet().toArray(new String[0]);
		this.versions = new String[this.addons.length];
		for (int i = 0; i < this.addons.length; i++) {
			this.versions[i] = addons.get(this.addons[i]);
		}
	}

	@Override
	public int getNumBytes() {
		int size = 2;
		for (int i = 0; i < addons.length; i++) {
			size += PacketUtil.getNumBytes(addons[i]);
			size += PacketUtil.getNumBytes(versions[i]);
		}
		return size;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		int size = input.readShort();
		addons = new String[size];
		versions = new String[size];
		for (int i = 0; i < size; i++) {
			addons[i] = PacketUtil.readString(input);
			versions[i] = PacketUtil.readString(input);
		}
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeShort(addons.length);
		for (int i = 0; i < addons.length; i++) {
			PacketUtil.writeString(output, addons[i]);
			PacketUtil.writeString(output, versions[i]);
		}
	}

	@Override
	public void run(int playerId) {
		SpoutManager.getPlayerFromId(playerId).setAddons(addons, versions);
	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketClientAddons;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
