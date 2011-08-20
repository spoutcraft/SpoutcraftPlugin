package org.getspout.spoutapi.event.input;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.getspout.spoutapi.player.RenderDistance;
import org.getspout.spoutapi.player.SpoutPlayer;

public class RenderDistanceChangeEvent extends Event implements Cancellable{
	private static final long serialVersionUID = 3737610397521859191L;
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
