package org.getspout.spoutapi.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public interface EntityManager {
	
	public void registerEntityTemplate(EntityTemplate template);
	
	public boolean isSpoutEntity(Entity entity);
	
	public SpoutEntity getSpoutEntity(Entity entity);
	
	public SpoutEntity spawnEntity(Location loc, EntityTemplate template);
	
}
