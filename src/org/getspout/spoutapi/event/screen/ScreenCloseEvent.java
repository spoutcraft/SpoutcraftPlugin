package org.getspout.spoutapi.event.screen;

import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ScreenCloseEvent extends ScreenEvent{
	public ScreenCloseEvent(SpoutPlayer player, Screen screen, ScreenType type) {
		super("ScreenCloseEvent", player, screen, type);
	}
}
