package org.getspout.spoutapi.material.block;

import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.BlockDesign;
import org.getspout.spoutapi.inventory.GenericBlockDesign;
import org.getspout.spoutapi.inventory.ItemManager;
import org.getspout.spoutapi.material.CustomBlock;

public class GenericCustomBlock extends GenericBlock implements CustomBlock {
	public BlockDesign design = new GenericBlockDesign();
	public ItemManager im = SpoutManager.getItemManager();
	public final String fullName;
	public int customID;
	public int customMetaData;

	public GenericCustomBlock(Plugin plugin, String name, BlockDesign design) {
		super(1, SpoutManager.getItemManager().registerCustomItemName(plugin, plugin.getDescription().getName() + name));
		this.setName(name);
		this.fullName = plugin.getDescription().getName() + name;
		customID = im.registerCustomItemName(plugin, fullName);
		this.design = design;
		updateDesign();
	}
	
	@Override
	public void setName(String name) {
		super.setName(name);
		im.setItemName(customID, name);
	}

	@Override
	public BlockDesign getBlockDesign() {
		return design;
	}

	@Override
	public void setBlockDesign(BlockDesign design) {
		this.design = design;
		updateDesign();
	}

	@Override
	public int getCustomID() {
		return customID;
	}

	public void updateDesign() {
		im.setCustomBlockDesign(1, customID, design);
	}

	@Override
	public String getFullName() {
		return fullName;
	}
}
