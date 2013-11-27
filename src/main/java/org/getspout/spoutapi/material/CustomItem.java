/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
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

public interface CustomItem extends Item {
	/**
	 * Gets the Custom ID number associated with this CustomItem
	 * @return customId
	 */
	public int getCustomId();

	/**
	 * Gets the full name of this CustomItem, which is plugin name + item name
	 * @return fullName
	 */
	public String getFullName();

	/**
	 * Gets the plugin associated with this CustomItem.
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

	public CustomItem setStackable(boolean stackable);

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
