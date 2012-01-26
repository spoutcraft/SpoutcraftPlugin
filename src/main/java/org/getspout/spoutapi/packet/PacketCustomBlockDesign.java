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

import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericBlockDesign;

public class PacketCustomBlockDesign implements SpoutPacket {
	private short customId;
	private BlockDesign design;

	public PacketCustomBlockDesign() {
	}

	public PacketCustomBlockDesign(short customId, BlockDesign design) {
		this.design = design;
		this.customId = customId;
	}

	public int getNumBytes() {
		int designBytes = (design == null) ? design.getResetNumBytes() : design.getNumBytes();
		return designBytes + 2;
	}

	public void readData(DataInputStream input) throws IOException {
		customId = input.readShort();
		design = new GenericBlockDesign();
		design.read(input);
		if (design.getReset()) {
			design = null;
		}
	}

	public void writeData(DataOutputStream output) throws IOException {
		output.writeShort(customId);
		if (design != null) {
			design.write(output);
		} else {
			new GenericBlockDesign().writeReset(output);
		}
	}

	public void run(int id) {
	}

	@Override
	public void failure(int id) {

	}

	public PacketType getPacketType() {
		return PacketType.PacketCustomBlockDesign;
	}

	@Override
	public int getVersion() {
		return design.getVersion() + 2;
	}
}
