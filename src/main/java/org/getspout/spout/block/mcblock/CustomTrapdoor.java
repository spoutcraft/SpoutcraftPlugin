package org.getspout.spout.block.mcblock;

import java.lang.reflect.Field;

import org.bukkit.event.block.BlockRedstoneEvent;

import net.minecraft.server.Block;
import net.minecraft.server.BlockTrapdoor;
import net.minecraft.server.Material;
import net.minecraft.server.World;

public class CustomTrapdoor extends BlockTrapdoor implements CustomMCBlock {

	protected BlockTrapdoor parent;

	protected CustomTrapdoor(int i, Material material) {
		super(i, material);
	}

	protected CustomTrapdoor(BlockTrapdoor parent) {
		this(parent.id, parent.material);
		this.parent = parent;
	}

	@Override
	public Block getParent() {
		return parent;
	}

	@Override
	public void setHardness(float hardness) {
		this.strength = hardness;
		updateField(this, parent, "strength");
	}

	@Override
	public boolean canPlace(World world, int i, int j, int k, int l) {
		if (l == 0) {
			return false;
		} else if (l == 1) {
			return false;
		} else {
			if (l == 2) {
				++k;
			}

			if (l == 3) {
				--k;
			}

			if (l == 4) {
				++i;
			}

			if (l == 5) {
				--i;
			}

			return f(world.getTypeId(i, j, k));
		}
	}

	@Override
	public void doPhysics(World world, int i, int j, int k, int l) {
		if (!world.isStatic) {
			int i1 = world.getData(i, j, k);
			int j1 = i;
			int k1 = k;

			if ((i1 & 3) == 0) {
				k1 = k + 1;
			}

			if ((i1 & 3) == 1) {
				--k1;
			}

			if ((i1 & 3) == 2) {
				j1 = i + 1;
			}

			if ((i1 & 3) == 3) {
				--j1;
			}

			if (!f(world.getTypeId(j1, j, k1))) {
				world.setTypeId(i, j, k, 0);
				this.b(world, i, j, k, i1, 0);
			}

			// CraftBukkit start
			if (l == 0 || l > 0 && Block.byId[l] != null && Block.byId[l].isPowerSource()) {
				org.bukkit.World bworld = world.getWorld();
				org.bukkit.block.Block block = bworld.getBlockAt(i, j, k);

				int power = block.getBlockPower();
				int oldPower = (world.getData(i, j, k) & 4) > 0 ? 15 : 0;

				if (oldPower == 0 ^ power == 0 || (Block.byId[l] != null && Block.byId[l].isPowerSource())) {
					BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, oldPower, power);
					world.getServer().getPluginManager().callEvent(eventRedstone);

					this.setOpen(world, i, j, k, eventRedstone.getNewCurrent() > 0);
				}
				// CraftBukkit end
			}
		}
	}

	private static boolean f(int i) {
		if (i <= 0) {
			return false;
		} else {
			Block block = Block.byId[i];

			//Spout Start - changed Block.GLOWSTONE to byId lookup
			return block != null && block.material.j() && block.b() || block == Block.byId[Block.GLOWSTONE.id];
			//Spout End
		}
	}

	private static void updateField(Block parent, Block child, String fieldName) {
		try {
			Field field = Block.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(child, field.get(parent));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
