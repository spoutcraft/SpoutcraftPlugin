package org.getspout.spoutapi.entity;

import org.bukkit.entity.LivingEntity;
import org.getspout.spoutapi.player.SpoutPlayer;

public interface EntityManager {
	public void setTexture(SpoutPlayer player, LivingEntity entity, String texture);

	public void setAlternateTexture(SpoutPlayer player, LivingEntity entity, String texture);
}
