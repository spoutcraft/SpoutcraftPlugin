/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
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
package org.getspout.spoutapi.material;

import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.player.SpoutPlayer;

public interface CustomArmor extends Armor {
	/**
	 * Gets the Custom ID number associated with this CustomArmor
	 * @return customId
	 */
	public int getCustomId();

	/**
	 * Gets the full name of this CustomArmor, which is plugin name + item name
	 * @return fullName
	 */
	public String getFullName();

	/**
	 * Gets the plugin associated with this CustomArmor.
	 * @return plugin
	 */
	public Plugin getPlugin();

	/**
	 * Sets the URLs of the textures on the armor
	 * @param texture
	 * @return this
	 */
	public CustomArmor setItemTexture(String texture);
	
	public CustomArmor setArmorTexture(String texture);
	
	/**
	 * Sets the slot of the armor piece
	 * @param slot
	 * @return this
	 */
	public CustomArmor setSlot(short slot);
	
	public short getSlot();

	/**
	 * Gets the URLs for the textures
	 * @return texture
	 */
	public String getItemTexture();
	
	public String getArmorTexture();

	public CustomArmor setStackable(boolean stackable);

	public boolean isStackable();

	/**
	 * Gets the next short. Starts at Short.MIN_VALUE and loopss back at Short.MAX_VALUE. This is used internally.
	 */
	public short getCounter();

	/**
	 * Occurs when a player right clicks on a block face of a air block in game, while holding this item
	 * <p/>
	 * Returns true if the item interaction was allowed, false if it was disallowed
	 * @param player who right clicked
	 * @param block  that was clicked
	 * @param face   that was clicked on
	 */
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block, BlockFace face);
}