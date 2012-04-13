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

import java.io.IOException;
import java.util.Map;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

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
	public void readData(SpoutInputStream input) throws IOException {
		int size = input.readShort();
		addons = new String[size];
		versions = new String[size];
		for (int i = 0; i < size; i++) {
			addons[i] = input.readString();
			versions[i] = input.readString();
		}
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeShort((short) addons.length);
		for (int i = 0; i < addons.length; i++) {
			output.writeString(addons[i]);
			output.writeString(versions[i]);
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
