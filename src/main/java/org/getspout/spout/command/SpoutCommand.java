/*
 * This file is part of SpoutPlugin (http://www.spout.org/).
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

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Player;

import org.getspout.spout.Spout;
import org.getspout.spout.config.ConfigReader;
import org.getspout.spout.player.SpoutCraftPlayer;
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
			sender.sendMessage("[Spout] Server version: " + p.getDescription().getVersion());
			return true;
		}

		if (!sender.isOp()) {
			sender.sendMessage("[Spout] This command is Op only");
			return true;
		}

		String c = args[0];

		if (c.equals("reload")) {
			(new ConfigReader()).read();
			sender.sendMessage("Configuration for Spout has been reloaded.");
			return true;
		}
		if (c.equals("version")) {
			sender.sendMessage("[Spout] Server version: " + p.getDescription().getVersion());

			CommandSender target = sender;

			if (args.length > 1) {
				target = p.getServer().getPlayer(args[1]);
				if (target == null) {
					sender.sendMessage("[Spout] Unknown player: " + args[1]);
					return true;
				}
			}

			if (!(target instanceof Player)) {
				sender.sendMessage("[Spout] Client version: no client");
			}
			if (!(target instanceof SpoutPlayer)) {
				sender.sendMessage("[Spout] Client version: standard client");
			} else {
				SpoutCraftPlayer sp = (SpoutCraftPlayer) target;
				if (!sp.isSpoutCraftEnabled()) {
					sender.sendMessage("[Spout] Client version: standard client");
				} else {
					sender.sendMessage("[Spout] Client version: " + sp.getVersionString());
				}
			}
			return true;
		}
		if (c.equals("verify") && args.length > 1) {
			sender.sendMessage("[Spout] Temporarily setting the motd to: " + args[1]);
			sender.sendMessage("[Spout] It will return to its original setting in ~5 mins");
			if (motd_temp == null) {
				motd_temp = ((CraftServer) Bukkit.getServer()).getHandle().server.motd;
			} else {
				Bukkit.getServer().getScheduler().cancelTask(motd_task);
			}
			((CraftServer) Bukkit.getServer()).getHandle().server.motd = args[1];
			motd_task = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(p, new Runnable() {
				@Override
				public void run() {
					((CraftServer) Bukkit.getServer()).getHandle().server.motd = motd_temp;
					motd_temp = null;
				}
			}, 20 * 60 * 5);
			return true;
		}

		return false;
	}
}
