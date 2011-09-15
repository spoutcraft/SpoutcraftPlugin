package org.getspout.spoutapi.event.permission;

import org.bukkit.event.Event;
import org.bukkit.permissions.Permission;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PlayerPermissionEvent extends Event implements PermissionEvent{
	protected SpoutPlayer player;
	protected Permission perm;
	protected String permission;
	protected boolean result;
	protected final boolean defaultResult;
	public PlayerPermissionEvent(SpoutPlayer player, String permission, Permission perm, boolean defaultResult) {
		super("PlayerPermissionEvent");
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
}
