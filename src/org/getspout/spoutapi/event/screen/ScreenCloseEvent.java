package org.getspout.spoutapi.event.screen;

import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ScreenCloseEvent extends ScreenEvent{
	protected Screen screen;
	protected boolean cancel = false;
	public ScreenCloseEvent(SpoutPlayer player, Screen screen) {
		super("ScreenCloseEvent", player, screen);
	}
}
