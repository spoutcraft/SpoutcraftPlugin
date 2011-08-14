package org.getspout.spoutapi.event.screen;

import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ScreenOpenEvent extends ScreenEvent{
	public ScreenOpenEvent(SpoutPlayer player, Screen screen, ScreenType type) {
		super("ScreenOpenEvent", player, screen, type);
		if (type == ScreenType.PLAYER_INVENTORY || type == ScreenType.CHEST_INVENTORY || type == ScreenType.DISPENSER_INVENTORY || type == ScreenType.FURNACE_INVENTORY || type == ScreenType.WORKBENCH_INVENTORY) {
			this.setCancelled(player.sendInventoryEvent());
		}
	}
}
