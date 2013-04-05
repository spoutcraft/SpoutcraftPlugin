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
package org.getspout.spout.command;

import net.minecraft.server.v1_5_R2.MinecraftServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_5_R2.CraftServer;
import org.bukkit.entity.Player;

import org.getspout.spout.Spout;
import org.getspout.spout.config.ConfigReader;
import org.getspout.spout.player.SpoutCraftPlayer;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SpoutCommand implements CommandExecutor {
	private final Spout p;
	private String motd_temp = null;
	private int motd_task = 0;

	public SpoutCommand(Spout p) {
		this.p = p;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("[SpoutPlugin] Server version: " + p.getDescription().getVersion());
			return true;
		}

		String c = args[0];
		if (c.equals("version")) {
			sender.sendMessage("[SpoutPlugin] Server version: " + p.getDescription().getVersion());

			CommandSender target = sender;

			if (args.length > 1) {
				target = p.getServer().getPlayer(args[1]);
				if (target == null) {
					sender.sendMessage("[SpoutPlugin] Unknown player: " + args[1]);
					return true;
				}
			}

			if (!(target instanceof Player)) {
				sender.sendMessage("[SpoutPlugin] Client version: no client");
			}
			if (!(target instanceof SpoutPlayer)) {
				sender.sendMessage("[SpoutPlugin] Client version: standard client");
			} else {
				SpoutCraftPlayer sp = (SpoutCraftPlayer) target;
				if (!sp.isSpoutCraftEnabled()) {
					sender.sendMessage("[SpoutPlugin] Client version: standard client");
				} else {
					sender.sendMessage("[SpoutPlugin] Client version: " + sp.getVersionString());
				}
			}
			return true;
		}

		if (!sender.isOp()) {
			sender.sendMessage("[SpoutPlugin] This command is Op only");
			return true;
		}

		if (c.equals("waypoint")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("Only players can add waypoints.");
				return true;
			}
			if (args.length > 1) {
				String name = args[1];
				(new ConfigReader()).addWaypoint(name, ((Player) sender).getLocation());
				sender.sendMessage("Waypoint [" + name + "] created successfully");
				return true;
			} else {
				sender.sendMessage("You must give a name to the waypoint.");
				return true;
			}
		}
		if (c.equals("list")) {
			String message = "Players online with " + ChatColor.BLUE + "Spoutcraft" + ChatColor.RESET + ":\n";
			for (Player plr : Bukkit.getOnlinePlayers()) {
				SpoutPlayer splr = SpoutManager.getPlayer(plr);
				if (splr.isSpoutCraftEnabled()) {
					message += ChatColor.GREEN + splr.getName() + ChatColor.RESET + ", ";
				}
			}
			message = message.substring(0, message.length() - 2);
			sender.sendMessage(message);
			return true;
		}
		if (c.equals("reload")) {
			(new ConfigReader()).read();
			sender.sendMessage("Configuration for SpoutPlugin has been reloaded.");
			return true;
		}
		if (c.equals("verify") && args.length > 1) {
			sender.sendMessage("[SpoutPlugin] Temporarily setting the MOTD to: " + args[1]);
			sender.sendMessage("[SpoutPlugin] It will return to its original setting in ~5 mins");
			if (motd_temp == null) {
				motd_temp = MinecraftServer.getServer().getMotd();
			} else {
				Bukkit.getServer().getScheduler().cancelTask(motd_task);
			}
			MinecraftServer.getServer().setMotd(args[1]);
			motd_task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
				@Override
				public void run() {
					MinecraftServer.getServer().setMotd(motd_temp);
					motd_temp = null;
				}
			}, 20 * 60 * 5);
			return true;
		}

		return false;
	}
}
