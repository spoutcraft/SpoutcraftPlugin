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
import java.util.UUID;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.gui.GenericComboBox;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketComboBox implements SpoutPacket {
	private GenericComboBox box;
	private UUID uuid;
	private boolean open;
	private int selection;

	public PacketComboBox() {

	}

	public PacketComboBox(GenericComboBox box) {
		this.box = box;
		this.uuid = box.getId();
		this.open = box.isOpen();
		this.selection = box.getSelectedRow();
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		uuid = new UUID(input.readLong(), input.readLong());
		open = input.readBoolean();
		selection = input.readInt();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeLong(uuid.getMostSignificantBits());
		output.writeLong(uuid.getLeastSignificantBits());
		output.writeBoolean(open);
		output.writeInt(selection);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if (player.getMainScreen().getActivePopup() != null) {
			Widget w = player.getMainScreen().getActivePopup().getWidget(uuid);
			if (w != null && w instanceof GenericComboBox) {
				box = (GenericComboBox) w;
				box.setOpen(open, false);
				box.setSelection(selection);
			}
		}
	}

	@Override
	public void failure(int playerId) {}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketComboBox;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
