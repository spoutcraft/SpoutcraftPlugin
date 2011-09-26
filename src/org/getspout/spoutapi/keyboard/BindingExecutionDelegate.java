package org.getspout.spoutapi.keyboard;

import org.getspout.spoutapi.event.input.KeyBindingEvent;

/**
 * Implement this interface to provide your custom action what should happen when a user presses a registered keybinding.
 * Register your keybinding using the KeyBindingManager
 * @author narrowtux<narrow.m@narrowtux.com>
 * @see KeyBindingManager
 */
public interface BindingExecutionDelegate {
	public void keyPressed(KeyBindingEvent event);
	public void keyReleased(KeyBindingEvent event);
}
