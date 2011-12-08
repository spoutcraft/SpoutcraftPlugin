/*
 * This file is part of SpoutAPI (http://wiki.getspout.org/).
 * 
 * SpoutAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi;

import gnu.trove.map.hash.TIntObjectHashMap;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.scheduler.BukkitScheduler;
import org.getspout.spoutapi.player.EntitySkinType;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.avaje.ebean.config.ServerConfig;

/**
 * A proxy class that provides Spout objects for the {@link Server} class
 */
public class SpoutServer implements Server{
	private Server server = Bukkit.getServer();
	private HashMap<UUID, SpoutWorld> worlds = new HashMap<UUID, SpoutWorld>();
	private TIntObjectHashMap<String> titles = new TIntObjectHashMap<String>(250);
	
	private SpoutWorld getSpoutWorld(World world) {
		if (world == null) {
			return null;
		}
		return getSpoutWorld(world.getUID());
	}
	
	private SpoutWorld getSpoutWorld(UUID id) {
		if (worlds.containsKey(id)) {
			return worlds.get(id);
		}
		SpoutWorld world = new SpoutWorld(server.getWorld(id));
		worlds.put(id, world);
		return world;
	}

	/**
	 * Sets the entity skin for the target entity to the url. The Skin Type is used when an entity has more than one skin type.
	 * @param target to set the skin on
	 * @param url of the skin
	 * @param type of skin to set
	 */
	public void setEntitySkin(LivingEntity target, String url, EntitySkinType type) {
		SpoutManager.getPlayerManager().getGlobalInfo().setEntitySkin(target, url, type);
	}

	/**
	 * Gets the entity skin for the target entity. The Skin Type is used when an entity has more than one skin type.
	 * @param target to get the skin for
	 * @param type of skin to set
	 */
	public String getEntitySkin(LivingEntity target, EntitySkinType type) {
		return SpoutManager.getPlayerManager().getGlobalInfo().getEntitySkin(target, type);
	}

	/**
	 * Resets the entity skin for the target entity.
	 * @param target to reset the skin for
	 */
	public void resetEntitySkin(LivingEntity target) {
		SpoutManager.getPlayerManager().getGlobalInfo().setEntitySkin(target, null);
	}
	
	public String getTitle(LivingEntity entity) {
		if (entity instanceof SpoutPlayer) {
			return ((SpoutPlayer)entity).getTitle();
		}
		if (titles.contains(entity.getEntityId())) {
			return titles.get(entity.getEntityId());
		}
		return null;
	}
	
	public void setTitle(LivingEntity entity, String title) {
		if (entity instanceof SpoutPlayer) {
			((SpoutPlayer)entity).setTitle(title);
		}
		titles.put(entity.getEntityId(), title);
	}

	@Override
	public boolean addRecipe(Recipe recipe) {
		return server.addRecipe(recipe);
	}

	@Override
	public void banIP(String address) {
		server.banIP(address);
	}

	@Override
	public int broadcast(String message, String permission) {
		return server.broadcast(message, permission);
	}

	@Override
	public int broadcastMessage(String message) {
		return server.broadcastMessage(message);
	}

	@Override
	public void configureDbConfig(ServerConfig config) {
		server.configureDbConfig(config);
	}

	@Override
	public MapView createMap(World world) {
		return server.createMap(world);
	}

	@Override
	public SpoutWorld createWorld(WorldCreator creator) {
		return getSpoutWorld(server.createWorld(creator));
	}

	@Override
	public World createWorld(String name, Environment environment) {
		throw new UnsupportedOperationException("This method is not supported");
	}

	@Override
	public World createWorld(String name, Environment environment, long seed) {
		throw new UnsupportedOperationException("This method is not supported");
	}

	@Override
	public World createWorld(String name, Environment environment, ChunkGenerator generator) {
		throw new UnsupportedOperationException("This method is not supported");
	}

	@Override
	public World createWorld(String name, Environment environment, long seed, ChunkGenerator generator) {
		throw new UnsupportedOperationException("This method is not supported");
	}

	@Override
	public boolean dispatchCommand(CommandSender sender, String commandLine) throws CommandException {
		return server.dispatchCommand(sender, commandLine);
	}

	@Override
	public boolean getAllowFlight() {
		return server.getAllowFlight();
	}

	@Override
	public boolean getAllowNether() {
		return server.getAllowNether();
	}

	@Override
	public Set<OfflinePlayer> getBannedPlayers() {
		return server.getBannedPlayers();
	}

	@Override
	public String getBukkitVersion() {
		return server.getBukkitVersion();
	}

	@Override
	public Map<String, String[]> getCommandAliases() {
		return server.getCommandAliases();
	}

	@Override
	public ConsoleCommandSender getConsoleSender() {
		return server.getConsoleSender();
	}

	@Override
	public GameMode getDefaultGameMode() {
		return server.getDefaultGameMode();
	}

	@Override
	public Set<String> getIPBans() {
		return server.getIPBans();
	}

	@Override
	public String getIp() {
		return server.getIp();
	}

	@Override
	public Logger getLogger() {
		return server.getLogger();
	}

	@Override
	public MapView getMap(short id) {
		return server.getMap(id);
	}

	@Override
	public int getMaxPlayers() {
		return server.getMaxPlayers();
	}

	@Override
	public String getName() {
		return server.getName();
	}

	@Override
	public OfflinePlayer getOfflinePlayer(String name) {
		return server.getOfflinePlayer(name);
	}

	@Override
	public boolean getOnlineMode() {
		return server.getOnlineMode();
	}

	@Override
	public SpoutPlayer[] getOnlinePlayers() {
		return SpoutManager.getOnlinePlayers();
	}

	@Override
	public Set<OfflinePlayer> getOperators() {
		return server.getOperators();
	}

	@Override
	public SpoutPlayer getPlayer(String name) {
		return (SpoutPlayer)server.getPlayer(name);
	}

	@Override
	public SpoutPlayer getPlayerExact(String name) {
		return (SpoutPlayer)server.getPlayerExact(name);
	}

	@Override
	public PluginCommand getPluginCommand(String name) {
		return server.getPluginCommand(name);
	}

	@Override
	public PluginManager getPluginManager() {
		return server.getPluginManager();
	}

	@Override
	public int getPort() {
		return server.getPort();
	}

	@Override
	public BukkitScheduler getScheduler() {
		return server.getScheduler();
	}

	@Override
	public String getServerId() {
		return server.getServerId();
	}

	@Override
	public String getServerName() {
		return server.getServerName();
	}

	@Override
	public ServicesManager getServicesManager() {
		return server.getServicesManager();
	}

	@Override
	public int getSpawnRadius() {
		return server.getSpawnRadius();
	}

	@Override
	public String getUpdateFolder() {
		return server.getUpdateFolder();
	}

	@Override
	public File getUpdateFolderFile() {
		return server.getUpdateFolderFile();
	}

	@Override
	public String getVersion() {
		return server.getVersion();
	}

	@Override
	public int getViewDistance() {
		return server.getViewDistance();
	}

	@Override
	public Set<OfflinePlayer> getWhitelistedPlayers() {
		return server.getWhitelistedPlayers();
	}

	@Override
	public SpoutWorld getWorld(String name) {
		return getSpoutWorld(server.getWorld(name));
	}

	@Override
	public SpoutWorld getWorld(UUID uid) {
		return getSpoutWorld(server.getWorld(uid));
	}

	@Override
	public List<World> getWorlds() {
		List<World> worlds = server.getWorlds();
		ArrayList<World> list = new ArrayList<World>(worlds.size());
		for (World w : worlds) {
			list.add(getSpoutWorld(w));
		}
		return list;
	}
	
	public List<SpoutWorld> getSpoutWorlds() {
		List<World> worlds = server.getWorlds();
		ArrayList<SpoutWorld> list = new ArrayList<SpoutWorld>(worlds.size());
		for (World w : worlds) {
			list.add(getSpoutWorld(w));
		}
		return list;
	}

	@Override
	public boolean hasWhitelist() {
		return server.hasWhitelist();
	}

	@Override
	public List<Player> matchPlayer(String name) {
		return server.matchPlayer(name);
	}

	@Override
	public void reload() {
		server.reload();
	}

	@Override
	public void reloadWhitelist() {
		server.reloadWhitelist();
	}

	@Override
	public void savePlayers() {
		server.savePlayers();
	}

	@Override
	public void setDefaultGameMode(GameMode mode) {
		server.setDefaultGameMode(mode);
	}

	@Override
	public void setSpawnRadius(int value) {
		server.setSpawnRadius(value);
	}

	@Override
	public void setWhitelist(boolean value) {
		server.setWhitelist(value);
	}

	@Override
	public void shutdown() {
		server.shutdown();
	}

	@Override
	public void unbanIP(String address) {
		server.unbanIP(address);
	}

	@Override
	public boolean unloadWorld(String name, boolean save) {
		return server.unloadWorld(name, save);
	}

	@Override
	public boolean unloadWorld(World world, boolean save) {
		return server.unloadWorld(world, save);
	}

	@Override
	public File getWorldContainer() {
		return server.getWorldContainer();
	}

	@Override
	public OfflinePlayer[] getOfflinePlayers() {
		return server.getOfflinePlayers();
	}

}
