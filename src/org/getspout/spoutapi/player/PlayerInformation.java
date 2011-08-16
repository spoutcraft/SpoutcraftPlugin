package org.getspout.spoutapi.player;

import java.util.Set;

import org.bukkit.block.Biome;
import org.getspout.spoutapi.block.SpoutWeather;

public interface PlayerInformation {

	public SpoutWeather getBiomeWeather(Biome biome);
	
	public void setBiomeWeather(Biome biome, SpoutWeather weather);
	
	public Set<Biome> getBiomes();
	
}
