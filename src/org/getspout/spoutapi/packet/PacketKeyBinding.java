package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.KeyBinding;
import org.getspout.spoutapi.keyboard.Keyboard;

public class PacketKeyBinding implements SpoutPacket {

	KeyBinding binding;
	Keyboard key;
	String id;
	String plugin;
	boolean pressed;
	int screen;

	public PacketKeyBinding(){

	}

	public PacketKeyBinding(KeyBinding binding){
		this.binding = binding;
	}

	@Override
	public int getNumBytes() {
		if(binding == null)
		{
			return 0;
		}
		return PacketUtil.getNumBytes(binding.getId()) + PacketUtil.getNumBytes(binding.getPlugin().getDescription().getName()) + 4 + PacketUtil.getNumBytes(binding.getDescription());
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		id = PacketUtil.readString(input);
		plugin = PacketUtil.readString(input);
		key = Keyboard.getKey(input.readInt());
		pressed = input.readBoolean();
		screen = input.readInt();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, binding.getId());
		PacketUtil.writeString(output, binding.getDescription());
		PacketUtil.writeString(output, binding.getPlugin().getDescription().getName());
		output.writeInt(binding.getDefaultKey().getKeyCode());
	}

	@Override
	public void run(int playerId) {
		SpoutManager.getKeyBindingManager().summonKey(id, Bukkit.getServer().getPluginManager().getPlugin(plugin), SpoutManager.getPlayerFromId(playerId), key, ScreenType.getType(screen), pressed);
	}

	@Override
	public void failure(int playerId) {}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketKeyBinding;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
