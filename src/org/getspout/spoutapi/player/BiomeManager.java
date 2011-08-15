package org.getspout.spoutapi.player;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.block.SpoutWeather;

public interface BiomeManager {
	
	/**
	 * Sets a players weather in a specific Biome to render as the specified SpoutWeather
	 * 
	 * @param player to set the biomes weather for
	 * @param biome that gets set to the weather for the player
	 * @param weather to set the biome to
	 */
	public void setPlayerBiomeWeather(SpoutPlayer player, Biome biome, SpoutWeather weather);
	
	/**
	 * Sets a players weather in all biomes to render as the specified SpoutWeather
	 * 
	 * @param player to set the worlds weather for
	 * @param weather to set the world to
	 */
	public void setPlayerWeather(SpoutPlayer player, SpoutWeather weather);
	
	/**
	 * Sets the whole servers weather in a specific Biome to render as the specified SpoutWeather
	 * 
	 * @param biome that gets set to the weather for the world
	 * @param weather to set the biome to
	 */
	public void setGlobalBiomeWeather(Biome biome, SpoutWeather weather);
	
	/** 
	 * Sets the whole servers weather to render as the specified SpoutWeather
	 * 
	 * @param weather to set the world to
	 */
	public void setGlobalWeather(SpoutWeather weather);
	
	/**
	 * Gets the global weather for the specified biome.
	 * 
	 * @param biome to get the weather for
	 * @return the weather
	 */
	public SpoutWeather getGlobalBiomeWeather(Biome biome);
	
	/**
	 * Gets the players weather for the specified biome.
	 * 
	 * @param player to get the weather for
	 * @param biome to get the weather for
	 * @return the weather
	 */
	public SpoutWeather getPlayerBiomeWeather(Player player, Biome biome);

}
