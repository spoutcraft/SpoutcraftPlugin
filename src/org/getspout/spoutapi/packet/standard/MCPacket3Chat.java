package org.getspout.spoutapi.packet.standard;

public interface MCPacket3Chat extends MCPacket {

	/**
	 * @return the chat message
	 */
	public String getMessage();
	
	/**
	 * @param message the chat message
	 */
	public void setMessage(String message);
	
}
