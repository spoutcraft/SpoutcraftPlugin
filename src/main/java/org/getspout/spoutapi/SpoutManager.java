/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, Spout LLC <http://www.spout.org/>
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
package org.getspout.spoutapi;

import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import org.getspout.spoutapi.chunkdatamanager.ChunkDataManager;
import org.getspout.spoutapi.inventory.InventoryBuilder;
import org.getspout.spoutapi.inventory.MaterialManager;
import org.getspout.spoutapi.keyboard.KeyBindingManager;
import org.getspout.spoutapi.packet.PacketManager;
import org.getspout.spoutapi.player.BiomeManager;
import org.getspout.spoutapi.player.FileManager;
import org.getspout.spoutapi.player.PlayerManager;
import org.getspout.spoutapi.player.SkyManager;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.sound.SoundManager;

@SuppressWarnings("deprecation")
public class SpoutManager {
	private static SpoutManager instance = new SpoutManager();
	private SoundManager soundManager = null;
	private SkyManager skyManager = null;
	private BiomeManager biomeManager = null;
	private InventoryBuilder inventoryBuilder = null;
	private PacketManager packetManager = null;
	private PlayerManager playerManager = null;
	private ChunkDataManager chunkDataManager = null;
	private FileManager fileManager = null;
	private KeyBindingManager keyBindingManager = null;
	private MaterialManager materialManager = null;
	private WorldManager worldManager = null;

	protected SpoutManager() {
	}

	/**
	 * Gets the singleton instance of the spout plugin
	 * @return spout plugin
	 */
	public static SpoutManager getInstance() {
		return instance;
	}

	/**
	 * Gets the sound manager
	 * @return sound manager
	 */
	public static SoundManager getSoundManager() {
		return getInstance().soundManager;
	}

	public void setSoundManager(SoundManager manager) {
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

	public void setPacketManager(PacketManager manager) {
		if (packetManager == null) {
			packetManager = manager;
		}
	}

	/**
	 * Gets the sky manager
	 * @return sky manager
	 */
	public static SkyManager getSkyManager() {
		return getInstance().skyManager;
	}

	public void setSkyManager(SkyManager manager) {
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

	public void setBiomeManager(BiomeManager manager) {
		if (biomeManager == null) {
			biomeManager = manager;
		}
	}

	/**
	 * Gets the inventory builder
	 * <p/>
	 * The inventory builder can construct Bukkit inventories from itemstacks or an initialization size.
	 * @return inventory builder
	 */
	@Deprecated
	public static InventoryBuilder getInventoryBuilder() {
		return getInstance().inventoryBuilder;
	}

	public void setInventoryBuilder(InventoryBuilder builder) {
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

	/**
	 * Gets the key binding manager;
	 * @return key binding manager
	 */
	public static KeyBindingManager getKeyBindingManager() {
		return getInstance().keyBindingManager;
	}

	/**
	 * Gets the material manager
	 * @return material manager
	 */
	public static MaterialManager getMaterialManager() {
		return getInstance().materialManager;
	}

	public void setMaterialManager(MaterialManager manager) {
		if (materialManager == null) {
			materialManager = manager;
		}
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

	public void setKeyBindingManager(KeyBindingManager manager) {
		if (keyBindingManager == null) {
			keyBindingManager = manager;
		}
	}

	public static ChunkDataManager getChunkDataManager() {
		return getInstance().chunkDataManager;
	}

	public void setWorldManager(WorldManager manager) {
		if (worldManager == null) {
			worldManager = manager;
		}
	}

	public static WorldManager getWorldManager() {
		return getInstance().worldManager;
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
	 * Gets a Entity from the given id, or null if none found
	 * @param entityId
	 * @return Entity
	 */
	public static Entity getEntityFromId(int entityId) {
		return getInstance().playerManager.getEntity(entityId);
	}

	/**
	 * Gets a Entity from the given id, or null if none found
	 * @param id
	 * @return Entity
	 */
	public static Entity getEntityFromId(UUID id) {
		return getInstance().playerManager.getEntity(id);
	}

	/**
	 * Gets a SpoutPlayer from the given bukkit player, will never fail
	 * @param player
	 * @return SpoutPlayer
	 */
	public static SpoutPlayer getPlayer(Player player) {
		return getInstance().playerManager.getPlayer(player);
	}

	/**
	 * Gets the list of online players
	 * @return online players
	 */
	public static SpoutPlayer[] getOnlinePlayers() {
		return getInstance().playerManager.getOnlinePlayers();
	}

	/**
	 * Gets the player manager
	 * @return player manager
	 */
	public static PlayerManager getPlayerManager() {
		return getInstance().playerManager;
	}
}
