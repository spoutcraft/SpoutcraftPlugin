package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.gui.GenericComboBox;
import org.getspout.spoutapi.gui.Widget;
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
	public int getNumBytes() {
		return 8 + 8 + 1 + 4;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		uuid = new UUID(input.readLong(), input.readLong());
		open = input.readBoolean();
		selection = input.readInt();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeLong(uuid.getMostSignificantBits());
		output.writeLong(uuid.getLeastSignificantBits());
		output.writeBoolean(open);
		output.writeInt(selection);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if(player.getMainScreen().getActivePopup() != null) {
			Widget w = player.getMainScreen().getActivePopup().getWidget(uuid);
			if(w != null && w instanceof GenericComboBox) {
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
