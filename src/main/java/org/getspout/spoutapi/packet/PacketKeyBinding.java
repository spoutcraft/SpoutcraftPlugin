/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.packet;

import java.io.IOException;
import java.util.UUID;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.keyboard.KeyBinding;
import org.getspout.spoutapi.keyboard.Keyboard;

public class PacketKeyBinding implements SpoutPacket {
	KeyBinding binding;
	Keyboard key;
	String id;
	String plugin;
	boolean pressed;
	int screen;
	UUID uniqueId;

	public PacketKeyBinding() {
	}

	public PacketKeyBinding(KeyBinding binding) {
		this.binding = binding;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		key = Keyboard.getKey(input.readInt());
		pressed = input.readBoolean();
		uniqueId = new UUID(input.readLong(), input.readLong());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeString(binding.getId());
		output.writeString(binding.getDescription());
		output.writeString(binding.getPlugin().getDescription().getName());
		output.writeInt(binding.getDefaultKey().getKeyCode());
		output.writeLong(binding.getUniqueId().getMostSignificantBits());
		output.writeLong(binding.getUniqueId().getLeastSignificantBits());
	}

	@Override
	public void run(int playerId) {
		SpoutManager.getKeyBindingManager().summonKey(uniqueId, SpoutManager.getPlayerFromId(playerId), key, pressed);
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketKeyBinding;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
