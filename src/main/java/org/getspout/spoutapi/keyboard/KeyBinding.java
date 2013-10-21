/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.keyboard;

import java.util.UUID;

import org.bukkit.plugin.Plugin;

public class KeyBinding {
	private String id;
	private Keyboard defaultKey;
	private String description;
	private Plugin plugin;
	private BindingExecutionDelegate delegate;
	private UUID uniqueId;

	public KeyBinding(String id, Keyboard defaultkey, String description, Plugin plugin, BindingExecutionDelegate delegate) {
		this.id = id;
		this.defaultKey = defaultkey;
		this.description = description;
		this.plugin = plugin;
		this.delegate = delegate;
		this.setUniqueId(UUID.randomUUID());
	}

	/**
	 * The unique id for this keybinding
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the unique id for this keybinding
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the default key for this keybinding
	 * @return default key
	 */
	public Keyboard getDefaultKey() {
		return defaultKey;
	}

	/**
	 * Sets the default key for this keybinding
	 * @param defaultKey
	 */
	public void setDefaultKey(Keyboard defaultKey) {
		this.defaultKey = defaultKey;
	}

	/**
	 * Gets the description that will be shown to players about the purpose of this keybinding
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the keybinding
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the plugin associated with this keybinding
	 * @return plugin
	 */
	public Plugin getPlugin() {
		return plugin;
	}

	/**
	 * Sets the plugin associated with this keybinding
	 * @param plugin
	 */
	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}

	/**
	 * Gets the key binding delegate object for this keybinding
	 * @return delegate
	 */
	public BindingExecutionDelegate getDelegate() {
		return delegate;
	}

	/**
	 * Sets the key binding delegate object for this keybinding
	 * @param delegate
	 */
	public void setDelegate(BindingExecutionDelegate delegate) {
		this.delegate = delegate;
	}

	/**
	 * Sets the unique id for this keybinding
	 * @param uniqueId
	 */
	public void setUniqueId(UUID uniqueId) {
		this.uniqueId = uniqueId;
	}

	/**
	 * Gets the unique id of this keybinding
	 * @return
	 */
	public UUID getUniqueId() {
		return uniqueId;
	}
}
