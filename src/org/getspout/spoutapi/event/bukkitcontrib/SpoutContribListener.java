package org.getspout.spoutapi.event.bukkitcontrib;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

public class SpoutContribListener extends CustomEventListener implements Listener{

	public SpoutContribListener() {

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
