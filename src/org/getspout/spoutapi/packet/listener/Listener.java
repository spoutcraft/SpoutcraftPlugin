/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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