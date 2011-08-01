package org.getspout.spoutapi.event.input;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.getspout.spoutapi.player.SpoutPlayer;

public class RenderDistanceChangeEvent extends Event implements Cancellable{
	protected RenderDistance newView;
	protected SpoutPlayer player;
	protected boolean cancel = false;
	public RenderDistanceChangeEvent(SpoutPlayer player, RenderDistance newView) {
		super("RenderDistanceChangeEvent");
		this.player = player;
		this.newView = newView;
	}
	
	public RenderDistance getCurrentRenderDistance() {
		return player.getRenderDistance();
	}
	
	public RenderDistance getNewRenderDistance() {
		return newView;
	}
	
	public boolean isCancelled() {
		return cancel;
	}
	
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

}
