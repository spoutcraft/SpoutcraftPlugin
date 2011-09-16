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
package org.getspout.spoutapi.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.inventory.Inventory;
import org.getspout.spoutapi.ClientOnly;
import org.getspout.spoutapi.gui.InGameHUD;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.packet.standard.MCPacket;

public interface SpoutPlayer extends org.bukkit.entity.Player{
	
	/**
	 * Closes any dialog windows the client may have open at the time
	 * @return true if a window was closed
	 */
	public boolean closeActiveWindow();
	
	/**
	 * Opens an inventory dialog to the player, with the given inventory displayed in the upper pane, and the player's inventory in the lower pane
	 * @param inventory to use in the dialog GUI
	 * @return true if an inventory window was opened
	 */
	public boolean openInventoryWindow(Inventory inventory);
	
	/**
	 * Opens an inventory dialog to the player, with the given inventory displayed in the upper pane, and the player's inventory in the lower pane.
	 * The location is not used, but is passed to other plugins when notifying them of the open window
	 * @param inventory to use in the dialog GUI
	 * @param location that represents this inventory in the world (e.g Chest, Furnace). Use null if there is no physical location.
	 * @return true if an inventory window was opened
	 */
	public boolean openInventoryWindow(Inventory inventory, Location location);
	
	/**
	 * Opens an inventory dialog to the player, with the given inventory displayed in the upper pane, and the player's inventory in the lower pane.
	 * The location is not used, but is passed to other plugins when notifying them of the open window
	 * @param inventory to use in the dialog GUI
	 * @param location that represents this inventory in the world (e.g Chest, Furnace). Use null if there is no physical location.
	 * @param ignoreDistance whether the distance from the inventory should be considered (opening an inventory will fail if it's too far away, without ignoring distance)
	 * @return true if an inventory window was opened
	 */
	public boolean openInventoryWindow(Inventory inventory, Location location, boolean ignoreDistance);
	
	/**
	 * Opens an workbench dialog to the player, using the workbench at the given location
	 * @param location of the workbench to use. Must be a valid workbench.
	 * @return true if a workbench window was opened
	 */
	public boolean openWorkbenchWindow(Location location);
	
	/**
	 * Gets a copy of the in game HUD to attach widget and launch popups from
	 * @return In game HUD
	 */
	public InGameHUD getMainScreen();
	
	/**
	 * Gets a copy of the current opened screen of the player. This can be the InGameHUD, but also other screen types.
	 * Check the screentype with Screen.getType();
	 * @see Screen.getType()
	 * @return the currently open screen
	 */
	public Screen getCurrentScreen();
	
	/**
	 * Return's true if the player is using the spoutcraft single player mod
	 * @return spout craft single player mod enabled
	 */
	public boolean isSpoutCraftEnabled();
	
	/**
	 * Gets the version of the spoutcraft SP client mod in use, or -1 if none.
	 * @return version
	 */
	@Deprecated
	public int getVersion();
	
	/**
	 * Return's the key bound to forward movement for this player, or unknown if not known.
	 * @return forward key
	 */
	@ClientOnly
	public Keyboard getForwardKey();
	
	/**
	 * Return's the key bound to backward movement for this player, or unknown if not known.
	 * @return backward key
	 */
	@ClientOnly
	public Keyboard getBackwardKey();
	
	/**
	 * Return's the key bound to left movement for this player, or unknown if not known.
	 * @return left key
	 */
	@ClientOnly
	public Keyboard getLeftKey();
	
	/**
	 * Return's the key bound to right movement for this player, or unknown if not known.
	 * @return right key
	 */
	public Keyboard getRightKey();
	
	/**
	 * Return's the key bound to jumping for this player, or unknown if not known.
	 * @return jump key
	 */
	@ClientOnly
	public Keyboard getJumpKey();
	
	/**
	 * Return's the key bound to opening and closing inventories for this player, or unknown if not known.
	 * @return inventory key
	 */
	@ClientOnly
	public Keyboard getInventoryKey();
	
	/**
	 * Return's the key bound to forward movement for this player, or unknown if not known.
	 * @return forward key
	 */
	@ClientOnly
	public Keyboard getDropItemKey();
	
	/**
	 * Return's the key bound to opening the chat bar for this player, or unknown if not known.
	 * @return chat key
	 */
	@ClientOnly
	public Keyboard getChatKey();
	
	/**
	 * Return's the key bound to toggle view fog for this player, or unknown if not known.
	 * @return toggle fog key
	 */
	@ClientOnly
	public Keyboard getToggleFogKey();
	
	/**
	 * Return's the key bound to sneaking for this player, or unknown if not known.
	 * @return sneak key
	 */
	@ClientOnly
	public Keyboard getSneakKey();
	
	/**
	 * Gets the render distance that the player views, or null if unknown
	 * @return render distance
	 */
	@ClientOnly
	public RenderDistance getRenderDistance();
	
	/**
	 * Sets the render distance that the player views
	 * @param distance to set
	 */
	@ClientOnly
	public void setRenderDistance(RenderDistance distance);
	
	/**
	 * Gets the maximum render distance that the player can view, or null if unknown
	 * @return maximum distance
	 */
	@ClientOnly
	public RenderDistance getMaximumRenderDistance();
	
	/**
	 * Sets the maximum render distance that the player can view
	 * @param maximum distance
	 */
	@ClientOnly
	public void setMaximumRenderDistance(RenderDistance maximum);
	
	/**
	 * Releases the maximum render distance, and allows the player to change the distance with no maximum restriction
	 */
	@ClientOnly
	public void resetMaximumRenderDistance();
	
	/**
	 * Gets the minimum render distance that the player can view, or null if unknown
	 * @return minimum distance
	 */
	@ClientOnly
	public RenderDistance getMinimumRenderDistance();
	
	/**
	 * Releases the minimum render distance, and allows the player to change the distance with no minimum restriction
	 */
	@ClientOnly
	public void resetMinimumRenderDistance();
	
	/**
	 * Sets the minimum render distance that the player can view
	 * @param minimum distance
	 */
	@ClientOnly
	public void setMinimumRenderDistance(RenderDistance minimum);
	
	/**
	 * Send's the player a notification (using the existing Achievement Get window), with the given title, message, and item to render as a graphic
	 * The title and message may not exceed 26 characters in length
	 * The item to render may not be null
	 * @param title to send
	 * @param message to send
	 * @param toRender to render
	 */
	@ClientOnly
	public void sendNotification(String title, String message, Material toRender);
	
	/**
	 * Send's the player a notification (using the existing Achievement Get window), with the given title, message, and item to render as a graphic
	 * The title and message may not exceed 26 characters in length
	 * The item to render may not be null
	 * @param title to send
	 * @param message to send
	 * @param toRender to render
	 * @param data for the item to render
	 * @param time for the notification to remain in milliseconds
	 */
	@ClientOnly
	public void sendNotification(String title, String message, Material toRender, short data, int time);
		
	/**
	 * Get's the clipboard text from the player, or null if unknown
	 * @return clipboard text
	 */
	@ClientOnly
	public String getClipboardText();
	
	/**
	 * Sets the clipboard text for the player
	 * @param text to set
	 */
	@ClientOnly
	public void setClipboardText(String text);
	
	public void setClipboardText(String text, boolean update);
	
	/**
	 * Sets the texture pack for this player to the given url
	 * The texture pack must be a standard texture pack (non-HD), in .zip format, and must be loadable by normal minecraft interface when offline
	 * @param url to set
	 */
	@ClientOnly
	public void setTexturePack(String url);
	
	/**
	 * Resets the texture pack for this player to the one they were using when they joined the game
	 */
	@ClientOnly
	public void resetTexturePack();
	
	/**
	 * Gets the gravity multiplier for this player
	 * 
	 * Default gravity modifier is 1
	 * @return gravity multiplier
	 */
	@ClientOnly
	public double getGravityMultiplier();
	
	/**
	 * Modifies the effects of gravity on the player's y axis movement.
	 * 
	 * Ex: setGravityMultiplier(10) will cause players to fall ten times faster than normal.
	 * 
	 * Warning, large modifiers may trigger fly-hack warnings.
	 * 
	 * Default gravity multiplier is 1
	 * @param multiplier to set.
	 */
	@ClientOnly
	public void setGravityMultiplier(double multiplier);
	
	/**
	 * Gets the swimming multiplier for this player
	 * 
	 * Default swimming modifier is 1
	 * @return swimming multiplier
	 */
	@ClientOnly
	public double getSwimmingMultiplier();
	
	/**
	 * Modifies the default swimming speed for this player
	 * 
	 * Ex: setSwimmingMultiplier(10) will cause players to swim ten times faster than normal.
	 * 
	 * Warning, large modifiers may trigger fly-hack warnings.
	 * 
	 * Default swimming multiplier is 1.
	 * @param multiplier to set.
	 */
	@ClientOnly
	public void setSwimmingMultiplier(double multiplier);
	
	/**
	 * Gets the walking multiplier for this player
	 * 
	 * Default walking modifier is 1
	 * @return walking multiplier
	 */
	@ClientOnly
	public double getWalkingMultiplier();
	
	/**
	 * Modifies the default walking speed for this player
	 * 
	 * Ex: setWalkingMultiplier(10) will cause players to walk ten times faster than normal.
	 * 
	 * Warning, large modifiers may trigger fly-hack warnings.
	 * 
	 * Default walking multiplier is 1.
	 * @param multiplier to set.
	 */
	@ClientOnly
	public void setWalkingMultiplier(double multiplier);
	
	/**
	 * Gets the jumping multiplier for this player
	 * 
	 * Default jumping modifier is 1
	 * @return jumping multiplier
	 */
	@ClientOnly
	public double getJumpingMultiplier();
	
	/**
	 * Modifies the default jumping speed for this player
	 * 
	 * Ex: setJumpingMultiplier(10) will cause players to jump ten times higher than normal.
	 * 
	 * Warning, large modifiers may trigger fly-hack warnings.
	 * 
	 * Default jumping multiplier is 1.
	 * @param multiplier to set.
	 */
	@ClientOnly
	public void setJumpingMultiplier(double multiplier);
	
	/**
	 * Gets the air speed multiplier for this player
	 * 
	 * Default air speed modifier is 1
	 * @return air speed multiplier
	 */
	@ClientOnly
	public double getAirSpeedMultiplier();
	
	/**
	 * Modifies the default air speed for this player
	 * 
	 * Ex: setAirSpeedMultiplier(10) will cause players to move horizontally while in the air ten times faster than normal.
	 * 
	 * Warning, large modifiers may trigger fly-hack warnings.
	 * 
	 * Default air speed multiplier is 1.
	 * @param multiplier to set.
	 */
	@ClientOnly
	public void setAirSpeedMultiplier(double multiplier);
	
	/**
	 * Resets all modified movement speeds, including walking, swimming, gravity, air speed, and jumping modifiers.
	 */
	public void resetMovement();
	
	/**
	 * Returns either the server wide fly setting, or specific player setting if a plugin has used setCanFly()
	 * 
	 * @return whether this player can fly.
	 */
	public boolean isCanFly();
	
	/**
	 * Overrides the server wide fly setting, allowing this player to fly, or not to fly. 
	 * 
	 * @param fly
	 */
	public void setCanFly(boolean fly);
	
	public Location getLastClickedLocation();
	
	@ClientOnly
	public void sendPacket(SpoutPacket packet);
	
	public void sendPacket(MCPacket packet);
	
	/**
	 * Sends the packet immediately.  Packets sent using this method are placed at the start of the packet queue.  
	 * If called from within a PacketListener's canSend method, the packet will be processed immediately after the current 
	 * packet is handled.
	 * 
	 * @param packet the packet to send
	 */
	public void sendImmediatePacket(MCPacket packet);
	
	public void updateKeys(byte[] keys);
	
	public Location getActiveInventoryLocation();
	
	public void setActiveInventoryLocation(Location location);

	public void setRenderDistance(RenderDistance currentRender, boolean update);
	
	/**
	 * Orders the client to reconnect to another server
	 * 
	 * This method is also supported by some server to server teleporting mods.
	 * 
	 * Players without the client mod will be given a kick message instructing them to join the other server
	 * 
	 * @param hostname the hostname of the other server
	 * @param port the port of the other server
	 */
	
	@ClientOnly
	public void reconnect(String hostname, int port);
	
	/**
	 * Orders the client to reconnect to another server.
	 * 
	 * This method is also supported by some server to server teleporting mods.
	 * 
	 * Players without the client mod will be given a kick message instructing them to join the other server
	 * 
	 * @param hostname the hostname of the other server
	 */
	
	@ClientOnly	
	public void reconnect(String hostname);

	public boolean sendInventoryEvent();
	
	public PlayerInformation getInformation();
	
	/**
	 * Orders the client to open the appriopriate screen type
	 * @param type of screen to open
	 */
	@ClientOnly
	public void openScreen(ScreenType type);
	
	public void openScreen(ScreenType type, boolean packet);
	
	/**
	 * Gets the active screen open for this player
	 * @return active screen
	 */
	@ClientOnly
	public ScreenType getActiveScreen();
	
	@ClientOnly
	public void openSignEditGUI(Sign sign);
	
	public void setPreCachingComplete(boolean complete);
	
	public boolean isPreCachingComplete();
	
}
