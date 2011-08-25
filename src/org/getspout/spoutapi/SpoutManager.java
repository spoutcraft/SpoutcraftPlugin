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
package org.getspout.spoutapi;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.getspout.spoutapi.chunkcache.CacheManager;
import org.getspout.spoutapi.chunkdatamanager.ChunkDataManager;
import org.getspout.spoutapi.inventory.InventoryBuilder;
import org.getspout.spoutapi.inventory.ItemManager;
import org.getspout.spoutapi.keyboard.KeyboardManager;
import org.getspout.spoutapi.packet.PacketManager;
import org.getspout.spoutapi.player.AppearanceManager;
import org.getspout.spoutapi.player.BiomeManager;
import org.getspout.spoutapi.player.FileManager;
import org.getspout.spoutapi.player.PlayerManager;
import org.getspout.spoutapi.player.SkyManager;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.sound.SoundManager;

public class SpoutManager {
	private static SpoutManager instance = new SpoutManager();
	private KeyboardManager keyManager = null;
	private AppearanceManager appearanceManager = null;
	private SoundManager soundManager = null;
	private SkyManager skyManager = null;
	private ItemManager itemManager = null;
	private BiomeManager biomeManager = null;
	private InventoryBuilder inventoryBuilder = null;
	private PacketManager packetManager = null;
	private PlayerManager playerManager = null;
	private CacheManager cacheManager = null;
	private ChunkDataManager chunkDataManager = null;
	private FileManager fileManager = null;
	
	protected SpoutManager(){

	}
	
	
	/**
	 * Gets the singleton instance of the spout plugin
	 * @return spout plugin
	 */
	public static SpoutManager getInstance() {
		return instance;
	}
	
	/**
	 * Gets the keyboard manager
	 * @return keyboard manager
	 */
	public static KeyboardManager getKeyboardManager() {
		return getInstance().keyManager;
	}
	
	public void setKeyboardManager(KeyboardManager manager){
		if (keyManager == null) {
			keyManager = manager;
		}
	}
	
	/**
	 * Gets the appearance manager
	 * @return appearance manager
	 */
	public static AppearanceManager getAppearanceManager() {
		return getInstance().appearanceManager;
	}
	
	public void setAppearanceManager(AppearanceManager manager){
		if (appearanceManager == null) {
			appearanceManager = manager;
		}
	}
	
	/**
	 * Gets the sound manager
	 * @return sound manager
	 */
	public static SoundManager getSoundManager() {
		return getInstance().soundManager;
	}
	
	public void setSoundManager(SoundManager manager){
		if (soundManager == null) {
			soundManager = manager;
		}
	}
	
	/**
	 * Gets the packet manager
	 * @return packet manager
	 */
	public static PacketManager getPacketManager() {
		return getInstance().packetManager;
	}
	
	public void setPacketManager(PacketManager manager){
		if (packetManager == null) {
			packetManager = manager;
		}
	}
	
	/**
	 * Gets the item manager
	 * @return item manager
	 */
	public static ItemManager getItemManager() {
		return getInstance().itemManager;
	}
	
	public void setItemManager(ItemManager manager){
		if (itemManager == null) {
			itemManager = manager;
		}
	}
	
	/**
	 * Gets the sky manager
	 * @return sky manager
	 */
	public static SkyManager getSkyManager() {
		return getInstance().skyManager;
	}
	
	public void setSkyManager(SkyManager manager){
		if (skyManager == null) {
			skyManager = manager;
		}
	}
	
	/**
	 * Gets the biome manager
	 * @return biome manager
	 */
	public static BiomeManager getBiomeManager() {
		return getInstance().biomeManager;
	}
	
	public void setBiomeManager(BiomeManager manager){
		if (biomeManager == null) {
			biomeManager = manager;
		}
	}
	
	public static CacheManager getCacheManager() {
		return getInstance().cacheManager;
	}
	
	public void setCacheManager(CacheManager manager) {
		if (cacheManager == null) {
			cacheManager = manager;
		}
	}
	
	/**
	 * Gets the inventory builder
	 * 
	 * The inventory builder can construct Bukkit inventories from itemstacks or an initialization size.
	 * @return inventory builder
	 */
	public static InventoryBuilder getInventoryBuilder() {
		return getInstance().inventoryBuilder;
	}
	
	public void setInventoryBuilder(InventoryBuilder builder){
		if (inventoryBuilder == null) {
			inventoryBuilder = builder;
		}
	}
	
	/**
	 * Gets the client side file and cache manager
	 * @return file manager
	 */
	public static FileManager getFileManager() {
		return getInstance().fileManager;
	}
	
	public void setFileManager(FileManager manager) {
		if (fileManager == null) {
			fileManager = manager;
		}
	}
	
	public void setPlayerManager(PlayerManager manager) {
		if (playerManager == null) {
			playerManager = manager;
		}
	}
	
	public void setChunkDataManager(ChunkDataManager manager) {
		if (chunkDataManager == null) {
			chunkDataManager = manager;
		}
	}
	
	public static ChunkDataManager getChunkDataManager() {
		return getInstance().chunkDataManager;
	}
	
	/**
	 * Gets a SpoutPlayer from the given id, or null if none found
	 * @param entityId
	 * @return SpoutPlayer
	 */
	public static SpoutPlayer getPlayerFromId(int entityId) {
		return getInstance().playerManager.getPlayer(entityId);
	}
	
	/**
	 * Gets a SpoutPlayer from the given id, or null if none found
	 * @param id
	 * @return SpoutPlayer
	 */
	public static SpoutPlayer getPlayerFromId(UUID id) {
		return getInstance().playerManager.getPlayer(id);
	}
	
	/**
	 * Gets a SpoutPlayer from the given bukkit player, will never fail
	 * @param player
	 * @return SpoutPlayer
	 */
	public static SpoutPlayer getPlayer(Player player) {
		return getInstance().playerManager.getPlayer(player);
	}
	
	public static PlayerManager getPlayerManager() {
		return getInstance().playerManager;
	}
}
