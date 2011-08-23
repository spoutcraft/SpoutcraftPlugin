package org.getspout.spoutapi.permission;

import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachment;

public interface SpoutPermissible extends Permissible {
	
	public boolean hasAttachment(PermissionAttachment attachment);

}
