package org.getspout.spoutapi.event.input;

import org.bukkit.event.Event;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class KeyReleasedEvent extends Event{
	private static final long serialVersionUID = 6700643745353278739L;
	private SpoutPlayer player;
	private Keyboard key;
	private ScreenType screenType;
	public KeyReleasedEvent(int keyPress, SpoutPlayer player, ScreenType screenType) {
		super("KeyReleasedEvent");
		this.player = player;
		this.key = Keyboard.getKey(keyPress);
		this.screenType = screenType;
	}
	
	public SpoutPlayer getPlayer() {
		return player;
	}
	
	public Keyboard getKey() {
		return key;
	}
	
	public ScreenType getScreenType(){
		return screenType;
	}
}
