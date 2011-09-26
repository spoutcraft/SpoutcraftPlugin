package org.getspout.spoutapi.event.input;

import org.bukkit.event.Event;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.KeyBinding;
import org.getspout.spoutapi.player.SpoutPlayer;

public class KeyBindingEvent extends Event {
	private static final long serialVersionUID = -6638017626616476366L;
	KeyBinding binding;
	SpoutPlayer player;
	public KeyBindingEvent(SpoutPlayer player, KeyBinding binding) {
		super("keybindingevent");
		this.binding = binding;
		this.player = player;
	}
	public KeyBinding getBinding() {
		return binding;
	}
	public SpoutPlayer getPlayer() {
		return player;
	}
	//Convenience method
	public ScreenType getScreenType(){
		return player.getActiveScreen();
	}
}
