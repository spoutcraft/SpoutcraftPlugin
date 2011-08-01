package org.getspout.spoutapi.event.screen;

import org.getspout.spoutapi.gui.Button;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ButtonClickEvent extends ScreenEvent{

	protected Button control;
	public ButtonClickEvent(SpoutPlayer player, Screen screen, Button control) {
		super("ButtonClickEvent", player, screen);
		this.control = control;
	}
	
	public Button getButton() {
		return control;
	}
}
