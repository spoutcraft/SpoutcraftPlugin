/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.plugin;

import java.util.logging.Logger;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.SpoutServer;

@Deprecated
public abstract class SpoutPlugin extends JavaPlugin {
	public SpoutServer getSpoutServer() {
		return Spout.getServer();
	}

	public SpoutManager getSpoutManager() {
		return SpoutManager.getInstance();
	}

	public void registerEvents(Listener listener) {
		getSpoutServer().getPluginManager().registerEvents(listener, this);
	}

	public String getVersion() {
		return getDescription().getVersion();
	}

	public void log(String s) {
		getLogger().info(String.format("[%s] %s", getName(), s));
	}

	public void log(String s, String... args) {
		getLogger().info(String.format("[%s] %s", getName(), String.format(s, (Object[]) args)));
	}
}
