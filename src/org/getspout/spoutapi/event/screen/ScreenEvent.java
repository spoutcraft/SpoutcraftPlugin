package org.getspout.spoutapi.event.screen;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.player.SpoutPlayer;

public abstract class ScreenEvent extends Event implements Cancellable{
	protected Screen screen;
	protected SpoutPlayer player;
	protected boolean cancel = false;
	protected ScreenEvent(String name, SpoutPlayer player, Screen screen) {
		super(name);
		this.screen = screen;
		this.player = player;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public SpoutPlayer getPlayer() {
		return player;
	}
	
	public boolean isCancelled(){
		return cancel;
	}
	
	public void setCancelled(boolean cancel) {
		this.cancel = true;
	}

}
