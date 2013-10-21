/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
package org.getspout.spoutapi.event.permission;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.permissions.Permission;

import org.getspout.spoutapi.player.SpoutPlayer;

public class PlayerPermissionEvent extends Event implements PermissionEvent {
	private static final HandlerList handlers = new HandlerList();
	private final SpoutPlayer player;
	private final Permission perm;
	private final String permission;
	private boolean result;
	private final boolean defaultResult;

	public PlayerPermissionEvent(SpoutPlayer player, String permission, Permission perm, boolean defaultResult) {
		this.player = player;
		this.permission = permission;
		this.perm = perm;
		this.result = defaultResult;
		this.defaultResult = defaultResult;
	}

	@Override
	public boolean getResult() {
		return result;
	}

	@Override
	public void setResult(boolean result) {
		this.result = result;
	}

	@Override
	public boolean getDefaultResult() {
		return defaultResult;
	}

	/**
	 * Gets the player involved in this permission event
	 * @return player
	 */
	public SpoutPlayer getPlayer() {
		return player;
	}

	/**
	 * Gets the permission string being checked against. Should never be null.
	 * @return permission string
	 */
	public String getPermissionString() {
		return permission;
	}

	/**
	 * Gets the permission being checked against. May be null if the permission is not set.
	 * @return permission
	 */
	public Permission getPermission() {
		return perm;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
