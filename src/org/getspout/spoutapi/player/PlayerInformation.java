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
