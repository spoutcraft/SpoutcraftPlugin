package org.getspout.spoutapi.event.spout;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public class SpoutListener extends CustomEventListener implements Listener{

	public SpoutListener() {

	}
	
	public void onSpoutCraftEnable(SpoutCraftEnableEvent event) {
 
	}
	
	public void onServerTick(ServerTickEvent event) {
		
	}

	@Override
	public void onCustomEvent(Event event) {
		if (event instanceof SpoutCraftEnableEvent) {
			onSpoutCraftEnable((SpoutCraftEnableEvent)event);
		}
		else if (event instanceof ServerTickEvent) {
			onServerTick((ServerTickEvent)event);
		}
	}

}
