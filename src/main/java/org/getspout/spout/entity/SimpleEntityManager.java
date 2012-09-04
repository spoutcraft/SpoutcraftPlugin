/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
