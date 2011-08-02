package org.getspout.spoutapi.packet.listener;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.packet.standard.MCPacket;

/**
 * @author Nightgunner5
 */
public interface PacketListener {
	/**
	 * @param player The player the packet is sent to
	 * @param packet The packet to check
	 * @return false if the packet should be stopped, true otherwise.
	 */
	public boolean checkPacket(Player player, MCPacket packet);
}
