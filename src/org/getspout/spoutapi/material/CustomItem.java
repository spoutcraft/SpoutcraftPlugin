package org.getspout.spoutapi.material;

import org.bukkit.plugin.Plugin;

public interface CustomItem extends Item {
	
	public int getCustomId();
	
	public String getFullName();
	
	public Plugin getPlugin();
	
	public CustomItem setTexture(String texture);
	
	public String getTexture();
}
