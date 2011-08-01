package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.keyboard.KeyboardManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketKeyPress implements SpoutPacket{
	public boolean pressDown;
	public byte key;
	public byte settingKeys[] = new byte[10];
	public int screenType = -1;
	public PacketKeyPress(){
	}

	public PacketKeyPress(byte key, boolean pressDown) {
		this.key = key;
		this.pressDown = pressDown;
	}

	public void readData(DataInputStream datainputstream) throws IOException {
		this.key = datainputstream.readByte();
		this.pressDown = datainputstream.readBoolean();
		this.screenType = datainputstream.readInt();
		for (int i = 0; i < 10; i++) {
			this.settingKeys[i] = datainputstream.readByte();
		}
	}

	public void writeData(DataOutputStream dataoutputstream) throws IOException {
		dataoutputstream.writeByte(this.key);
		dataoutputstream.writeBoolean(this.pressDown);
		dataoutputstream.writeInt(this.screenType);
		for (int i = 0; i < 10; i++) {
			dataoutputstream.writeByte(this.settingKeys[i]);
		}
	}

	public void run(int id) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(id);
		if (player != null) {
			player.updateKeys(settingKeys);
			Keyboard pressed = Keyboard.getKey(this.key);
			KeyboardManager manager = SpoutManager.getKeyboardManager();
			if (pressDown) {
				manager.onPreKeyPress(pressed, player);
				Bukkit.getServer().getPluginManager().callEvent(new KeyPressedEvent(this.key, player, ScreenType.getType(screenType)));
				manager.onPostKeyPress(pressed, player);
			}
			else {
				manager.onPreKeyRelease(pressed, player);
				Bukkit.getServer().getPluginManager().callEvent(new KeyReleasedEvent(this.key, player, ScreenType.getType(screenType)));
				manager.onPostKeyRelease(pressed, player);
			}
		}
	}

	public int getNumBytes()
	{
		return 1 + 1 + 4 + 10;
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketKeyPress;
	}
}
