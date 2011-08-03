package org.getspout.spoutapi.packet.standard;

public interface MCPacket17 extends MCPacket {
	
	/**
	 * @return the player's entity id
	 */
	public int getEntityId();
	
	/**
	 * @param the player's entity id
	 */
	public void setEntityId(int entityId);
	
	
	/**
	 * 
	 * 0: Player entered bed?
	 * 
	 * @return the bed field
	 */
	public int getBed();
	
	/**
	 * @param the bed field
	 */
	public void setBed(int bed);
	
	public int getBlockX();
	
	public int getBlockY();
	
	public int getBlockZ();
	
	public void setBlockX(int x);
	
	public void setBlockY(int y);
	
	public void setBlockZ(int z);
}
