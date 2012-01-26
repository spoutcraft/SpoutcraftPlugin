/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.keyboard;

import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.player.SpoutPlayer;

@Deprecated
public interface KeyboardManager {
	/**
	 * Get's the number of key bindings associated with this key
	 * @param key to check against
	 * @return number of key bindings
	 * @deprecated use KeyBindingManager instead, it provides more flexibility to the user.
	 */
	@Deprecated
	public int getNumKeyBindings(Keyboard key);

	/**
	 * Adds a key binding to a particular key
	 * @param key to bind to
	 * @param keyBinding to bind to the key
	 * @param plugin for this binding
	 */
	@Deprecated
	public void addKeyBinding(Keyboard key, KeyboardBinding keyBinding, Plugin plugin);

	/**
	 * Removes a key binding from a particular key
	 * @param key to remove the binding from
	 * @param keyBindingClass that the keyboardbinding is an instanceof
	 * @param plugin for this binding
	 */
	@Deprecated
	public void removeKeyBinding(Keyboard key, Class<? extends KeyboardBinding> keyBindingClass, Plugin plugin);

	/**
	 * Removes all the keyboard bindings associated with this particular plugin. Automatically called onPluginDisable.
	 * @param plugin to remove key bindings for
	 */
	@Deprecated
	public void removeAllKeyBindings(Plugin plugin);

	public void onPreKeyPress(Keyboard pressed, SpoutPlayer player);

	public void onPostKeyPress(Keyboard pressed, SpoutPlayer player);

	public void onPreKeyRelease(Keyboard pressed, SpoutPlayer player);

	public void onPostKeyRelease(Keyboard pressed, SpoutPlayer player);
}
