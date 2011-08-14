package org.getspout.spoutapi.packet.listener;

import org.bukkit.entity.Player;

/**
 * @author Nightgunner5
 */
@Deprecated
public interface Listener {
	/**
	 * @param player The player the packet is sent to
	 * @param packet The packet to check
	 * @return false if the packet should be stopped, true otherwise.
	 */
	public boolean checkPacket(Player player, Object packet);
}