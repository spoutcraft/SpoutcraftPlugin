/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
package org.getspout.spoutapi.player;

import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import org.getspout.spoutapi.ClientOnly;
import org.getspout.spoutapi.gui.InGameHUD;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.packet.standard.MCPacket;
import org.getspout.spoutapi.player.accessories.AccessoryType;

/**
 * Represents a SpoutPlayer, which extends the standard Bukkit Player.
 * SpoutPlayer's can be retrieved by casting Bukkit's org.bukkit.entity.Player class
 */
public interface SpoutPlayer extends org.bukkit.entity.Player {
	/**
	 * @return true if a window was closed
	 * @Deprecated use {@link #closeInventory()} instead. <br/><br/>
	 * <p/>
	 * Closes any dialog windows the client may have open at the time
	 */
	@Deprecated
	public boolean closeActiveWindow();

	/**
	 * @param inventory to use in the dialog GUI
	 * @return true if an inventory window was opened
	 * @Deprecated use {@link #openInventory(Inventory)} instead. <br/><br/>
	 * <p/>
	 * Opens an inventory dialog to the player, with the given inventory displayed in the upper pane, and the player's inventory in the lower pane
	 */
	@Deprecated
	public boolean openInventoryWindow(Inventory inventory);

	/**
	 * @param inventory to use in the dialog GUI
	 * @param location  that represents this inventory in the world (e.g Chest, Furnace). Use null if there is no physical location.
	 * @return true if an inventory window was opened
	 * @Deprecated use {@link #openInventory(Inventory)} instead. <br/><br/>
	 * <p/>
	 * Opens an inventory dialog to the player, with the given inventory displayed in the upper pane, and the player's inventory in the lower pane.
	 * The location is not used, but is passed to other plugins when notifying them of the open window
	 */
	@Deprecated
	public boolean openInventoryWindow(Inventory inventory, Location location);

	/**
	 * @param inventory      to use in the dialog GUI
	 * @param location       that represents this inventory in the world (e.g Chest, Furnace). Use null if there is no physical location.
	 * @param ignoreDistance whether the distance from the inventory should be considered (opening an inventory will fail if it's too far away, without ignoring distance)
	 * @return true if an inventory window was opened
	 * @Deprecated use {@link #openInventory(Inventory)} instead. <br/><br/>
	 * <p/>
	 * Opens an inventory dialog to the player, with the given inventory displayed in the upper pane, and the player's inventory in the lower pane.
	 * The location is not used, but is passed to other plugins when notifying them of the open window
	 */
	@Deprecated
	public boolean openInventoryWindow(Inventory inventory, Location location, boolean ignoreDistance);

	/**
	 * @param location of the workbench to use. Must be a valid workbench.
	 * @return true if a workbench window was opened
	 * @Deprecated use {@link #openInventory(Inventory)} instead. <br/><br/>
	 * <p/>
	 * Opens an workbench dialog to the player, using the workbench at the given location
	 */
	@Deprecated
	public boolean openWorkbenchWindow(Location location);

	/**
	 * Gets a copy of the in game HUD to attach widget and launch popups from
	 * @return In game HUD
	 */
	public InGameHUD getMainScreen();

	/**
	 * Gets a copy of the current opened screen of the player. This can be the InGameHUD, but also other screen types.
	 * Check the screentype with Screen.getType();
	 * @return the currently open screen
	 * @see Screen.getType()
	 */
	public Screen getCurrentScreen();

	/**
	 * Returns true if the player is using the spoutcraft single player mod
	 * @return spout craft single player mod enabled
	 */
	public boolean isSpoutCraftEnabled();

	/**
	 * Returns the key bound to forward movement for this player, or unknown if not known.
	 * @return forward key
	 */
	@ClientOnly
	public Keyboard getForwardKey();

	/**
	 * Returns the key bound to backward movement for this player, or unknown if not known.
	 * @return backward key
	 */
	@ClientOnly
	public Keyboard getBackwardKey();

	/**
	 * Returns the key bound to left movement for this player, or unknown if not known.
	 * @return left key
	 */
	@ClientOnly
	public Keyboard getLeftKey();

	/**
	 * Returns the key bound to right movement for this player, or unknown if not known.
	 * @return right key
	 */
	public Keyboard getRightKey();

	/**
	 * Returns the key bound to jumping for this player, or unknown if not known.
	 * @return jump key
	 */
	@ClientOnly
	public Keyboard getJumpKey();

	/**
	 * Returns the key bound to opening and closing inventories for this player, or unknown if not known.
	 * @return inventory key
	 */
	@ClientOnly
	public Keyboard getInventoryKey();

	/**
	 * Returns the key bound to dropping items for this player, or unknown if not known.
	 * @return drop item key
	 */
	@ClientOnly
	public Keyboard getDropItemKey();

	/**
	 * Returns the key bound to opening the chat bar for this player, or unknown if not known.
	 * @return chat key
	 */
	@ClientOnly
	public Keyboard getChatKey();

	/**
	 * Returns the key bound to toggle view fog for this player, or unknown if not known.
	 * @return toggle fog key
	 */
	@ClientOnly
	public Keyboard getToggleFogKey();

	/**
	 * Returns the key bound to sneaking for this player, or unknown if not known.
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
	 * Sends the player a notification (using the existing Achievement Get window), with the given title, message, and item to render as a graphic
	 * The title and message may not exceed 26 characters in length
	 * The item to render may not be null
	 * @param title    to send
	 * @param message  to send
	 * @param toRender to render
	 */
	@ClientOnly
	public void sendNotification(String title, String message, Material toRender);

	/**
	 * Sends the player a notification (using the existing Achievement Get window), with the given title, message, and item to render as a graphic
	 * The title and message may not exceed 26 characters in length
	 * The item to render may not be null
	 * @param title    to send
	 * @param message  to send
	 * @param toRender to render
	 * @param data     for the item to render
	 * @param time     for the notification to remain in milliseconds
	 */
	@ClientOnly
	public void sendNotification(String title, String message, Material toRender, short data, int time);

	/**
	 * Sends the player a notification (using the existing Achievement Get window), with the given title, message, and item to render as a graphic
	 * The title and message may not exceed 26 characters in length
	 * The item to render may not be null
	 * @param title   to send
	 * @param message to send
	 * @param item    to render
	 * @param time    for the notification to remain in milliseconds
	 */
	@ClientOnly
	public void sendNotification(String title, String message, ItemStack item, int time);

	/**
	 * Gets the clipboard text from the player, or null if unknown
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
	 * <p/>
	 * Default gravity modifier is 1
	 * @return gravity multiplier
	 */
	@ClientOnly
	public double getGravityMultiplier();

	/**
	 * Modifies the effects of gravity on the player's y axis movement.
	 * <p/>
	 * Ex: setGravityMultiplier(10) will cause players to fall ten times faster than normal.
	 * <p/>
	 * Warning, large modifiers may trigger fly-hack warnings.
	 * <p/>
	 * Default gravity multiplier is 1
	 * @param multiplier to set.
	 */
	@ClientOnly
	public void setGravityMultiplier(double multiplier);

	/**
	 * Gets the swimming multiplier for this player
	 * <p/>
	 * Default swimming modifier is 1
	 * @return swimming multiplier
	 */
	@ClientOnly
	public double getSwimmingMultiplier();

	/**
	 * Modifies the default swimming speed for this player
	 * <p/>
	 * Ex: setSwimmingMultiplier(10) will cause players to swim ten times faster than normal.
	 * <p/>
	 * Warning, large modifiers may trigger fly-hack warnings.
	 * <p/>
	 * Default swimming multiplier is 1.
	 * @param multiplier to set.
	 */
	@ClientOnly
	public void setSwimmingMultiplier(double multiplier);

	/**
	 * Gets the walking multiplier for this player
	 * <p/>
	 * Default walking modifier is 1
	 * @return walking multiplier
	 */
	@ClientOnly
	public double getWalkingMultiplier();

	/**
	 * Modifies the default walking speed for this player
	 * <p/>
	 * Ex: setWalkingMultiplier(10) will cause players to walk ten times faster than normal.
	 * <p/>
	 * Warning, large modifiers may trigger fly-hack warnings.
	 * <p/>
	 * Default walking multiplier is 1.
	 * @param multiplier to set.
	 */
	@ClientOnly
	public void setWalkingMultiplier(double multiplier);

	/**
	 * Gets the jumping multiplier for this player
	 * <p/>
	 * Default jumping modifier is 1
	 * @return jumping multiplier
	 */
	@ClientOnly
	public double getJumpingMultiplier();

	/**
	 * Modifies the default jumping speed for this player
	 * <p/>
	 * Ex: setJumpingMultiplier(10) will cause players to jump ten times higher than normal.
	 * <p/>
	 * Warning, large modifiers may trigger fly-hack warnings.
	 * <p/>
	 * Default jumping multiplier is 1.
	 * @param multiplier to set.
	 */
	@ClientOnly
	public void setJumpingMultiplier(double multiplier);

	/**
	 * Gets the air speed multiplier for this player
	 * <p/>
	 * Default air speed modifier is 1
	 * @return air speed multiplier
	 */
	@ClientOnly
	public double getAirSpeedMultiplier();

	/**
	 * Modifies the default air speed for this player
	 * <p/>
	 * Ex: setAirSpeedMultiplier(10) will cause players to move horizontally while in the air ten times faster than normal.
	 * <p/>
	 * Warning, large modifiers may trigger fly-hack warnings.
	 * <p/>
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
	 * @return whether this player can fly.
	 */
	public boolean canFly();

	/**
	 * Overrides the server wide fly setting, allowing this player to fly, or not to fly.
	 * @param fly
	 */
	public void setCanFly(boolean fly);

	/**
	 * Gets the location that the player clicked on last, or null if there was no previous click locations
	 * @return
	 */
	public Location getLastClickedLocation();

	/**
	 * Sends a MCPacket to the client
	 * @param packet to send
	 */
	public void sendPacket(MCPacket packet);

	/**
	 * True if the player has completed precaching files
	 * @return true if precaching files is complete
	 */
	public boolean isPreCachingComplete();

	/**
	 * Sends the packet immediately.  Packets sent using this method are placed at the start of the packet queue.
	 * If called from within a PacketListener's canSend method, the packet will be processed immediately after the current
	 * packet is handled.
	 * @param packet the packet to send
	 */
	public void sendImmediatePacket(MCPacket packet);

	/**
	 * Orders the client to reconnect to another server
	 * <p/>
	 * This method is also supported by some server to server teleporting mods.
	 * <p/>
	 * Players without the client mod will be given a kick message instructing them to join the other server
	 * @param message  the message to include in the kick message for vanilla clients
	 * @param hostname the hostname of the other server
	 * @param port     the port of the other server
	 */
	@ClientOnly
	public void reconnect(String message, String hostname, int port);

	/**
	 * Orders the client to reconnect to another server.
	 * <p/>
	 * This method is also supported by some server to server teleporting mods.
	 * <p/>
	 * Players without the client mod will be given a kick message instructing them to join the other server
	 * @param message  the message to include in the kick message for vanilla clients
	 * @param hostname the hostname of the other server
	 */
	@ClientOnly
	public void reconnect(String message, String hostname);

	/**
	 * Orders the client to reconnect to another server
	 * <p/>
	 * This method is also supported by some server to server teleporting mods.
	 * <p/>
	 * Players without the client mod will be given a kick message instructing them to join the other server
	 * @param hostname the hostname of the other server
	 * @param port     the port of the other server
	 */
	@ClientOnly
	public void reconnect(String hostname, int port);

	/**
	 * Orders the client to reconnect to another server.
	 * <p/>
	 * This method is also supported by some server to server teleporting mods.
	 * <p/>
	 * Players without the client mod will be given a kick message instructing them to join the other server
	 * @param hostname the hostname of the other server
	 */
	@ClientOnly
	public void reconnect(String hostname);

	/**
	 * Gets the active screen open for this player
	 * @return active screen
	 */
	@ClientOnly
	public ScreenType getActiveScreen();

	/**
	 * Opens a sign edit GUI for the given sign
	 * @param sign to edit
	 */
	@ClientOnly
	public void openSignEditGUI(Sign sign);

	/**
	 * Orders the client to open the appriopriate screen type
	 * @param type of screen to open
	 */
	@ClientOnly
	public void openScreen(ScreenType type);

	/**
	 * Sends a request to the client to send a screenshot to the server.
	 */
	@ClientOnly
	public void sendScreenshotRequest();

	/**
	 * Sets the skin of this player
	 * @param url to set to
	 */
	public void setSkin(String url);

	/**
	 * Sets the skin of this player, only visibile to the viewingPlayer
	 * @param viewingPlayer that this skin will be visible to
	 * @param url           to set to
	 */
	public void setSkinFor(SpoutPlayer viewingPlayer, String url);

	/**
	 * Gets the skin url that this player is using
	 * @return skin
	 */
	public String getSkin();

	/**
	 * Gets the skin url that is visible to the viewingPlayer
	 * @param viewingPlayer that this skin is visible to
	 * @return skin
	 */
	public String getSkin(SpoutPlayer viewingPlayer);

	/**
	 * Resets the skin to the default
	 */
	public void resetSkin();

	/**
	 * Resets the skin to the default for the viewing player
	 * @param viewingPlayer to reset the skin for
	 */
	public void resetSkinFor(SpoutPlayer viewingPlayer);

	/**
	 * Sets the cape url of this player
	 * @param url to set to
	 */
	public void setCape(String url);

	/**
	 * Sets the cape url of this player, that is only visible to the viewingPlayer
	 * @param viewingPlayer that this cape is visible for
	 * @param url           to set to
	 */
	public void setCapeFor(SpoutPlayer viewingPlayer, String url);

	/**
	 * Gets the cape that this player is wearing
	 * @return cape url
	 */
	public String getCape();

	/**
	 * Gets the cape that is visible to the viewingPlayer
	 * @param viewingPlayer that this cape is visible for
	 * @return cape url
	 */
	public String getCape(SpoutPlayer viewingPlayer);

	/**
	 * Resets the cape that this player is wearing
	 */
	public void resetCape();

	/**
	 * Resets the cape that is visible for the viewingPlayer
	 * @param viewingPlayer to reset the cape for
	 */
	public void resetCapeFor(SpoutPlayer viewingPlayer);

	/**
	 * Sets the overhead title for the player.
	 * <p/>
	 * Note: '\n' in the title will create a new line. You may use as many lines in a title as you desire.
	 * <p/>
	 * Note: You can color titles with the {@link org.bukkit#ChatColor} colors.
	 * @param title to set overhead.
	 */
	public void setTitle(String title);

	/**
	 * Sets the overhead title for the player, only visible to the viewingPlayer.
	 * <p/>
	 * Note: '\n' in the title will create a new line. You may use as many lines in a title as you desire.
	 * <p/>
	 * Note: You can color titles with the {@link org.bukkit#ChatColor} colors.
	 * @param viewingPlayer that this title is visible to
	 * @param title         to set overhead.
	 */
	public void setTitleFor(SpoutPlayer viewingPlayer, String title);

	/**
	 * Gets the overhead title for the player.
	 * @return overhead title
	 */
	public String getTitle();

	/**
	 * Gets the overhead title that is visible to the viewingPlayer
	 * @param viewingPlayer that this title is visible for
	 * @return overhead title
	 */
	public String getTitleFor(SpoutPlayer viewingPlayer);

	/**
	 * Completely hides the title from view of all players.
	 */
	public void hideTitle();

	/**
	 * Completely hides the title from the view of the viewingPlayer
	 * @param viewingPlayer to hide the title from.
	 */
	public void hideTitleFrom(SpoutPlayer viewingPlayer);

	/**
	 * Resets the title back to its default state.
	 */
	public void resetTitle();

	/**
	 * Resets the title back to its default state for the viewingPlayer.
	 * @param viewingPlayer
	 */
	public void resetTitleFor(SpoutPlayer viewingPlayer);

	/**
	 * Sets the entity skin for the target entity to the url. The Skin Type is used when an entity has more than one skin type.
	 * @param target to set the skin on
	 * @param url    of the skin
	 * @param type   of skin to set
	 */
	public void setEntitySkin(LivingEntity target, String url, EntitySkinType type);

	/**
	 * Resets the entity skin for the target entity.
	 * @param target to reset the skin for
	 */
	public void resetEntitySkin(LivingEntity target);

	/**
	 * Tests the skin/cape url for correctness. Will throw an {@link UnsupportedOperationException} if it fails.
	 * @param url to test
	 * @throws UnsupportedOperationException
	 */
	public void checkUrl(String url);

	/**
	 * Internal use only
	 * @param type
	 * @param packet
	 */
	public void openScreen(ScreenType type, boolean packet);

	/**
	 * Internal use only
	 * @param complete
	 */
	public void setPreCachingComplete(boolean complete);

	/**
	 * Internal use only
	 * @param currentRender
	 * @param update
	 */
	public void setRenderDistance(RenderDistance currentRender, boolean update);

	/**
	 * Internal use only
	 * @return true if event was successful
	 */
	@Deprecated
	public boolean sendInventoryEvent();

	/**
	 * Internal use only
	 * @return player information
	 */
	public PlayerInformation getInformation();

	/**
	 * Internal use only
	 * @param packet
	 */
	@ClientOnly
	public void sendPacket(SpoutPacket packet);

	/**
	 * Internal use only
	 * @param packet
	 */
	@ClientOnly
	public void sendDelayedPacket(SpoutPacket packet);

	/**
	 * Internal use only
	 * @param keys
	 */
	public void updateKeys(byte[] keys);

	/**
	 * Internal use only
	 * @return location
	 */
	@Deprecated
	public Location getActiveInventoryLocation();

	/**
	 * Internal use only
	 * @param location
	 */
	@Deprecated
	public void setActiveInventoryLocation(Location location);

	/**
	 * Internal use only
	 * @param text
	 * @param update
	 */
	public void setClipboardText(String text, boolean update);

	/**
	 * Internal use only
	 * @param entities to update skins for
	 */
	public void updateEntitySkins(List<LivingEntity> entities);

	/**
	 * Gets a list of the addons the player is running.
	 * @return a Map of the players addons to their corresponding
	 *         versions.
	 */
	public Map<String, String> getAddons();

	/**
	 * Internal use only
	 * @param addons   the addons the player is using
	 * @param versions the corresponding versions for the addon list
	 */
	public void setAddons(String[] addons, String[] versions);

	/**
	 * Sends the current value of the node to the client.
	 * @param node the node to update
	 */
	public void updatePermission(String node);

	/**
	 * Sends the current value of all given nodes to the client.
	 * @param nodes the nodes to update
	 */
	public void updatePermissions(String... nodes);

	/**
	 * Sends the current value of all permissions that the player has to the client
	 */
	public void updatePermissions();

	/**
	 * Sends a packet to the client to spawn a text entity
	 * @param text     the text that should be displayed
	 * @param location the location of the entity
	 * @param scale    the scale of the entity, where 1.0f is one block high
	 * @param duration if not 0, this will despawn the entity after the given duration (in ticks)
	 * @param movement the entity will move by the given vector each tick
	 * @returns if the packet for the entity could be send
	 */
	public boolean spawnTextEntity(String text, Location location, float scale, int duration, Vector movement);

	/**
	 * Adds a waypoint to the minimap of the client, with the given loation and given name.
	 * <br/> <br/>
	 * Note: This waypoint will be cleared when the user logs off or changes worlds.
	 * @param name iof the waypoint
	 * @param x    coordinate
	 * @param y    coordinate
	 * @param z    coordinate
	 */
	public void addWaypoint(String name, double x, double y, double z);

	/**
	 * Gets the player's Spoutcraft version as an int.
	 */
	public int getBuildVersion();

	/**
	 * Gets the player's Spoutcraft version as a String.
	 */
	public String getVersionString();

	/**
	 * Checks if the player has that accessory type.
	 * @param type The type to check for.
	 * @return Whether the player has that type of accessory.
	 */
	public boolean hasAccessory(AccessoryType type);

	/**
	 * Adds a new accessory to the player.
	 * @param type The accessory's type.
	 * @param url  The accessory's url.
	 */
	public void addAccessory(AccessoryType type, String url);

	/**
	 * Removes an accessory from the player.
	 * @param type The accessory type.
	 * @return The accessory's url.
	 */
	public String removeAccessory(AccessoryType type);

	/**
	 * Gets the accessory's url
	 * @param type The accessory.
	 * @return  The url.
	 */
	public String getAccessoryURL(AccessoryType type);
}
