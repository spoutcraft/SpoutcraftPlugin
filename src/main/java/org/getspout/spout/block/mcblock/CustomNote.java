/*
 * This file is part of SpoutPlugin (http://www.spout.org/).
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
		updateField(parent, this, "bT");
		this.minX = parent.minX;
		this.minY = parent.minY;
		this.minZ = parent.minZ;
		this.maxX = parent.maxX;
		this.maxY = parent.maxY;
		this.maxZ = parent.maxZ;
		this.stepSound = parent.stepSound;
		this.cc = parent.cc;
		this.frictionFactor = parent.frictionFactor;
		updateField(parent, this, "name");
		
		isTileEntity = true;
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
