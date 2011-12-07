package org.getspout.spoutapi.material;

import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

public interface CustomItem extends Item {
	
	/**
	 * Gets the Custom ID number associated with this CustomItem
	 * 
	 * @return customId
	 */
	public int getCustomId();
	
	/**
	 * Gets the full name of this CustomItem, which is plugin name + item name
	 * 
	 * @return fullName
	 */
	public String getFullName();
	
	
	/**
	 * Gets the plugin associated with this CustomItem.
	 * 
	 * @return plugin
	 */
	public Plugin getPlugin();
	
	/**
	 * Sets the URL of the texture on the item
	 * @param texture
	 * @return this
	 */
	public CustomItem setTexture(String texture);
	
	/**
	 * Gets the URL for the texture
	 * @return texture
	 */
	public String getTexture();
	
	/**
	 * Occurs when a player right clicks on a block face of a air block in game, while holding this item
	 * 
	 * Returns true if the item interaction was allowed, false if it was disallowed
	 * 
	 * @param player who right clicked
	 * @param block that was clicked
	 * @param face that was clicked on
	 */
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, BlockFace face);
}
