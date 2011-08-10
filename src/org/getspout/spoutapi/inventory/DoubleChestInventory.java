package org.getspout.spoutapi.inventory;

import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;

public interface DoubleChestInventory extends Inventory{
	/**
	 * Gets the block containing the top half of the double chest
	 * @return top half
	 */
	public Block getTopHalf();
	
	/**
	 * Gets the block containing the bottom half of the double chest
	 * @return bottom half
	 */
	public Block getBottomHalf();

}
