package org.getspout.spoutapi.event.spout;

import org.bukkit.event.Event;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutCraftEnableEvent extends Event{
	private static final long serialVersionUID = 5172371431162815630L;
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
