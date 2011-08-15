package org.getspout.spoutapi.player;

import org.bukkit.block.Biome;
import org.getspout.spoutapi.block.SpoutWeather;

public interface BiomeManager {
	
	public void setPlayerBiomeWeather(SpoutPlayer player, Biome biome, SpoutWeather weather);
	
	public void setPlayerWeather(SpoutPlayer player, SpoutWeather weather);
	
	public void setGlobalBiomeWeather(Biome biome, SpoutWeather weather);
	
	public void setGlobalWeather(SpoutWeather weather);

}
