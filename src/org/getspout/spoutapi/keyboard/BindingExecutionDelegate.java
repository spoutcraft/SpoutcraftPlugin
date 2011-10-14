package org.getspout.spoutapi.keyboard;

import org.getspout.spoutapi.event.input.KeyBindingEvent;

/**
 * Implement this interface to provide your custom action what should happen when a user presses a registered keybinding.
 * Register your keybinding using the KeyBindingManager
 * @see KeyBindingManager
 */
public interface BindingExecutionDelegate {
	/**
	 * Called when the key bound to the delegate is pressed
	 * @param event args
	 */
	public void keyPressed(KeyBindingEvent event);
	/**
	 * Called when the key bound to the delegate is released
	 * @param event args
	 */
	public void keyReleased(KeyBindingEvent event);
}
