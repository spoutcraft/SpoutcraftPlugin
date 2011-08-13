package org.getspout.spoutapi.event.screen;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.getspout.spout.SpoutNetServerHandler;
import org.getspout.spoutapi.event.inventory.InventoryOpenEvent;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ScreenOpenEvent extends ScreenEvent{
	public ScreenOpenEvent(SpoutPlayer player, Screen screen, ScreenType type) {
		super("ScreenOpenEvent", player, screen, type);
		CraftPlayer cp = (CraftPlayer) player;
		SpoutNetServerHandler snsh = (SpoutNetServerHandler) cp.getHandle().netServerHandler;
		if ((type == ScreenType.PLAYER_INVENTORY || type == ScreenType.CHEST_INVENTORY || type == ScreenType.DISPENSER_INVENTORY || type == ScreenType.FURNACE_INVENTORY || type == ScreenType.WORKBENCH_INVENTORY) && !snsh.activeInventory) {
			snsh.activeInventory = true;
			InventoryOpenEvent event = new InventoryOpenEvent(player, player.getInventory(), snsh.getDefaultInventory(), snsh.getActiveInventoryLocation());
			Bukkit.getServer().getPluginManager().callEvent(event);
			this.setCancelled(event.isCancelled());
		}
	}
}
