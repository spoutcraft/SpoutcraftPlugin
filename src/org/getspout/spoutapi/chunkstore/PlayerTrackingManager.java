/*
 * This file is part of Spout (http://wiki.getspout.org/).
 * 
 * Spout is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.chunkstore;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PlayerTrackingManager{
	private HashMap<String, PlayerTrackingThread> playerThreads = new HashMap<String, PlayerTrackingThread>();
		
	public void onPlayerJoin(SpoutPlayer player) {
		PlayerTrackingThread thread = new PlayerTrackingThread(player);
		thread.onPlayerJoin();
		playerThreads.put(player.getName(), thread);
	}
	
	public void onWorldChange(SpoutPlayer player) {
		playerThreads.get(player.getName()).onWorldChange();
	}
	
	public void onMoveChunk(SpoutPlayer player) {
		playerThreads.get(player.getName()).onMoveChunk();
	}
	
	public void onPlayerQuit(Player player) {
		playerThreads.remove(player.getName()).onPlayerQuit();
	}
}