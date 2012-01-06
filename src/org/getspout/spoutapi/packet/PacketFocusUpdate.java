package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.gui.Control;
import org.getspout.spoutapi.gui.PopupScreen;
import org.getspout.spoutapi.gui.Widget;
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

	public int getNumBytes() {
		return 16 + 1;
	}

	public void readData(DataInputStream input) throws IOException {
		widgetId = new UUID(input.readLong(), input.readLong());
		focus = input.readBoolean();
	}

	public void writeData(DataOutputStream output) throws IOException {
		output.writeLong(control.getId().getMostSignificantBits());
		output.writeLong(control.getId().getLeastSignificantBits());
		output.writeBoolean(focus);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if(player != null) {
			PopupScreen popup = player.getMainScreen().getActivePopup();
			if(popup != null) {
				Widget w = popup.getWidget(widgetId);
				if(w != null && w instanceof Control) {
					((Control)w).setFocus(focus);
				}
			}
		}
	}

	@Override
	public void failure(int playerId) {}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketFocusUpdate;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
