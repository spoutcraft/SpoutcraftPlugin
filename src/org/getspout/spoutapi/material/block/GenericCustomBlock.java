package org.getspout.spoutapi.material.block;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericBlockDesign;
import org.getspout.spoutapi.inventory.MaterialManager;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;

public abstract class GenericCustomBlock extends GenericBlock implements CustomBlock {
	public BlockDesign design = new GenericBlockDesign();
	public static MaterialManager mm = SpoutManager.getMaterialManager();
	private final String fullName;
	private final int customID;
	private final Plugin plugin;
	private final CustomItem item;
	private final int blockId;
	public int customMetaData = 0;
	private boolean opaque;

	/**
	 * Creates a GenericCustomBlock with no model yet.
	 * 
	 * @param plugin creating the block
	 * @param name of the block
	 * @param isOpaque true if you want the block solid
	 */
	public GenericCustomBlock(Plugin plugin, String name, boolean isOpaque) {
		super(name, isOpaque ? 1 : 20);
		this.opaque = isOpaque;
		item = new GenericCustomItem(plugin, name);
		this.blockId = isOpaque ? 1 : 20;
		this.plugin = plugin;
		this.fullName = item.getFullName();
		this.customID = item.getCustomId();
		MaterialData.addCustomBlock(this);
		this.setItemDrop(mm.getCustomItemStack(this, 1));
	}

	/**
	 * Creates a GenericCustomBlock with a specified Design and metadata
	 * 
	 * @param plugin creating the block
	 * @param name of the block
	 * @param isOpaque true if you want the block solid
	 * @param design to use for the block
	 * @param customMetaData for the block
	 */
	public GenericCustomBlock(Plugin plugin, String name, boolean isOpaque, BlockDesign design, int customMetaData) {
		this(plugin, name, isOpaque);
		this.customMetaData = customMetaData;
		setBlockDesign(design);
	}

	/**
	 * Creates a basic GenericCustomblock with no design that is opaque/solid.
	 * 
	 * @param plugin creating the block
	 * @param name of the block
	 */
	public GenericCustomBlock(Plugin plugin, String name) {
		this(plugin, name, true);
	}
	
	@Override
	public boolean isOpaque() {
		return opaque;
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		item.setName(name);
	}

	@Override
	public BlockDesign getBlockDesign() {
		return design;
	}

	@Override
	public CustomBlock setBlockDesign(BlockDesign design) {
		this.design = design;
		mm.setCustomBlockDesign(this, design);
		mm.setCustomBlockDesign(this.getBlockItem(), design);
		mm.setCustomItemBlock(item, this);

		return this;
	}

	@Override
	public int getCustomId() {
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

	@Override
	public CustomItem getBlockItem() {
		return item;
	}
	
	@Override
	public int getRawId() {
		return this.item.getRawId();
	}
	
	@Override
	public int getRawData() {
		return this.item.getCustomId();
	}
	
	@Override
	public int getBlockId() {
		return this.blockId;
	}
	
	@Override
	public CustomBlock setItemDrop(ItemStack item) {
		mm.registerItemDrop(this, item);
		return this;
	}
}
