package org.getspout.spoutapi.player;

import org.bukkit.block.Biome;

public interface BiomeManager {
	
	public void setPlayerBiomeWeather(SpoutPlayer player, Biome biome, byte weather);

}
