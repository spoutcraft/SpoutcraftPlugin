package org.getspout.spoutapi.chunkstore;

import org.getspout.spoutapi.io.FlatFileStore;
import org.getspout.spoutapi.util.UniqueItemStringMap;

public class WorldGlobalItemMapConverter {

	private final int[] worldToGlobalMap;
	private final int[] globalToWorldMap;
	
	private final FlatFileStore worldItemMap;
	
	private int nextFree = 1024;
	
	WorldGlobalItemMapConverter(FlatFileStore worldItemMap) {
		worldToGlobalMap = new int[65536];
		globalToWorldMap = new int[65536];
		for (int x = 0; x < 65536; x++) {
			worldToGlobalMap[x] = 0;
			globalToWorldMap[x] = 0;
		}
		this.worldItemMap = worldItemMap;
	}
	
	public Integer getWorldItemId(int globalId) {
		
		if (globalId == 0) {
			return 0;
		}
		
		Integer worldId = globalToWorldMap[globalId];
		
		if (worldId != 0) {
			return worldId;
		} else {
			String key = UniqueItemStringMap.getString(globalId);
			if (key == null) {
				return null;
			}
			worldId = worldItemMap.get(key);
			if (worldId == null) {
				while (nextFree < 65536 && worldItemMap.reverseGet(nextFree) != null) {
					nextFree++;
				}
				if (nextFree == 65536) {
					return null;
				}
				worldItemMap.set(key, nextFree);
				worldId = nextFree;
			}
			globalToWorldMap[globalId] = worldId;
			worldToGlobalMap[worldId] = globalId;
			return worldId;
		}

	}
	
	public Integer getGlobalItemId(int worldId) {
		
		if (worldId == 0) {
			return 0;
		}
		
		Integer globalId = worldToGlobalMap[worldId];
		
		if (globalId != 0) {
			return globalId;
		} else {
			String key = worldItemMap.reverseGet(worldId);
			if (key == null) {
				return null;
			}
			globalId = UniqueItemStringMap.getId(key);
			
			globalToWorldMap[globalId] = worldId;
			worldToGlobalMap[worldId] = globalId;
			return globalId;
		}
		
		
	}
	
	public void save() {
		worldItemMap.save();
	}
	
	
}
