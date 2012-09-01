package org.getspout.spout.entity;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.getspout.spoutapi.entity.EntityManager;
import org.getspout.spoutapi.entity.EntityTemplate;
import org.getspout.spoutapi.entity.SpoutEntity;
import org.getspout.spoutapi.entity.ai.SpoutAI;

public class SimpleEntityManager implements EntityManager{
	private Map<Integer, SpoutEntity> spawned = new HashMap<Integer, SpoutEntity>();

	@Override
	public void registerEntityTemplate(EntityTemplate template) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isSpoutEntity(Entity entity) {
		return getNMSEntity(entity) instanceof EntityBase;
	}

	@Override
	public SpoutEntity getSpoutEntity(Entity entity) {
		if(!(isSpoutEntity(entity))) {
			return null;
		}
		return spawned.get(entity.getEntityId());
		
	}

	@Override
	public SpoutEntity spawnEntity(Location loc, EntityTemplate template) {
		
		EntityBase toSpawn = new EntityBase(loc.getWorld());
		((CraftWorld) loc.getWorld()).getHandle().addEntity(toSpawn, SpawnReason.NATURAL);
		
		SpoutcraftEntity toReturn = new SpoutcraftEntity(toSpawn);
		for(SpoutAI sai : template.getTasks()) {
			toReturn.addTask(sai);
		}
		spawned.put(toSpawn.id, toReturn);
		
		return toReturn;
		
	}
	
	private net.minecraft.server.Entity getNMSEntity(Entity toHandle) {
		return ((CraftEntity) toHandle).getHandle();
	}
	
	private net.minecraft.server.World getNMSWorld(World toHandle) {
		return ((CraftWorld) toHandle).getHandle();
	}
	
}
