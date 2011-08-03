package org.getspout.spoutapi.packet.standard;

public interface MCPacket51MapChunk {

	/**
	 * @return the x world block coordinate
	 */
	public int getX();
	
	/**
	 * @return the y world block coordinate
	 */
	public int getY();
	
	/**
	 * @return the z world block coordinate
	 */
	public int getZ();
	
	/**
	 * @param x sets the x world block coordinate
	 */
	public void setX(int x);
	
	/**
	 * @param y sets the y world block coordinate
	 */
	public void setY(int y);
	
	/**
	 * @param z sets the z world block coordinate
	 */
	public void setZ(int z);

	/**
	 * @return (X size of the cuboid) - 1
	 */
	public int getSizeX();
	
	/**
	 * @return (Y size of the cuboid) - 1
	 */
	public int getSizeY();
	
	/**
	 * @return (Z size of the cuboid) - 1
	 */
	public int getSizeZ();
	
	/**
	 * The cuboid to update must be completely within 1 chunk
	 * 
	 * @param x (Z size of cuboid) - 1
	 */
	public void setSizeX(int x);
	
	/**
	 * The cuboid to update must be completely within 1 chunk
	 * 
	 * @param y (Y size of cuboid) - 1
	 */
	public void setSizeY(int y);
	
	/**
	 * The cuboid to update must be completely within 1 chunk
	 * 
	 * @param z (Z size of cuboid) - 1
	 */
	public void setSizeZ(int z);

	/**
	 * @return chunk data compressed using Deflate
	 */
	public byte[] getCompressedChunkData();
	
}
