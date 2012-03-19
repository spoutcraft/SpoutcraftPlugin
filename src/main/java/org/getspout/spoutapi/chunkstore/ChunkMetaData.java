/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.chunkstore;

import gnu.trove.iterator.TIntObjectIterator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import org.getspout.commons.inventory.ItemMap;
import org.getspout.commons.util.map.TByteShortByteKeyedMap;
import org.getspout.commons.util.map.TByteShortByteKeyedObjectHashMap;
import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutWorld;
import org.getspout.spoutapi.chunkstore.Utils.SerializedData;
import org.getspout.spoutapi.inventory.MaterialManager;

public class ChunkMetaData implements Serializable {
	private static final long serialVersionUID = 3L;

	// This data is saved. This means data can handle different map heights
	// Changes may be needed to the positionToKey method

	private int cx;
	private int cz;
	private UUID worldUid;

	//Storage for objects saved to this chunk
	private HashMap<String, Serializable> chunkData;

	//storage for custom block id's
	private short[] customBlockIds = null;

	//storage for local block data
	private TByteShortByteKeyedObjectHashMap<HashMap<String, Serializable>> blockData;

	private static final int CURRENT_VERSION = 3;
	private static final int MAGIC_NUMBER = 0xEA5EDEBB;

	transient private boolean dirty = false;

	//quais-final, need to be set in serialization
	transient private int worldHeight;
	transient private int worldHeightMinusOne;
	transient private int xBitShifts;
	transient private int zBitShifts;

	transient private ItemMap worldItemMap;
	transient private ItemMap serverItemMap;

	transient private boolean conversionNeeded;

	ChunkMetaData(UUID worldId, ItemMap worldItemMap, int cx, int cz) {
		blockData = new TByteShortByteKeyedObjectHashMap<HashMap<String, Serializable>>(100);
		chunkData = new HashMap<String, Serializable>();

		this.cx = cx;
		this.cz = cz;
		this.worldUid = worldId;

		SpoutWorld world = Spout.getServer().getWorld(this.worldUid);

		this.worldHeight = world != null ? world.getMaxHeight() : 128;
		this.xBitShifts = world != null ? world.getXBitShifts() : 11;
		this.zBitShifts = world != null ? world.getZBitShifts() : 7;
		worldHeightMinusOne = worldHeight - 1;

		this.worldItemMap = worldItemMap;
		this.serverItemMap = ItemMap.getRootMap();
		conversionNeeded = false;
	}

	/**
	 * True if this chunk's data has been altered and needs to be serialized into storage
	 * @return dirty
	 */
	public boolean isDirty() {
		return dirty;
	}

	/**
	 * Sets this chunk's data dirty flag
	 * @param dirty
	 */
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	public int getChunkX() {
		return cx;
	}

	public int getChunkZ() {
		return cz;
	}

	public UUID getWorldUID() {
		return worldUid;
	}

	/**
	 * Removes the data associated with the id at this chunk
	 * @param id of data
	 * @return data removed
	 */
	public Serializable removeChunkData(String id) {
		Serializable serial = chunkData.remove(id);
		if (serial != null) {
			dirty = true;
			return serial;
		}
		return null;
	}

	/**
	 * Gets the data associated with the id at this chunk.
	 *
	 * If the data is still in a serialized form, this will deserialize it.
	 * @param id of data
	 * @return data at the given id, or null if none found
	 */
	public Serializable getChunkData(String id) {
		Serializable serial = chunkData.get(id);
		if (serial != null && serial instanceof SerializedData) {
			try {
				serial = Utils.deserializeRaw(((SerializedData) serial).serialData);
				chunkData.put(id, serial);
			} catch (ClassNotFoundException e) {
				return null;
			} catch (IOException e) {
				return null;
			}
		}
		return serial;
	}

	public Serializable putChunkData(String id, Serializable o) {
		Serializable serial = chunkData.put(id, o);
		dirty = true;
		return serial;
	}
	/**
	 * Returns the array that is backing the block id data for this chunk.
	 *
	 * If the contents of the array are altered, setDirty(true) must be used so that the updated contents will be saved.
	 * Alternatively, use setCustomBlockIds(array) when you are finished manipulating the array and it will set the dirty flag for you.
	 *
	 * @return array of block id data for this chunk
	 */
	public short[] getCustomBlockIds() {
		return customBlockIds;
	}

	/**
	 * Sets the array that is used for the block id data for this chunk.
	 *
	 * This array will <b>override</b> any existing data, and wipe it out, so be sure this is what you intend to do.
	 * @param ids to set
	 */
	public void setCustomBlockIds(short[] ids) {
		customBlockIds = ids;
		setDirty(true);
	}

	public Serializable removeBlockData(String id, int x, int y, int z) {

		if (id.equals(MaterialManager.blockIdString)) {
			if (customBlockIds != null) {
				int key = ((x & 0xF) << xBitShifts) | ((z & 0xF) << zBitShifts) | (y & worldHeightMinusOne);
				short old = customBlockIds[key];
				if (old != 0) {
					dirty = true;
					customBlockIds[key] = 0;
				}
				return old;
			}
		} else {
			HashMap<String, Serializable> localBlockData = blockData.get(x, y, z);
			if (localBlockData != null) {
				Serializable old = localBlockData.remove(id);
				if (old != null) {
					dirty = true;
					if (localBlockData.size() == 0) {
						blockData.remove(x, y, z);
					}
				}
				return old;
			}
		}
		return null;
	}

	public Serializable getBlockData(String id, int x, int y, int z) {
		if (id.equals(MaterialManager.blockIdString)) {
			if (customBlockIds != null) {
				int key = ((x & 0xF) << xBitShifts) | ((z & 0xF) << zBitShifts) | (y & worldHeightMinusOne);
				return customBlockIds[key];
			}
		} else {
			HashMap<String, Serializable> localBlockData = blockData.get(x, y, z);
			if (localBlockData != null) {
				Serializable serial = localBlockData.get(id);
				//Check if we need to deserialize it
				if (serial != null && serial instanceof SerializedData) {
					try {
						serial = Utils.deserializeRaw(((SerializedData) serial).serialData);
						localBlockData.put(id, serial);
					} catch (ClassNotFoundException e) {
					} catch (IOException e) {
					}
				}

				return serial;
			}
		}
		return null;
	}

	public Serializable putBlockData(String id, int x, int y, int z, Serializable o) {

		if (id.equals(MaterialManager.blockIdString)) {
			if (customBlockIds == null) {
				customBlockIds = new short[16*16*worldHeight];
			}
			int key = ((x & 0xF) << xBitShifts) | ((z & 0xF) << zBitShifts) | (y & worldHeightMinusOne);
			customBlockIds[key] = ((Integer)o).shortValue();
			dirty = true;
		} else {
			HashMap<String, Serializable> localBlockData = blockData.get(x, y, z);
			if (localBlockData == null) {
				localBlockData = new HashMap<String, Serializable>();
				blockData.put(x, y, z, localBlockData);
			}
			localBlockData.put(id, o);
			dirty = true;
		}

		return o;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(MAGIC_NUMBER);
		out.writeInt(CURRENT_VERSION);

		out.writeLong(worldUid.getLeastSignificantBits());
		out.writeLong(worldUid.getMostSignificantBits());
		out.writeInt(cx);
		out.writeInt(cz);
		if (customBlockIds != null) {
			out.writeBoolean(true);
			for (int i = 0; i < (16* 16 * worldHeight); i++) {
				Integer worldId = worldItemMap.convertFrom(this.serverItemMap, customBlockIds[i]);
				if (worldId == null) {
					worldId = 0;
				}
				out.writeShort(worldId);
			}
			worldItemMap.save();
			serverItemMap.save();
		} else {
			out.writeBoolean(false);
		}
		out.writeInt(blockData != null ? blockData.size() : 0);
		if (blockData != null) {
			TIntObjectIterator<HashMap<String, Serializable>> i = blockData.iterator();
			while (i.hasNext()) {
				i.advance();
				int key = i.key();
				byte x = TByteShortByteKeyedMap.getXFromKey(key);
				short y = TByteShortByteKeyedMap.getYFromKey(key);
				byte z = TByteShortByteKeyedMap.getZFromKey(key);
				out.writeByte(x);
				out.writeShort(y);
				out.writeByte(z);
				writeMap(out, i.value());
			}
		}
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		blockData = new TByteShortByteKeyedObjectHashMap<HashMap<String, Serializable>>(100);
		chunkData = new HashMap<String, Serializable>();

		int fileVersionNumber; // can be used to determine the format of the file

		long lsb = in.readLong();
		if (((int)(lsb >> 32)) == MAGIC_NUMBER) {
			fileVersionNumber = (int)lsb;
			lsb = in.readLong();
		} else {
			fileVersionNumber = 0;
		}

		long msb = in.readLong();
		worldUid = new UUID(msb, lsb);
		cx = in.readInt();
		cz = in.readInt();
		boolean customBlockIdsExist = in.readBoolean();

		//Constructor is not invoked, need to set these fields
		SpoutWorld world = Spout.getServer().getWorld(this.worldUid);

		this.worldHeight = world.getMaxHeight();
		this.xBitShifts = world.getXBitShifts();
		this.zBitShifts = world.getZBitShifts();
		worldHeightMinusOne = worldHeight - 1;

		if (customBlockIdsExist) {
			if (fileVersionNumber >= 2) {
				conversionNeeded = true;
			}
			
			customBlockIds = new short[16 * 16 * worldHeight];
			int size = (16* 16 * worldHeight);
			if (fileVersionNumber < 3) {
				size = 16 *16 * 128;
			}
			for (int i = 0; i < size; i++) {
				if (fileVersionNumber > 2) {
					customBlockIds[i] = in.readShort();
				}
				else {
					int oldX = (i >> 11) & 0xF;
					int oldY = i & 0x7F;
					int oldZ = (i >> 7) & 0xF;
					int newKey = ((oldX & 0xF) << 12) | ((oldZ & 0xF) << 8) | (oldY & 0xFF);
					customBlockIds[newKey] = in.readShort();
				}
			}
		}
		int size = in.readInt();
		for (int i = 0; i < size; i++) {
			int x = in.readByte();
			int y = in.readShort();
			int z = in.readByte();
			HashMap<String, Serializable> map = readMap(in);
			blockData.put(x, y, z, map);
		}

		if (fileVersionNumber < CURRENT_VERSION) {
			dirty = true;
		}
	}

	public void setWorldItemMap(ItemMap worldItemMap) {
		this.serverItemMap = ItemMap.getRootMap();
		this.worldItemMap = worldItemMap;
		if (conversionNeeded) {
			convertIds(worldItemMap);
		}
	}

	private void convertIds(ItemMap worldItemMap) {
		int length = customBlockIds.length;
		for (int i = 0; i < length; i++) {

			Integer globalId = worldItemMap.convertTo(serverItemMap, customBlockIds[i]);

			if (globalId == null) {
				System.out.println("Custom id " + customBlockIds[i] + " does not exist in custom item map, replacing with 0");
				globalId = 0;
			}
			customBlockIds[i] = (short)(int)globalId;
		}
		conversionNeeded = false;
	}

	private void writeMap(ObjectOutputStream out, HashMap<String, Serializable> map) throws IOException {
		if (map == null) {
			out.writeBoolean(false);
			return;
		} else {
			out.writeBoolean(true);
		}

		out.writeObject(map);

	}

	@SuppressWarnings("unchecked")
	private HashMap<String, Serializable> readMap(ObjectInputStream in) throws IOException {

		if (!in.readBoolean()) {
			return null;
		}

		HashMap<String, Serializable> map = new HashMap<String, Serializable>();

		try {
			map = (HashMap<String, Serializable>) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (OptionalDataException ode) {
			if (ode.eof) {
				throw new RuntimeException("EOF reached", ode);
			} else {
				throw new RuntimeException("Primitive data in object stream of length " + ode.length, ode);
			}
		}

		return map;
	}
}
