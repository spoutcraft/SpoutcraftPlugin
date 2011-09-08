package org.getspout.spoutapi.keyboard;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.player.SpoutPlayer;

public interface KeyBindingManager {
	public void registerBinding(String id, Keyboard defaultKey, String description, BindingExecutionDelegate callback, Plugin plugin) throws Exception;
	public void summonKey(String id, Plugin plugin, SpoutPlayer player, Keyboard key, ScreenType screenType, boolean pressed);
}
