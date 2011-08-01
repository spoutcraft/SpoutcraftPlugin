package org.getspout.spoutapi.event.screen;

import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ScreenOpenEvent extends ScreenEvent{
	protected Screen screen;
	protected boolean cancel = false;
	public ScreenOpenEvent(SpoutPlayer player, Screen screen) {
		super("ScreenOpenEvent", player, screen);
	}
}
