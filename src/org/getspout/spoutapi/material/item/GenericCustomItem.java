package org.getspout.spoutapi.material.item;

import org.bukkit.block.BlockFace;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.inventory.MaterialManager;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.player.SpoutPlayer;

public class GenericCustomItem extends GenericItem implements CustomItem {
	public static MaterialManager mm = SpoutManager.getMaterialManager();
	private final String fullName;
	private final Plugin plugin;
	private final int customId;
	public String texture;

	public GenericCustomItem(Plugin plugin, String name) {
		super(name, 318, mm.registerCustomItemName(plugin, plugin.getDescription().getName() + name));
		this.fullName = plugin.getDescription().getName() + name;
		this.customId = mm.registerCustomItemName(plugin, fullName);
		this.plugin = plugin;
		this.setName(name);
		MaterialData.addCustomItem(this);
	}
	
	public GenericCustomItem(Plugin plugin, String name, String texture) {
		this(plugin,name);
		SpoutManager.getFileManager().addToCache(plugin, texture);
		this.setTexture(texture);
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		mm.setItemName(this, name);
	}

	@Override
	public int getCustomId() {
		return customId;
	}

	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public Plugin getPlugin() {
		return plugin;
	}

	@Override
	public CustomItem setTexture(String texture) {
		this.texture = texture;
		mm.setItemTexture(this, plugin, texture);
		return this;
	}

	@Override
	public String getTexture() {
		return texture;
	}

	@Override
	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block,	BlockFace face) {
		return true;
	}
	
}
