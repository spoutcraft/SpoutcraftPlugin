/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.event.inventory;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.SpoutEvent;

public class InventoryListener extends CustomEventListener implements Listener{
	
	public InventoryListener() {

	}

	public void onInventoryClose(InventoryCloseEvent event) {

	}

	public void onInventoryOpen(InventoryOpenEvent event) {

	}

	public void onInventoryClick(InventoryClickEvent event) {

	}

	public void onInventoryCraft(InventoryCraftEvent event) {

	}

	public void onPlayerEnchant(InventoryPlayerEnchantEvent event) {
	        
	}
	
	@Override
	public void onCustomEvent(Event event) {
		if (event instanceof SpoutEvent) {
			switch (((SpoutEvent) event).getEventType()) {
				case Inventory_Close:
					onInventoryClose((InventoryCloseEvent)event);
					break;
				case Inventory_Open:
					onInventoryOpen((InventoryOpenEvent)event);
					break;
				case Inventory_Click:
					onInventoryClick((InventoryClickEvent)event);
					break;
				case Inventory_Craft:
					onInventoryCraft((InventoryCraftEvent)event);
					break;	
	            case Player_Enchant:
	                onPlayerEnchant((InventoryPlayerEnchantEvent)event);
	                break;
			}
		}
	}
	

}
