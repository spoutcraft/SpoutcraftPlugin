package org.getspout.spoutapi.material.block;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.BlockDesign;
import org.getspout.spoutapi.inventory.GenericBlockDesign;
import org.getspout.spoutapi.inventory.ItemManager;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.MaterialData;

public class GenericCustomBlock extends GenericBlock implements CustomBlock {
	public BlockDesign design = new GenericBlockDesign();
	public ItemManager im = SpoutManager.getItemManager();
	private final String fullName;
	private final int customID;
	private final Plugin plugin;
	public int customMetaData = 0;

	public GenericCustomBlock(Plugin plugin, String name, boolean isOpaque) {
		super(isOpaque ? 20 : 1);
		this.plugin = plugin;
		this.fullName = plugin.getDescription().getName() + name;
		this.customID = im.registerCustomItemName(plugin, fullName);
		setName(name);
		MaterialData.addCustomBlock(this);
	}

	public GenericCustomBlock(Plugin plugin, String name, BlockDesign design, boolean isOpaque, int customMetaData) {
		this(plugin, name, isOpaque);
		this.customMetaData = customMetaData;
		setBlockDesign(design);
		im.setCustomItemBlock(customID, getRawId(), (short) customMetaData);
	}

	public GenericCustomBlock(Plugin plugin, String name) {
		super(1);
		this.plugin = plugin;
		this.fullName = plugin.getDescription().getName() + name;
		this.customID = im.registerCustomItemName(plugin, fullName);
		MaterialData.addCustomBlock(this);
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		im.setItemName(getRawId(), (short) customID, name);
	}

	@Override
	public BlockDesign getBlockDesign() {
		return design;
	}

	@Override
	public CustomBlock setBlockDesign(BlockDesign design) {
		this.design = design;
		im.setCustomBlockDesign(customID, (short) customMetaData, design);
		im.setCustomItemBlock(customID, getRawId(), (short) customMetaData);

		return this;
	}

	@Override
	public int getCustomID() {
		return customID;
	}

	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public CustomBlock setCustomMetaData(int customMetaData) {
		this.customMetaData = customMetaData;

		return this;
	}

	@Override
	public int getCustomMetaData() {
		return customMetaData;
	}

	@Override
	public Plugin getPlugin() {
		return plugin;
	}
}
