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
package org.getspout.spout.block;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import gnu.trove.map.hash.TIntIntHashMap;

import net.minecraft.server.v1_5_R2.Chunk;
import net.minecraft.server.v1_5_R2.ChunkProviderServer;
import net.minecraft.server.v1_5_R2.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_5_R2.CraftChunk;
import org.bukkit.craftbukkit.v1_5_R2.CraftWorld;

import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.SpoutWorld;
import org.getspout.spoutapi.block.SpoutChunk;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.MaterialData;

public class SpoutCraftChunk extends CraftChunk implements SpoutChunk {
	protected final ConcurrentHashMap<Integer, Integer> queuedId = new ConcurrentHashMap<Integer, Integer>();
	protected final ConcurrentHashMap<Integer, Byte> queuedData = new ConcurrentHashMap<Integer, Byte>();
	protected static final Set<SpoutCraftChunk> queuedChunks = Collections.newSetFromMap(new ConcurrentHashMap<SpoutCraftChunk, Boolean>());

	public final TIntIntHashMap powerOverrides = new TIntIntHashMap();

	transient private final int worldHeight;
	transient private final int worldHeightMinusOne;
	transient private final int xBitShifts;
	transient private final int zBitShifts;

	public SpoutCraftChunk(Chunk chunk) {
		super(chunk);

		SpoutWorld world = Spout.getServer().getWorld(getWorld().getUID());

		this.worldHeight = world != null ? world.getMaxHeight() : 256;
		this.xBitShifts = world != null ? world.getXBitShifts() : 12;
		this.zBitShifts = world != null ? world.getZBitShifts() : 8;
		worldHeightMinusOne = worldHeight - 1;
	}

	@Override
	public Block getBlock(int x, int y, int z) {
		return new SpoutCraftBlock(this, (getX() << 4) | (x & 0xF), y & worldHeightMinusOne, (getZ() << 4) | (z & 0xF));
	}

	private Block getBlockFromPos(int pos) throws IllegalAccessException, NoSuchFieldException {
		int x = (pos >> xBitShifts) & 0xF;
		int y = (pos >> 0) & worldHeightMinusOne;
		int z = (pos >> zBitShifts) & 0xF;

		return getBlock(x, y, z);

	}

	public void onTick() {
		while (!queuedData.isEmpty() || !queuedId.isEmpty()) {
			Iterator<Entry<Integer, Integer>> i = queuedId.entrySet().iterator();
			while (i.hasNext()) {
				Entry<Integer, Integer> entry = i.next();
				try {
					Block block = getBlockFromPos(entry.getKey());
					block.setTypeId(entry.getValue());
					i.remove();
				} catch (Exception e) {
				}
			}
			Iterator<Entry<Integer, Byte>> j = queuedData.entrySet().iterator();
			while (j.hasNext()) {
				Entry<Integer, Byte> entry = j.next();
				if (queuedId.isEmpty()) {
					try {
						Block block = getBlockFromPos(entry.getKey());
						block.setData(entry.getValue());
						j.remove();
					} catch (Exception e) {
					}
				} else {
					break;
				}
			}
		}
	}

	protected void onReset() {
		// TODO finalize queuing
	}

	public static void updateTicks() {
		Iterator<SpoutCraftChunk> i = SpoutCraftChunk.queuedChunks.iterator();
		while (i.hasNext()) {
			SpoutCraftChunk chunk = i.next();
			chunk.onTick();
			i.remove();
		}
	}

	public static void replaceAllBukkitChunks() {
		replaceAllBukkitChunks(false);
	}

	public static void resetAllBukkitChunks() {
		replaceAllBukkitChunks(true);
	}

	private static void replaceAllBukkitChunks(boolean reset) {
		List<World> worlds = Bukkit.getServer().getWorlds();
		for (World world : worlds) {
			try {
				CraftWorld cw = (CraftWorld) world;
				Field worldServer = CraftWorld.class.getDeclaredField("world");
				worldServer.setAccessible(true);
				ChunkProviderServer cps = ((WorldServer) worldServer.get(cw)).chunkProviderServer;
				for (Chunk c : cps.chunks.values()) {
					Chunk chunk = c;
					if (reset) {
						if (chunk.bukkitChunk instanceof SpoutCraftChunk) {
							((SpoutCraftChunk) chunk.bukkitChunk).onReset();
						}
						resetBukkitChunk(chunk.bukkitChunk);
					} else {
						replaceBukkitChunk(chunk.bukkitChunk);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean replaceBukkitChunk(org.bukkit.Chunk chunk) {
		CraftChunk handle = (CraftChunk) ((CraftChunk) chunk).getHandle().bukkitChunk;
		if (handle != null) {
			boolean replace = false;
			if (handle.getX() != chunk.getX()) {
				replace = true;
			}
			if (handle.getZ() != chunk.getZ()) {
				replace = true;
			}
			if (handle.getClass().hashCode() != SpoutCraftChunk.class.hashCode()) {
				replace = true;
			}
			org.bukkit.Chunk loopbackChunk = ((CraftChunk)chunk).getHandle().bukkitChunk;
			if (loopbackChunk != chunk) {
				replace = true;
			}
			if (replace) {
				((CraftChunk) chunk).getHandle().bukkitChunk = new SpoutCraftChunk(((CraftChunk) chunk).getHandle());
			}
			return true;
		}
		return false;
	}

	public static void resetBukkitChunk(org.bukkit.Chunk chunk) {
		((CraftChunk) chunk).getHandle().bukkitChunk = new CraftChunk(((CraftChunk) chunk).getHandle());
	}

	@Override
	public Serializable setData(String id, Serializable data) {
		return SpoutManager.getChunkDataManager().setChunkData(id, getWorld(), getX(), getZ(), data);
	}

	@Override
	public Serializable getData(String id) {
		return SpoutManager.getChunkDataManager().getChunkData(id, getWorld(), getX(), getZ());
	}

	@Override
	public Serializable removeData(String id) {
		return SpoutManager.getChunkDataManager().removeChunkData(id, getWorld(), getX(), getZ());
	}

	@Override
	public short[] getCustomBlockIds() {
		return SpoutManager.getChunkDataManager().getCustomBlockIds(getWorld(), getX(), getZ());
	}

	@Override
	public void setCustomBlockIds(short[] ids) {
		SpoutManager.getChunkDataManager().setCustomBlockIds(getWorld(), getX(), getZ(), ids);
	}

	@Override
	public short getCustomBlockId(int x, int y, int z) {
		short[] ids = getCustomBlockIds();
		if (ids == null) {
			return 0;
		}
		int index = ((x & 0xF) << xBitShifts) | ((z & 0xF) << zBitShifts) | (y & worldHeightMinusOne);
		return ids[index];
	}

	@Override
	public short setCustomBlockId(int x, int y, int z, short id) {
		short[] ids = getCustomBlockIds();
		if (ids == null) {
			ids = new short[16*16*worldHeight];
			setCustomBlockIds(ids);
		}
		int index = ((x & 0xF) << xBitShifts) | ((z & 0xF) << zBitShifts) | (y & worldHeightMinusOne);
		short old = ids[index];
		ids[index] = id;
		return old;
	}

	@Override
	public CustomBlock setCustomBlock(int x, int y, int z, CustomBlock block) {
		if (block == null) {
			throw new NullPointerException("Custom Block can not be null!");
		}
		short old = setCustomBlockId(x, y, z, (short) block.getCustomId());
		return MaterialData.getCustomBlock(old);
	}

	@Override
	public byte[] getCustomBlockData() {
		return SpoutManager.getChunkDataManager().getCustomBlockData(getWorld(), getX(), getZ());
	}

	@Override
	public void setCustomBlockData(byte[] data) {
		SpoutManager.getChunkDataManager().setCustomBlockData(getWorld(), getX(), getZ(), data);
	}

	@Override
	public byte getCustomBlockData(int x, int y, int z) {
		byte[] data = getCustomBlockData();
		if (data == null) {
			return 0;
		}
		int index = ((x & 0xF) << xBitShifts) | ((z & 0xF) << zBitShifts) | (y & worldHeightMinusOne);
		return data[index];
	}

	@Override
	public byte setCustomBlockData(int x, int y, int z, byte data) {
		byte[] dats = getCustomBlockData();
		if (dats == null) {
			dats = new byte[16*16*worldHeight];
			setCustomBlockData(dats);
		}
		int index = ((x & 0xF) << xBitShifts) | ((z & 0xF) << zBitShifts) | (y & worldHeightMinusOne);
		byte old = dats[index];
		dats[index] = data;
		return old;
	}

	@Override
	public CustomBlock setCustomBlock(int x, int y, int z, CustomBlock block, byte data) {
		if (block == null) {
			throw new NullPointerException("Custom Block can not be null!");
		}
		short old = setCustomBlockId(x, y, z, (short) block.getCustomId());
		setCustomBlockData(x, y, z, data);
		return MaterialData.getCustomBlock(old);
	}
}
