/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spout.packet.builtin;

import java.io.IOException;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.material.Block;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.item.GenericCustomFood;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.material.item.GenericCustomTool;
import org.getspout.spoutapi.packet.SpoutPacket;

public class PacketCustomItem extends SpoutPacket {
	private GenericCustomItem item;

	protected PacketCustomItem() {
	}

	public PacketCustomItem(GenericCustomItem item) {
		this.item = item;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		throw new IOException("The server should not receive custom items from the client (hack?)!");
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		if (item == null) {
			throw new IOException("Attempt made to send a null custom item to the client!");
		}
		output.writeInt(item.getCustomId());
		output.writeString(item.getName());
		output.writeString(item.getPlugin().getName());
		output.writeString(item.getTexture());

		if (item instanceof GenericCustomFood) {
			output.writeInt(((GenericCustomFood) item).getHungerRestored());
		} else if (item instanceof GenericCustomTool) {
			final GenericCustomTool tool = (GenericCustomTool) item;
			output.writeShort(tool.getMaxDurability());
			Block[] mod = tool.getStrengthModifiedBlocks();
			output.writeShort((short) mod.length);
			for (Block block : mod) {
				if (block instanceof CustomBlock) {
					output.writeInt(((CustomBlock) block).getCustomId());
					output.writeShort((short) -1);
				} else {
					output.writeInt(block.getRawId());
					output.writeShort((short) block.getRawData());
				}
				output.writeFloat(tool.getStrengthModifier(block));
			}
		}
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomItem;
	}

	@Override
	public int getVersion() {
		return 1;
	}
}