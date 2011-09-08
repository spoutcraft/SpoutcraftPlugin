package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.keyboard.KeyBinding;
import org.getspout.spoutapi.keyboard.Keyboard;

public class PacketKeyBinding implements SpoutPacket {

	KeyBinding binding;
	Keyboard key;
	
	public PacketKeyBinding(KeyBinding binding){
		this.binding = binding;
	}
	
	@Override
	public int getNumBytes() {
		return PacketUtil.getNumBytes(binding.getId()) + PacketUtil.getNumBytes(binding.getPlugin().getDescription().getName()) + 4 + PacketUtil.getNumBytes(binding.getDescription());
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		//BEWARE! packets from the clients have other data!
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
		// TODO Auto-generated method stub

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
