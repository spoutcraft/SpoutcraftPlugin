package org.getspout.spoutapi.keyboard;

import java.util.UUID;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.player.SpoutPlayer;

public interface KeyBindingManager {
	public void registerBinding(String id, Keyboard defaultKey, String description, BindingExecutionDelegate callback, Plugin plugin) throws Exception;
	public void summonKey(UUID uniqueId, SpoutPlayer player, Keyboard key, boolean pressed);
}
