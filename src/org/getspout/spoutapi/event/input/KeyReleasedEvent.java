package org.getspout.spoutapi.event.input;

import org.bukkit.event.Event;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class KeyReleasedEvent extends Event{
	private SpoutPlayer player;
	private Keyboard key;
	public KeyReleasedEvent(int keyPress, SpoutPlayer player) {
		super("KeyReleasedEvent");
		this.player = player;
		this.key = Keyboard.getKey(keyPress);
	}
	
	public SpoutPlayer getPlayer() {
		return player;
	}
	
	public Keyboard getKey() {
		return key;
	}
}
