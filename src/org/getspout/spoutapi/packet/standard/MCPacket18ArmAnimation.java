package org.getspout.spoutapi.packet.standard;

public interface MCPacket18ArmAnimation extends MCPacket {
	
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
	 * 0: None
	 * 1: Swing Arm
	 * 2: Damage
	 * 3: Leave bed
	 * 102: ?
	 * 104: Crouch
	 * 105: Uncrouch
	 * 
	 * @return the bed field
	 */
	public int getAnimate();
	
	/**
	 * @param the bed field
	 */
	public void setAnimate(int animate);

}
