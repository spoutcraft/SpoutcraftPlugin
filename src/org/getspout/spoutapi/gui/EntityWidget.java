package org.getspout.spoutapi.gui;

public interface EntityWidget extends Widget {
	
	/**
	 * Sets the id of this entity
	 * @param id of the entity
	 * @return this
	 */
	public EntityWidget setEntityId(int id);
	
	/**
	 * Gets the id of this entity
	 * @return the id of this entity
	 */
	public int getEntityId();
}
