/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
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
package org.getspout.spout.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.getspout.spout.Spout;
import org.getspout.spout.precache.PrecacheManager;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PluginListener implements Listener {
	public PluginListener(Spout plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPluginDisable(PluginDisableEvent event) {
		for (Player i : Bukkit.getServer().getOnlinePlayers()) {
			SpoutPlayer p = SpoutManager.getPlayer(i);
			p.getMainScreen().removeWidgets(event.getPlugin());
		}
	}

	/**
	 * Handle the precache setup after all plugins have loaded
	 * @param event
	 */
	@EventHandler
	public void onPluginEnabled(PluginEnableEvent event) {
		PrecacheManager.onPluginEnabled(event.getPlugin());
	}

}
