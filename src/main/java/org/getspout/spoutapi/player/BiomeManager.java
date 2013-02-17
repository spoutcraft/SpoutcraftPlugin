/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
package org.getspout.spoutapi.player;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import org.getspout.spoutapi.ClientOnly;
import org.getspout.spoutapi.block.SpoutWeather;

@Deprecated
public interface BiomeManager {
	/**
	 * Sets a players weather in a specific Biome to render as the specified SpoutWeather
	 * @param player  to set the biomes weather for
	 * @param biome   that gets set to the weather for the player
	 * @param weather to set the biome to
	 */
	@ClientOnly
	public void setPlayerBiomeWeather(SpoutPlayer player, Biome biome, SpoutWeather weather);

	/**
	 * Sets a players weather in all biomes to render as the specified SpoutWeather
	 * @param player  to set the worlds weather for
	 * @param weather to set the world to
	 */
	@ClientOnly
	public void setPlayerWeather(SpoutPlayer player, SpoutWeather weather);

	/**
	 * Sets the whole servers weather in a specific Biome to render as the specified SpoutWeather
	 * @param biome   that gets set to the weather for the world
	 * @param weather to set the biome to
	 */
	@ClientOnly
	public void setGlobalBiomeWeather(Biome biome, SpoutWeather weather);

	/**
	 * Sets the whole servers weather to render as the specified SpoutWeather
	 * @param weather to set the world to
	 */
	@ClientOnly
	public void setGlobalWeather(SpoutWeather weather);

	/**
	 * Gets the global weather for the specified biome.
	 * @param biome to get the weather for
	 * @return the weather
	 */
	@ClientOnly
	public SpoutWeather getGlobalBiomeWeather(Biome biome);

	/**
	 * Gets the players weather for the specified biome.
	 * @param player to get the weather for
	 * @param biome  to get the weather for
	 * @return the weather
	 */
	@ClientOnly
	public SpoutWeather getPlayerBiomeWeather(Player player, Biome biome);
}
