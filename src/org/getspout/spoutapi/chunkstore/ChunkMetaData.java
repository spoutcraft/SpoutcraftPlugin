/*
 * This file is part of Spout (http://wiki.getspout.org/).
 * 
 * Spout is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout is distributed in the hope that it will be useful,
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
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutWorld;
import org.getspout.spoutapi.chunkstore.Utils.SerializedData;
import org.getspout.spoutapi.inventory.MaterialManager;
import org.getspout.spoutapi.util.map.TByteTripleObjectHashMap;

public class ChunkMetaData implements Serializable {

	private static final long serialVersionUID = 2L;

	// This data is saved. This means data can handle different map heights
	// Changes may be needed to the positionToKey method

	private int cx;
	private int cz;
	private UUID worldUid;

	//Storage for objects saved to this chunk
	private HashMap<String, Serializable> chunkData = new HashMap<String, Serializable>();
	
	//storage for custom block id's
	private short[] customBlockIds = null;
	
	//storage for local block data
	private TByteTripleObjectHashMap<HashMap<String, Serializable>> blockData = new TByteTripleObjectHashMap<HashMap<String, Serializable>>(100);
	
	transient private boolean dirty = false;
	
	//quais-final, need to be set in serialization
	transient private int worldHeight;
	transient private int worldHeightMinusOne;
	transient private int xBitShifts;
	transient private int zBitShifts;

	ChunkMetaData(UUID worldId, int cx, int cz) {
		this.cx = cx;
		this.cz = cz;
		this.worldUid = worldId;
		
		SpoutWorld world = Spout.getServer().getWorld(this.worldUid);
		
		this.worldHeight = world != null ? world.getMaxHeight() : 128;
		this.xBitShifts = world != null ? world.getXBitShifts() : 11;
		this.zBitShifts = world != null ? world.getZBitShifts() : 7;
		worldHeightMinusOne = worldHeight - 1;
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
		}
		else {
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
		}
		else {
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
		}
		else {
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
		out.writeLong(worldUid.getLeastSignificantBits());
		out.writeLong(worldUid.getMostSignificantBits());
		out.writeInt(cx);
		out.writeInt(cz);
		if (customBlockIds != null) {
			out.writeBoolean(true);
			for (int i = 0; i < (16* 16 * worldHeight); i++) {
				out.writeShort(customBlockIds[i]);
			}
		}
		else {
			out.writeBoolean(false);
		}
		out.writeInt(blockData != null ? blockData.size() : 0);
		if (blockData != null) {
			TIntObjectIterator<HashMap<String, Serializable>> i = blockData.iterator();
			while (i.hasNext()) {
				out.writeInt(i.key());
				writeMap(out, i.value());
				i.advance();
			}
		}
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		long lsb = in.readLong();
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
			customBlockIds = new short[16 * 16 * worldHeight];
			for (int i = 0; i < (16* 16 * worldHeight); i++) {
				customBlockIds[i] = in.readShort();
			}
		}
		int size = in.readInt();
		for (int i = 0; i < size; i++) {
			int x = ((i >> xBitShifts) & 0xF) + cx * 16;
			int y = i & worldHeightMinusOne;
			int z = ((i >> zBitShifts) & 0xF) + cz * 16;
			blockData.put(x, y, z, readMap(in));
		}
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
		}

		return map;

	}
}
