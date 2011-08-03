package org.getspout.spoutapi.packet;

import org.getspout.spoutapi.packet.listener.PacketListener;
import org.getspout.spoutapi.packet.standard.MCPacket;

public interface PacketManager {

	/**
	 * Returns a MCPacket instance with the default constructor.
	 * 
	 * An id of 256 will give an uncompressed Map Chunk packet 
	 * 
	 * @param packetId the id of the desired packet
	 * @return number of key bindings
	 */
	MCPacket getInstance(int packetId);
	
	/**
	 * adds a packet listener for uncompressed map chunk packets
	 * 
	 * @param listener the listener instance
	 */
	public void addListenerUncompressedChunk(PacketListener listener);
	
	/**
	 * adds a packet listener for packets of the given id
	 * 
	 * @param packetId the packet id
	 * @param listener the listener instance
	 */
	public void addListener(int packetId, PacketListener listener);
	
	/**
	 * removes a packet listener for uncompressed map chunk packets
	 * 
	 * @param listener the listener instance
	 * @return true if listener was removed
	 */
	public boolean removeListenerUncompressedChunk(PacketListener listener);

	/**
	 * removes a packet listener for packets of the given id
	 * 
	 * @param listener the listener instance
	 * @return true if listener was removed
	 */
	public boolean removeListener(int packetId, PacketListener listener);
	
	/**
	 * removes all packet listeners
	 * 
	 * @param listener the listener instance
	 * @return true if listener was removed
	 */
	public void clearAllListeners();
	
}
