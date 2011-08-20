package org.getspout.spoutapi.packet.listener;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.packet.standard.MCPacket;

public class FakeListener implements PacketListener {
	
	@SuppressWarnings("deprecation")
	public Listener listener;
	
	@SuppressWarnings("deprecation")
	FakeListener(Listener listener) {
		this.listener = listener;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean checkPacket(Player player, MCPacket packet) {
		Object raw = packet.getPacket();
		return listener.checkPacket(player, raw);
	}

}
