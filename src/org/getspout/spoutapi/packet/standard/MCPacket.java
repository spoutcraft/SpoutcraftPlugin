package org.getspout.spoutapi.packet.standard;


public interface MCPacket {
	
	/**
	 * @return the packet id of the packet
	 */
	public int getId();
	
	
	/**
	 * @return the raw minecraft packet
	 */
	@Deprecated
	public Object getPacket();
	
}
