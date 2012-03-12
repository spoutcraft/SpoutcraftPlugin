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
package org.getspout.spout;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.server.Packet18ArmAnimation;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.FileUtil;

import org.getspout.commons.inventory.ItemMap;
import org.getspout.commons.io.CRCStore;
import org.getspout.commons.io.store.FlatFileStore;
import org.getspout.spout.block.SpoutCraftChunk;
import org.getspout.spout.block.mcblock.CustomBlock;
import org.getspout.spout.chunkcache.SimpleCacheManager;
import org.getspout.spout.command.SpoutCommand;
import org.getspout.spout.config.ConfigReader;
import org.getspout.spout.inventory.SimpleMaterialManager;
import org.getspout.spout.inventory.SpoutInventoryBuilder;
import org.getspout.spout.item.mcitem.CustomItemFlint;
import org.getspout.spout.item.mcitem.CustomItemPickaxe;
import org.getspout.spout.item.mcitem.CustomItemSpade;
import org.getspout.spout.keyboard.SimpleKeyBindingManager;
import org.getspout.spout.keyboard.SimpleKeyboardManager;
import org.getspout.spout.packet.CustomPacket;
import org.getspout.spout.packet.SimplePacketManager;
import org.getspout.spout.player.SimpleAppearanceManager;
import org.getspout.spout.player.SimpleBiomeManager;
import org.getspout.spout.player.SimpleFileManager;
import org.getspout.spout.player.SimplePlayerManager;
import org.getspout.spout.player.SimpleSkyManager;
import org.getspout.spout.player.SpoutCraftPlayer;
import org.getspout.spout.sound.SimpleSoundManager;
import org.getspout.spout.util.DeadlockMonitor;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.chunkstore.PlayerTrackingManager;
import org.getspout.spoutapi.chunkstore.SimpleChunkDataManager;
import org.getspout.spoutapi.packet.PacketRenderDistance;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Spout extends JavaPlugin {
	public SpoutPlayerListener playerListener;
	protected final PlayerTrackingManager playerTrackingManager;
	protected SpoutWorldListener chunkListener;
	protected SpoutWorldMonitorListener chunkMonitorListener;
	protected SpoutBlockListener blockListener;
	protected SpoutEntityListener entityListener;
	protected PluginListener pluginListener;
	protected SpoutCustomBlockMonitor blockMonitor;
	protected static Spout instance;
	protected FlatFileStore<String> CRCConfig;
	protected FlatFileStore<Integer> itemMapConfig;
	protected ItemMap serverItemMap;
	protected List<SpoutPlayer> playersOnline = new ArrayList<SpoutPlayer>();
	protected Thread shutdownThread = null;
	protected InventoryListener invListener;

	public Spout() {
		super();
		Spout.instance = this;
		SpoutManager.getInstance().setKeyboardManager(new SimpleKeyboardManager());
		SpoutManager.getInstance().setAppearanceManager(new SimpleAppearanceManager());
		SpoutManager.getInstance().setSoundManager(new SimpleSoundManager());
		SpoutManager.getInstance().setSkyManager(new SimpleSkyManager());
		SpoutManager.getInstance().setInventoryBuilder(new SpoutInventoryBuilder());
		SpoutManager.getInstance().setPacketManager(new SimplePacketManager());
		SpoutManager.getInstance().setPlayerManager(new SimplePlayerManager());
		SpoutManager.getInstance().setCacheManager(new SimpleCacheManager());
		SpoutManager.getInstance().setChunkDataManager(new SimpleChunkDataManager());
		SpoutManager.getInstance().setBiomeManager(new SimpleBiomeManager());
		SpoutManager.getInstance().setFileManager(new SimpleFileManager());
		SpoutManager.getInstance().setKeyBindingManager(new SimpleKeyBindingManager());
		SpoutManager.getInstance().setMaterialManager(new SimpleMaterialManager());
		SpoutManager.getInstance().setWorldManager(new SimpleWorldManager());
		playerTrackingManager = new PlayerTrackingManager();
		shutdownThread = new ShutdownThread();
		Runtime.getRuntime().addShutdownHook(shutdownThread);
	}

	@Override
	public void onDisable() {
		//order matters
		CustomBlock.resetBlocks();
		((SimpleMaterialManager)SpoutManager.getMaterialManager()).reset();
		((SimpleSkyManager)SpoutManager.getSkyManager()).reset();
		((SimplePlayerManager)SpoutManager.getPlayerManager()).onPluginDisable();
		Player[] online = getServer().getOnlinePlayers();
		for (Player player : online) {
			try {
				SpoutCraftPlayer scp = (SpoutCraftPlayer) SpoutCraftPlayer.getPlayer(player);
				scp.resetMovement();
				if (scp.isSpoutCraftEnabled()) {
					scp.sendPacket(new PacketRenderDistance(true, true));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//for (Player player : online) {
		//	SpoutCraftPlayer.removeBukkitEntity(player);
		//	SpoutCraftPlayer.resetNetServerHandler(player);
		//}
		SpoutCraftChunk.resetAllBukkitChunks();

		getServer().getScheduler().cancelTasks(this);

		SimpleChunkDataManager dm = (SimpleChunkDataManager)SpoutManager.getChunkDataManager();
		dm.unloadAllChunks();
		dm.closeAllFiles();

		try {
			CRCConfig.save();
		} catch (Exception e) {
			getServer().getLogger().info("Exception caught from saving CRCConfig. This may be ignored if you had a warning the line before regarding improper build of SpoutPlugin with Craftbukkit. If otherwise, please report it to spout.in/issues");
		}

		if (itemMapConfig != null) {
			synchronized(itemMapConfig) {
				itemMapConfig.save();
			}
		}

		SimpleFileManager.clearTempDirectory();

		//end the thread
		PacketCompressionThread.endThread();

		Runtime.getRuntime().removeShutdownHook(shutdownThread);
		super.onDisable();
	}

	@Override
	public void onEnable() {
		(new ConfigReader()).read();
		if (ConfigReader.isBuildCheck()) {
			InputStream is = getResource("plugin.yml");
			YamlConfiguration temp = YamlConfiguration.loadConfiguration(is);
			String cbBuild = temp.getString("cbversion");
			if (!getServer().getBukkitVersion().equals(cbBuild)) {
				getServer().getLogger().log(Level.SEVERE, "Spout has detected that you are attemping to run an incompatible build of SpoutPlugin with CraftBukkit. Spout will shut itself off to prevent possible damage to your server. If you believe this is mistaken or you know what you are doing, then you can turn this feature off within Spout's config.");
				getServer().getPluginManager().disablePlugin(this);
				return;
			}
		}
		playerListener = new SpoutPlayerListener(this);
		chunkListener = new SpoutWorldListener(this);
		chunkMonitorListener = new SpoutWorldMonitorListener(this);
		pluginListener = new PluginListener(this);
		entityListener = new SpoutEntityListener(this);
		blockMonitor = new SpoutCustomBlockMonitor(this);
		blockListener = new SpoutBlockListener(this);
		invListener = new InventoryListener(this);

		getCommand("spout").setExecutor(new SpoutCommand(this));

		for (SpoutPlayer player : org.getspout.spoutapi.Spout.getServer().getOnlinePlayers()) {
			SpoutCraftPlayer.resetNetServerHandler(player);
			SpoutCraftPlayer.updateNetServerHandler(player);
			SpoutCraftPlayer.updateBukkitEntity(player);
			authenticate(player);
			playerListener.manager.onPlayerJoin(player);
			((SimplePlayerManager)SpoutManager.getPlayerManager()).onPlayerJoin(player);
			player.setPreCachingComplete(true); //already done if we are already online!
			synchronized(playersOnline) {
				playersOnline.add(player);
			}
		}

		SpoutCraftChunk.replaceAllBukkitChunks();
		((SimplePlayerManager)SpoutManager.getPlayerManager()).onPluginEnable();

		CustomItemSpade.replaceSpades();
		CustomItemPickaxe.replacePickaxes();
		CustomItemFlint.replaceFlint();
		//Do on demand
		//CustomBlock.replaceBlocks();

		PacketCompressionThread.startThread();

		//Start counting ticks
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new ServerTickTask(), 0, 1);

		//Remove mappings from previous loads
		//Can not remove them on disable because the packets will still be in the send queue
		CustomPacket.removeClassMapping();
		CustomPacket.addClassMapping();

		SimpleChunkDataManager dm = (SimpleChunkDataManager)SpoutManager.getChunkDataManager();
		dm.loadAllChunks();

		CRCConfig = new FlatFileStore<String>(new File(this.getDataFolder(), "CRCCache.txt"), String.class);
		CRCConfig.load();

		CRCStore.setConfigFile(CRCConfig);

		itemMapConfig = new FlatFileStore<Integer>(new File(this.getDataFolder(), "itemMap.txt"), Integer.class);
		if (!itemMapConfig.load()) {
			System.out.println("[Spout] Unable to load global item map");
		} else {
			serverItemMap = new ItemMap(null, itemMapConfig, null);
		}
		ItemMap.setRootMap(serverItemMap);

		SimpleMaterialManager.disableFlintStackMix();

		if (ConfigReader.runDeadlockMonitor()) {
			new DeadlockMonitor().start();
		}
		
		super.onEnable();
	}

	/**
	 * Gets the singleton instance of the Spout plugin
	 * @return Spout plugin
	 */
	public static Spout getInstance() {
		return instance;
	}

	public PlayerTrackingManager getPlayerTrackingManager() {
		return playerTrackingManager;
	}

	public void authenticate(Player player) {
		if (ConfigReader.authenticateSpoutcraft()) {
			Packet18ArmAnimation packet = new Packet18ArmAnimation();
			packet.a = -42;
			((SpoutCraftPlayer)SpoutCraftPlayer.getPlayer(player)).getNetServerHandler().sendImmediatePacket(packet);
		}
	}
}

class ShutdownThread extends Thread {
	public void run() {
		SimpleChunkDataManager dm = (SimpleChunkDataManager)SpoutManager.getChunkDataManager();
		dm.unloadAllChunks();
		dm.closeAllFiles();
	}
}
