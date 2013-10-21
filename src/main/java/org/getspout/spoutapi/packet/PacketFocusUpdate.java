/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
import org.getspout.spoutapi.gui.Control;
import org.getspout.spoutapi.gui.PopupScreen;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketFocusUpdate implements SpoutPacket {
	private Control control;
	private boolean focus;
	private UUID widgetId;

	public PacketFocusUpdate() {
	}

	public PacketFocusUpdate(Control control, boolean focus) {
		this.control = control;
		this.focus = focus;
	}

	public void readData(SpoutInputStream input) throws IOException {
		widgetId = new UUID(input.readLong(), input.readLong());
		focus = input.readBoolean();
	}

	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeLong(control.getId().getMostSignificantBits());
		output.writeLong(control.getId().getLeastSignificantBits());
		output.writeBoolean(focus);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if (player != null) {
			PopupScreen popup = player.getMainScreen().getActivePopup();
			if (popup != null) {
				Widget w = popup.getWidget(widgetId);
				if (w != null && w instanceof Control) {
					((Control) w).setFocus(focus);
				}
			}
		}
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketFocusUpdate;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
