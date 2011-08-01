package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketClipboardText implements SpoutPacket{
	
	public PacketClipboardText() {
		
	}
	
	public PacketClipboardText(String text) {
		this.text = text;
	}
	protected String text;
	@Override
	public int getNumBytes() {
		return PacketUtil.getNumBytes(text);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		text = PacketUtil.readString(input);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		if (text.length() > PacketUtil.maxString) {
			text = text.substring(0, PacketUtil.maxString - 1);
		}
		PacketUtil.writeString(output, text);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		player.setClipboardText(text, false);
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketClipboardText;
	}

}
