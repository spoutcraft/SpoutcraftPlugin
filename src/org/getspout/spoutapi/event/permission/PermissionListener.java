package org.getspout.spoutapi.event.permission;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public class PermissionListener extends CustomEventListener implements Listener{
	
	public void onPlayerPermission(PlayerPermissionEvent event) {
		
	}

	@Override
	public void onCustomEvent(Event event) {
		if (event instanceof PlayerPermissionEvent) {
			onPlayerPermission((PlayerPermissionEvent)event);
		}
	}
}
