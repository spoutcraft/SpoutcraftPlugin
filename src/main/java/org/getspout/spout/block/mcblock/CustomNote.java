package org.getspout.spout.block.mcblock;

import java.lang.reflect.Field;

import net.minecraft.server.Block;
import net.minecraft.server.BlockNote;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.TileEntity;
import net.minecraft.server.World;

public class CustomNote extends BlockNote implements CustomMCBlock {
	protected BlockNote parent;
	
	protected CustomNote(BlockNote parent) {
		super(parent.id);
		
		this.parent = parent;
		updateField(parent, this, "strength");
		updateField(parent, this, "durability");
		updateField(parent, this, "bR");
		updateField(parent, this, "bS");
		this.minX = parent.minX;
		this.minY = parent.minY;
		this.minZ = parent.minZ;
		this.maxX = parent.maxX;
		this.maxY = parent.maxY;
		this.maxZ = parent.maxZ;
		this.stepSound = parent.stepSound;
		this.ca = parent.ca;
		this.frictionFactor = parent.frictionFactor;
		updateField(parent, this, "name");
		
		Block.isTileEntity[id] = true;
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
	
	public float getExplosionResistance() {
		return this.durability;
	}

	public void setExplosionResistance(float resistance) {
		this.durability = resistance;
	}
	
    public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
    	return parent.interact(world, i, j, k, entityhuman);
    }

    public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
    	parent.attack(world, i, j, k, entityhuman);
    }

    public TileEntity a_() {
        return parent.a_();
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
