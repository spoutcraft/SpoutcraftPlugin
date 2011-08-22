/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.player;

import java.util.Set;

import org.bukkit.block.Biome;
import org.bukkit.entity.LivingEntity;
import org.getspout.spoutapi.block.SpoutWeather;

public interface PlayerInformation {

	public SpoutWeather getBiomeWeather(Biome biome);
	
	public void setBiomeWeather(Biome biome, SpoutWeather weather);
	
	public Set<Biome> getBiomes();

	public void setEntitySkin(LivingEntity entity, String url);
	
	public String getEntitySkin(LivingEntity entity);
	
	public void setEntitySecondarySkin(LivingEntity entity, String url);
	
	public String getEntitySecondarySkin(LivingEntity entity);
}
