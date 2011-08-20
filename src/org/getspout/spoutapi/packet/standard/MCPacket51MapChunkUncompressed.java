package org.getspout.spoutapi.packet.standard;

public interface MCPacket51MapChunkUncompressed extends MCPacket51MapChunk {

	/**
	 * The chunk data contains 4 sections
	 * 
	 *  type data:	  1 byte per block
	 *  meta data:	  1 nibble per block
	 *  block light:	1 nibble per block
	 *  sunlight level: 1 nibble per block
	 * 
	 * The data in each section can be indexed using
	 * 
	 *  data[x, y, z] = section[y + z*(sizeY + 1) + x*(sizeY + 1)*(sizeZ + 1)
	 * 
	 * For the last 3 sections, section[] is an array of nibbles.
	 * 
	 * At least one of the 3 size coordinates is even, which guarantees an even number of nibbles.
	 * 
	 * @return uncompressed chunk data 
	 */
	
	public byte[] getUncompressedChunkData();
	
}
