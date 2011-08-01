package org.getspout.spoutapi.event.bukkitcontrib;

import org.bukkit.event.Event;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutCraftEnableEvent extends Event{
	private SpoutPlayer player;
	public SpoutCraftEnableEvent(SpoutPlayer player) {
		super("SpoutCraftEnableEvent");
		this.player = player;
	}

	/**
	 * Returns the player who just had their Spout SinglePlayer Mod enabled
	 * @return player
	 */
	public SpoutPlayer getPlayer() {
		return player;
	}
}
