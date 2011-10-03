package org.getspout.spoutapi.material.item;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.ItemManager;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;

public class GenericCustomItem extends GenericItem implements CustomItem {
	public static ItemManager im = SpoutManager.getItemManager();
	private final String fullName;
	private final Plugin plugin;
	private final int customId;
	public String texture;

	public GenericCustomItem(Plugin plugin, String name) {
		super(318, im.registerCustomItemName(plugin, plugin.getDescription().getName() + name));
		this.fullName = plugin.getDescription().getName() + name;
		this.customId = im.registerCustomItemName(plugin, fullName);
		this.plugin = plugin;
		this.setName(name);
		MaterialData.addCustomItem(this);
	}
	
	public GenericCustomItem(Plugin plugin, String name, String texture) {
		this(plugin,name);
		this.texture = texture;
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		im.setItemName(this, name);
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
		im.setItemTexture(this, plugin, texture);
		return this;
	}

	@Override
	public String getTexture() {
		return texture;
	}
	
}
