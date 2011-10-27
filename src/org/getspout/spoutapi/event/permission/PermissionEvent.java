package org.getspout.spoutapi.event.permission;

public interface PermissionEvent{
	
	/**
	 * Gets the default result of this event, if unmodified.
	 * @return default result
	 */
	public boolean getDefaultResult();
	
	/**
	 * Gets the result of this event.
	 * 
	 * If true, the action will be allowed
	 * @return result
	 */
	public boolean getResult();
	
	/**
	 * Sets the result of this event.
	 * @param result to set
	 */
	public void setResult(boolean result);

}
