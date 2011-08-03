package org.getspout.spoutapi.player;

import java.util.UUID;

import org.bukkit.entity.Player;

public interface PlayerManager {
	
	public SpoutPlayer getPlayer(Player player);
	
	public SpoutPlayer getPlayer(UUID id);
	
	public SpoutPlayer getPlayer(int entityId);

}
