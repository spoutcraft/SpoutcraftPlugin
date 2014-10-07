/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
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
import java.util.concurrent.ConcurrentSkipListSet;

import gnu.trove.map.hash.TIntIntHashMap;

import net.minecraft.server.v1_6_R3.Chunk;
import net.minecraft.server.v1_6_R3.ChunkProviderServer;
import net.minecraft.server.v1_6_R3.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_6_R3.CraftChunk;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;

import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.SpoutWorld;
import org.getspout.spoutapi.block.SpoutChunk;
import org.getspout.spoutapi.material.CustomBlock;
import org.getspout.spoutapi.material.MaterialData;

public class SpoutCraftChunk extends CraftChunk implements SpoutChunk {
	protected final ConcurrentHashMap<Integer, Integer> queuedId = new ConcurrentHashMap<Integer, Integer>();
	protected final ConcurrentHashMap<Integer, Byte> queuedData = new ConcurrentHashMap<Integer, Byte>();
    //TODO Very experimental...may need to be reverted
	protected static final Set<SpoutCraftChunk> queuedChunks = new ConcurrentSkipListSet<SpoutCraftChunk>();
    //protected static final Set<SpoutCraftChunk> queuedChunks = Collections.newSetFromMap(new ConcurrentHashMap<SpoutCraftChunk, Boolean>());

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

	private Block getBlockFromPos(int pos) {
		int x = (pos >> xBitShifts) & 0xF;
		int y = (pos) & worldHeightMinusOne;
		int z = (pos >> zBitShifts) & 0xF;

		return getBlock(x, y, z);

	}

	public void onTick() {
        //Apply queued ids on blocks
        if (!queuedId.isEmpty()) {
            final Iterator<Entry<Integer, Integer>> i = queuedId.entrySet().iterator();
            while (i.hasNext()) {
                final Entry<Integer, Integer> entry = i.next();
                Block block = getBlockFromPos(entry.getKey());
                block.setTypeId(entry.getValue());
                i.remove();
            }
        }
        //Apply queued data on blocks
        if (queuedId.isEmpty()) {
            final Iterator<Entry<Integer, Byte>> j = queuedData.entrySet().iterator();
            while (j.hasNext()) {
                //If another thread adds to the id queue, we need to halt and let the next tick apply it
                //TODO Even possible, needed? Doesn't add much overhead to check this...
                if (!queuedId.isEmpty()) {
                    break;
                }
                final Entry<Integer, Byte> entry = j.next();
                final Block block = getBlockFromPos(entry.getKey());
                block.setData(entry.getValue());
                j.remove();
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
                    if (reset) {
						if (c.bukkitChunk instanceof SpoutCraftChunk) {
							((SpoutCraftChunk) c.bukkitChunk).onReset();
						}
						resetBukkitChunk(c.bukkitChunk);
					} else {
						replaceBukkitChunk(c.bukkitChunk);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static SpoutCraftChunk getChunkSafe(org.bukkit.Chunk chunk) {
		if (chunk == null) {
			return null;
		}
		if (replaceBukkitChunk(chunk)) {
			return (SpoutCraftChunk) ((CraftChunk)chunk).getHandle().bukkitChunk;
		}
		return (SpoutCraftChunk) chunk;
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
