package org.getspout.spoutapi.packet.standard;

public interface MCPacketUnknown extends MCPacket {
	
	/**
	 * This allows unsupported Minecraft packets to be supported.  The Object can be cast to a net.minecraft.server.Packet object.
	 * 
	 * However, this is only a temporary solution.  New packets can be supported as required.
	 * 
	 * @return the raw Minecraft Packet
	 */
	public Object getRawPacket();

}
