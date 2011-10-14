package org.getspout.spoutapi.keyboard;

import java.util.UUID;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.player.SpoutPlayer;

public interface KeyBindingManager {
	/**
	 * Registers a new kind binding
	 * @param id unique id that represents your binding. IT MUST BE UNIQUE!
	 * @param defaultKey for this binding.
	 * @param description of this key binding, that players will see.
	 * @param callback the class that will receive the event callbacks.
	 * @param plugin that registered this binding.
	 * @throws IllegalArgumentException if the id passed is not unique.
	 */
	public void registerBinding(String id, Keyboard defaultKey, String description, BindingExecutionDelegate callback, Plugin plugin) throws IllegalArgumentException;
	
	/**
	 * Internal use only
	 */
	public void summonKey(UUID uniqueId, SpoutPlayer player, Keyboard key, boolean pressed);
}
