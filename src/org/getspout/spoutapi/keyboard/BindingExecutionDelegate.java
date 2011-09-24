package org.getspout.spoutapi.keyboard;

import org.getspout.spoutapi.event.input.KeyPressedEvent;
import org.getspout.spoutapi.event.input.KeyReleasedEvent;
import org.getspout.spoutapi.gui.ScreenType;

/**
 * Implement this interface to provide your custom action what should happen when a user presses a registered keybinding.
 * Register your keybinding using the KeyBindingManager
 * @author narrowtux<narrow.m@narrowtux.com>
 * @see KeyBindingManager
 */
public interface BindingExecutionDelegate {
	public void keyPressed(KeyPressedEvent event);
	public void keyReleased(KeyReleasedEvent event);
}
