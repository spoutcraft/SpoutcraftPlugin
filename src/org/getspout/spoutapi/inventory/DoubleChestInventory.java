package org.getspout.spoutapi.inventory;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
	
	/**
	 * Gets the left half of the double chest
	 * @return left side
	 */
	public Block getLeftSide();
	
	/**
	 * Gets the right half of the double chest
	 * @return right side
	 */
	public Block getRightSide();

	/**
	 * Gets the direction of the front buckle on the double chest
	 * @return buckle direction
	 */
	public BlockFace getDirection();
}
