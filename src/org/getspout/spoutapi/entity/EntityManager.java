package org.getspout.spoutapi.entity;

import org.bukkit.entity.Entity;
import org.getspout.spoutapi.player.SpoutPlayer;

public interface EntityManager {
	public void setTexture(SpoutPlayer player, Entity entity, String texture);

	public void setAlternateTexture(SpoutPlayer player, Entity entity, String texture);
}
