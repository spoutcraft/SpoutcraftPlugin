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
package org.getspout.spoutapi.plugin;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.SpoutServer;

public abstract class SpoutPlugin extends JavaPlugin {
	public SpoutServer getSpoutServer() {
		return Spout.getServer();
	}

	public SpoutManager getSpoutManager() {
		return SpoutManager.getInstance();
	}

	public void registerEvent(Event.Type type, Listener listener, Priority priority) {
		getSpoutServer().getPluginManager().registerEvent(type, listener, priority, this);
	}

	public void registerEvent(Event.Type type, Listener listener) {
		getSpoutServer().getPluginManager().registerEvent(type, listener, Priority.Normal, this);
	}

	public void registerCustomEvent(Listener listener, Priority priority) {
		getSpoutServer().getPluginManager().registerEvent(Event.Type.CUSTOM_EVENT, listener, priority, this);
	}

	public void registerCustomEvent(Listener listener) {
		getSpoutServer().getPluginManager().registerEvent(Event.Type.CUSTOM_EVENT, listener, Priority.Normal, this);
	}

	public String getVersion() {
		return getDescription().getVersion();
	}

	public String getName() {
		return getDescription().getName();
	}

	public Logger getLogger() {
		return getSpoutServer().getLogger();
	}

	public void log(String s) {
		getLogger().info(String.format("[%s] %s", getName(), s));
	}

	public void log(String s, String... args) {
		getLogger().info(String.format("[%s] %s", getName(), String.format(s, (Object[])args)));
	}
}
