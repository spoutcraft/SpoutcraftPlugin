package org.getspout.spoutapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.getspout.spoutapi.inventory.InventoryBuilder;
import org.getspout.spoutapi.inventory.ItemManager;
import org.getspout.spoutapi.keyboard.KeyboardManager;
import org.getspout.spoutapi.player.AppearanceManager;
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
	private InventoryBuilder inventoryBuilder = null;
	
	protected SpoutManager(){

	}
	
	
	/**
	 * Gets the singleton instance of the bukkitcontrib plugin
	 * @return bukkitcontrib plugin
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
	
	public static InventoryBuilder getInventoryBuilder() {
		return getInstance().inventoryBuilder;
	}
	
	public void setInventoryBuilder(InventoryBuilder builder){
		if (inventoryBuilder == null) {
			inventoryBuilder = builder;
		}
	}
	
	public static SpoutPlayer getPlayerFromId(int entityId) {
		Player[] online = Bukkit.getServer().getOnlinePlayers();
		for (Player player : online) {
			if (player.getEntityId() == entityId && player instanceof SpoutPlayer) {
				return (SpoutPlayer)player;
			}
		}
		return null;
	}

}
