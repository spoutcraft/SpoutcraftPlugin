/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
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
package org.getspout.spoutapi.material.block;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.block.design.Axis;
import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.packet.PacketCustomBlockDesign;
import org.getspout.spoutapi.packet.PacketType;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.player.SpoutPlayer;

public class GenericCustomBlock extends GenericBlock implements CustomBlock, SpoutPacket {
	public BlockDesign[] design = new BlockDesign[256];
	private SpoutItemStack drop = null;
	private String fullName;
	private int customId;
	private Plugin plugin;
	private CustomItem item;
	private int blockId;
	private int metadata;
	private boolean opaque;
	private boolean rotate = false;
	private boolean mirroredRotate = false;
	private boolean fullRotate = false;
	private float hardness = 1.5F;
	private float friction = 0.6F;
	private int lightLevel = 0;

	/**
	 * Creates a GenericCustomBlock with no model yet.
	 * @param plugin         creating the block
	 * @param name           of the block
	 * @param blockID        of the underlying vanilla block
	 * @param metadata       of the underlying vanilla block
	 * @param rotate         will the block rotate to face the player when placed
	 * @param mirroredRotate can the block rotate upside-down
	 * @param fullRotate     can the block rotate in all directions
	 */
	protected GenericCustomBlock(Plugin plugin, String name, int blockId, int metadata, boolean rotate, boolean mirroredRotate, boolean fullRotate) {
		super(name, blockId, metadata);
		item = new GenericCustomItem(plugin, name);
		this.blockId = blockId;
		this.metadata = metadata;
		this.opaque = MaterialData.getBlock(blockId).isOpaque();
		this.plugin = plugin;
		this.fullName = item.getFullName();
		this.customId = item.getCustomId();
		this.rotate = rotate;
		this.mirroredRotate = mirroredRotate;
		this.fullRotate = fullRotate;
		MaterialData.addCustomBlock(this);
		this.setItemDrop(new SpoutItemStack(this, 1));

		for (SpoutPlayer player : Spout.getServer().getOnlinePlayers()) {
			player.sendPacket(this);
		}
	}
	/**
	 * Creates a GenericCustomBlock with no model yet.
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param blockID  of the underlying vanilla block
	 * @param metadata of the underlying vanilla block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	protected GenericCustomBlock(Plugin plugin, String name, int blockId, int metadata, boolean rotate) {
		this(plugin, name, blockId, metadata, rotate, false, false);
	}

	/**
	 * Creates a GenericCustomBlock with no model yet.
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param blockID  of the underlying vanilla block
	 * @param metadata of the underlying vanilla block
	 */
	protected GenericCustomBlock(Plugin plugin, String name, int blockId, int metadata) {
		this(plugin, name, blockId, metadata, false);
	}

	/**
	 * Creates a GenericCustomBlock with no model yet.
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param isOpaque true if you want the block solid
	 */
	protected GenericCustomBlock(Plugin plugin, String name, boolean isOpaque) {
		this(plugin, name, isOpaque ? 1 : 20, 0);
	}

	/**
	 * Creates a GenericCustomBlock with no model yet.
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param isOpaque true if you want the block solid
	 * @param rotate   will the block rotate to face the player when placed
	 */
	protected GenericCustomBlock(Plugin plugin, String name, boolean isOpaque, boolean rotate) {
		this(plugin, name, isOpaque, rotate, false, false);
	}

	/**
	 * Creates a GenericCustomBlock with no model yet.
	 * @param plugin         creating the block
	 * @param name           of the block
	 * @param isOpaque       true if you want the block solid
	 * @param rotate         will the block rotate to face the player when placed
	 * @param mirroredRotate can the block rotate upside-down
	 * @param fullRotate     can the block rotate in all directions
	 */
	protected GenericCustomBlock(Plugin plugin, String name, boolean isOpaque, boolean rotate, boolean mirroredRotate, boolean fullRotate) {
		this(plugin, name, isOpaque ? 1 : 20, 0, rotate, mirroredRotate, fullRotate);
	}

	/**
	 * Creates a GenericCustomBlock with no model yet and underlying vanilla block
	 * @param plugin  creating the block
	 * @param name    of the block
	 * @param blockID of the underlying vanilla block
	 */
	protected GenericCustomBlock(Plugin plugin, String name, int blockId) {
		this(plugin, name, blockId, 0);
	}

	/**
	 * Creates a GenericCustomBlock with no model yet and underlying vanilla block
	 * @param plugin  creating the block
	 * @param name    of the block
	 * @param blockID of the underlying vanilla block
	 * @param rotate  will the block rotate to face the player when placed
	 */
	protected GenericCustomBlock(Plugin plugin, String name, int blockId, boolean rotate) {
		this(plugin, name, blockId, rotate, false, false);
	}

	/**
	 * Creates a GenericCustomBlock with no model yet and underlying vanilla block
	 * @param plugin         creating the block
	 * @param name           of the block
	 * @param blockID        of the underlying vanilla block
	 * @param rotate         will the block rotate to face the player when placed
	 * @param mirroredRotate can the block rotate upside-down
	 * @param fullRotate     can the block rotate in all directions
	 */
	protected GenericCustomBlock(Plugin plugin, String name, int blockId, boolean rotate, boolean mirroredRotate, boolean fullRotate) {
		this(plugin, name, blockId, 0, rotate, mirroredRotate, fullRotate);
	}

	/**
	 * Creates a GenericCustomBlock with a specified design and underlying vanilla block
	 * @param plugin  creating the block
	 * @param name    of the block
	 * @param blockID of the underlying vanilla block
	 * @param design  to use for the block
	 */
	public GenericCustomBlock(Plugin plugin, String name, int blockId, BlockDesign design) {
		this(plugin, name, blockId, 0);
		setBlockDesign(design);
	}

	/**
	 * Creates a GenericCustomBlock with a specified design and underlying vanilla block
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param blockID  of the underlying vanilla block
	 * @param design   to use for the block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCustomBlock(Plugin plugin, String name, int blockId, BlockDesign design, boolean rotate) {
		this(plugin, name, blockId, design, rotate, false, false);
	}

	/**
	 * Creates a GenericCustomBlock with a specified design and underlying vanilla block
	 * @param plugin         creating the block
	 * @param name           of the block
	 * @param blockID        of the underlying vanilla block
	 * @param design         to use for the block
	 * @param rotate         will the block rotate to face the player when placed
	 * @param mirroredRotate can the block rotate upside-down
	 * @param fullRotate     can the block rotate in all directions
	 */
	public GenericCustomBlock(Plugin plugin, String name, int blockId, BlockDesign design, boolean rotate, boolean mirroredRotate, boolean fullRotate) {
		this(plugin, name, blockId, 0, rotate, mirroredRotate, fullRotate);
		setBlockDesign(design);
	}

	/**
	 * Creates a GenericCustomBlock with a specified design and underlying vanilla block + metadata
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param blockID  of the underlying vanilla block
	 * @param metadata of the underlying vanilla block
	 * @param design   to use for the block
	 */
	public GenericCustomBlock(Plugin plugin, String name, int blockId, int metadata, BlockDesign design) {
		this(plugin, name, blockId, metadata);
		setBlockDesign(design);
	}

	/**
	 * Creates a GenericCustomBlock with a specified design and underlying vanilla block + metadata
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param blockID  of the underlying vanilla block
	 * @param metadata of the underlying vanilla block
	 * @param design   to use for the block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCustomBlock(Plugin plugin, String name, int blockId, int metadata, BlockDesign design, boolean rotate) {
		this(plugin, name, blockId, metadata, design, rotate, false, false);
	}

	/**
	 * Creates a GenericCustomBlock with a specified design and underlying vanilla block + metadata
	 * @param plugin         creating the block
	 * @param name           of the block
	 * @param blockID        of the underlying vanilla block
	 * @param metadata       of the underlying vanilla block
	 * @param design         to use for the block
	 * @param rotate         will the block rotate to face the player when placed
	 * @param mirroredRotate can the block rotate upside-down
	 * @param fullRotate     can the block rotate in all directions
	 */
	public GenericCustomBlock(Plugin plugin, String name, int blockId, int metadata, BlockDesign design, boolean rotate, boolean mirroredRotate, boolean fullRotate) {
		this(plugin, name, blockId, metadata, rotate, mirroredRotate, fullRotate);
		setBlockDesign(design);
	}

	/**
	 * Creates a GenericCustomBlock with a specified design and underlying block ID
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param isOpaque true if you want the block solid
	 * @param design   to use for the block
	 */
	public GenericCustomBlock(Plugin plugin, String name, boolean isOpaque, BlockDesign design) {
		this(plugin, name, isOpaque);
		setBlockDesign(design);
	}

	/**
	 * Creates a GenericCustomBlock with a specified design and underlying block ID
	 * @param plugin   creating the block
	 * @param name     of the block
	 * @param isOpaque true if you want the block solid
	 * @param design   to use for the block
	 * @param rotate   will the block rotate to face the player when placed
	 */
	public GenericCustomBlock(Plugin plugin, String name, boolean isOpaque, BlockDesign design, boolean rotate) {
		this(plugin, name, isOpaque, design, rotate, false, false);
	}

	/**
	 * Creates a GenericCustomBlock with a specified design and underlying block ID
	 * @param plugin         creating the block
	 * @param name           of the block
	 * @param isOpaque       true if you want the block solid
	 * @param design         to use for the block
	 * @param rotate         will the block rotate to face the player when placed
	 * @param mirroredRotate can the block rotate upside-down
	 * @param fullRotate     can the block rotate in all directions
	 */
	public GenericCustomBlock(Plugin plugin, String name, boolean isOpaque, BlockDesign design, boolean rotate, boolean mirroredRotate, boolean fullRotate) {
		this(plugin, name, isOpaque, rotate, mirroredRotate, fullRotate);
		setBlockDesign(design);
	}

	/**
	 * Creates a basic GenericCustomblock with no design that is opaque/solid.
	 * @param plugin creating the block
	 * @param name   of the block
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
		return getBlockDesign(0);
	}

	@Override
	public BlockDesign getBlockDesign(int id) {
		return design[id + 128];
	}

	@Override
	public CustomBlock setBlockDesign(BlockDesign design) {
		if (rotate) {
			setBlockDesign(design, 0);
			setBlockDesign(design.rotate(90, Axis.Y), 1);
			setBlockDesign(design.rotate(180, Axis.Y), 2);
			setBlockDesign(design.rotate(270, Axis.Y), 3);
			
			if (mirroredRotate) {
				setBlockDesign(design.rotate(180, Axis.Z), 4);
				setBlockDesign(design.rotate(180, Axis.Z).rotate(90, Axis.Y), 5);
				setBlockDesign(design.rotate(180, Axis.Z).rotate(180, Axis.Y), 6);
				setBlockDesign(design.rotate(180, Axis.Z).rotate(270, Axis.Y), 7);
			}

			if (fullRotate) {
				setBlockDesign(design.rotate(90, Axis.X), 8);
				setBlockDesign(design.rotate(90, Axis.X).rotate(90, Axis.Y), 9);
				setBlockDesign(design.rotate(90, Axis.X).rotate(180, Axis.Y), 10);
				setBlockDesign(design.rotate(90, Axis.X).rotate(270, Axis.Y), 11);
				
				setBlockDesign(design.rotate(270, Axis.X), 12);
				setBlockDesign(design.rotate(270, Axis.X).rotate(90, Axis.Y), 13);
				setBlockDesign(design.rotate(270, Axis.X).rotate(180, Axis.Y), 14);
				setBlockDesign(design.rotate(270, Axis.X).rotate(270, Axis.Y), 15);
				
				setBlockDesign(design.rotate(90, Axis.Z), 16);
				setBlockDesign(design.rotate(90, Axis.Z).rotate(90, Axis.Y), 17);
				setBlockDesign(design.rotate(90, Axis.Z).rotate(180, Axis.Y), 18);
				setBlockDesign(design.rotate(90, Axis.Z).rotate(270, Axis.Y), 19);
				
				setBlockDesign(design.rotate(270, Axis.Z), 20);
				setBlockDesign(design.rotate(270, Axis.Z).rotate(90, Axis.Y), 21);
				setBlockDesign(design.rotate(270, Axis.Z).rotate(180, Axis.Y), 22);
				setBlockDesign(design.rotate(270, Axis.Z).rotate(270, Axis.Y), 23);
			}
			
			return this;
		}
		return setBlockDesign(design, 0);
	}

	@Override
	public CustomBlock setBlockDesign(BlockDesign design, int id) {
		this.design[id + 128] = design;

		for (SpoutPlayer sp : Spout.getServer().getOnlinePlayers()) {
			sp.sendPacket(new PacketCustomBlockDesign((short) customId, design, (byte) id));
		}
		return this;
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
	public int getBlockData() {
		return this.metadata;
	}

	@Override
	public CustomBlock setItemDrop(ItemStack item) {
		if (item instanceof SpoutItemStack) {
			drop = (SpoutItemStack) item;
		} else {
			drop = new SpoutItemStack(drop);
		}
		return this;
	}

	@Override
	public SpoutItemStack getItemDrop() {
		return drop;
	}

	@Override
	public float getHardness() {
		return hardness;
	}

	@Override
	public CustomBlock setHardness(float hardness) {
		this.hardness = hardness;
		return this;
	}

	@Override
	public float getFriction() {
		return friction;
	}

	@Override
	public CustomBlock setFriction(float friction) {
		this.friction = friction;
		return this;
	}

	@Override
	public int getLightLevel() {
		return lightLevel;
	}

	@Override
	public CustomBlock setLightLevel(int level) {
		lightLevel = level;
		return this;
	}

	@Override
	public boolean canRotate() {
		return rotate;
	}

	@Override
	public CustomBlock setRotate(boolean rotate) {
		this.rotate = rotate;
		return this;
	}

	@Override
	public boolean canMirroredRotate() {
		return mirroredRotate;
	}

	@Override
	public CustomBlock setMirroredRotate(boolean mirroredRotate) {
		this.mirroredRotate = mirroredRotate;
		return this;
	}

	@Override
	public boolean canFullRotate() {
		return fullRotate;
	}

	@Override
	public CustomBlock setFullRotate(boolean fullRotate) {
		this.fullRotate = fullRotate;
		return this;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int changedId) {
	}

	@Override
	public void onBlockPlace(World world, int x, int y, int z) {
	}

	@Override
	public void onBlockPlace(World world, int x, int y, int z, LivingEntity living) {
	}

	@Override
	public void onBlockDestroyed(World world, int x, int y, int z) {
	}

	@Override
	public void onBlockDestroyed(World world, int x, int y, int z, LivingEntity living) {
	}

	@Override
	public boolean onBlockInteract(World world, int x, int y, int z, SpoutPlayer player) {
		return false;
	}

	@Override
	public void onEntityMoveAt(World world, int x, int y, int z, Entity entity) {
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, SpoutPlayer player) {
	}

	@Override
	public boolean isProvidingPowerTo(World world, int x, int y, int z, BlockFace face) {
		return false;
	}

	@Override
	public boolean isIndirectlyProvidingPowerTo(World world, int x, int y, int z, BlockFace face) {
		return false;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		customId = input.readInt();
		setName(input.readString());
		plugin = Bukkit.getServer().getPluginManager().getPlugin(input.readString());
		opaque = input.readBoolean();
		setFriction(input.readFloat());
		setHardness(input.readFloat());
		setLightLevel(input.readInt());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(customId);
		output.writeString(getName());
		output.writeString(getPlugin().getDescription().getName());
		output.writeBoolean(isOpaque());
		output.writeFloat(getFriction());
		output.writeFloat(getHardness());
		output.writeInt(getLightLevel());
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomBlock;
	}

	@Override
	public int getVersion() {
		return 0;
	}

	public boolean isPowerSource() {
		return false;
	}
}
