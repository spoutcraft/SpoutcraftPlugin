/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
package org.getspout.spout;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import org.getspout.spout.config.ConfigReader;
import org.getspout.spout.config.PermHandler;
import org.getspout.spout.inventory.SimpleMaterialManager;
import org.getspout.spout.keyboard.SimpleKeyBindingManager;
import org.getspout.spout.player.SimpleBiomeManager;
import org.getspout.spout.player.SimpleSkyManager;
import org.getspout.spout.player.SpoutCraftPlayer;
import org.getspout.spout.precache.PrecacheManager;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.event.spout.SpoutcraftFailedEvent;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.packet.PacketAllowVisualCheats;
import org.getspout.spoutapi.packet.PacketBlockData;
import org.getspout.spoutapi.packet.PacketCustomBlockDesign;
import org.getspout.spoutapi.packet.PacketServerPlugins;
import org.getspout.spoutapi.player.PlayerInformation;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PlayerChunkMap {
	private HashMap<String, Integer> timer = new HashMap<String, Integer>();
	HashMap<String, PlayerInformation> infoMap = new HashMap<String, PlayerInformation>();

	public void onPlayerJoin(Player player) {
		timer.put(player.getName(), ConfigReader.getAuthenticateTicks());
	}

	public void onServerTick() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (timer.containsKey(player.getName())) {
				int ticksLeft = timer.get(player.getName());
				if (--ticksLeft > 0) {
					timer.put(player.getName(), ticksLeft);
				} else {
					timer.remove(player.getName());
					SpoutCraftPlayer scp = (SpoutCraftPlayer)SpoutManager.getPlayer(player);
					Bukkit.getServer().getPluginManager().callEvent(new SpoutcraftFailedEvent(scp));
					scp.queued = null;
					if (player.hasPermission("spout.plugin.ignorespoutcraft")) {
						break;
					}
					if (!player.hasPermission("spout.plugin.forcespoutcraft")) {
						break;
					}
					if (!player.isOp()) {
						System.out.println("[SpoutPlugin] Failed to authenticate " + player.getName() + "'s Spoutcraft client in " + ConfigReader.getAuthenticateTicks() + " server ticks.");
						System.out.println("[SpoutPlugin] Kicking " + player.getName() + " for not running Spoutcraft");
						player.kickPlayer(ConfigReader.getKickMessage());
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void onSpoutcraftEnable(SpoutPlayer player) {
		timer.remove(player.getName());
		//player.sendPacket(new PacketServerPlugins(Bukkit.getServer().getPluginManager().getPlugins()));
		player.updateInventory();

		((SimpleMaterialManager)SpoutManager.getMaterialManager()).onPlayerJoin(player);
		((SimpleSkyManager)SpoutManager.getSkyManager()).onPlayerJoin(player);
		((SimpleBiomeManager)SpoutManager.getBiomeManager()).onPlayerJoin(player);
		((SimpleKeyBindingManager)SpoutManager.getKeyBindingManager()).onPlayerJoin(player);
		player.sendPacket(new PacketAllowVisualCheats(PermHandler.allowSkyCheat(player),PermHandler.forceSkyCheat(player),PermHandler.showSkyCheat(player),PermHandler.allowClearWaterCheat(player),PermHandler.forceClearWaterCheat(player),PermHandler.showClearWaterCheat(player),PermHandler.allowStarsCheat(player),PermHandler.forceStarsCheat(player),PermHandler.showStarsCheat(player),PermHandler.allowWeatherCheat(player),PermHandler.forceWeatherCheat(player),PermHandler.showWeatherCheat(player),PermHandler.allowTimeCheat(player),PermHandler.allowCoordsCheat(player),PermHandler.allowEntityLabelCheat(player),PermHandler.allowVoidFogCheat(player),PermHandler.forceVoidFogCheat(player),PermHandler.showVoidFogCheat(player),PermHandler.allowFlightSpeedCheat(player)));
		player.updatePermissions();
		PrecacheManager.onPlayerJoin(player);
		player.sendPacket(new PacketBlockData(SpoutManager.getMaterialManager().getModifiedBlocks()));
		Bukkit.getServer().getPluginManager().callEvent(new SpoutCraftEnableEvent(player));
		((SpoutCraftPlayer)player).updateWaypoints();
	}
}
