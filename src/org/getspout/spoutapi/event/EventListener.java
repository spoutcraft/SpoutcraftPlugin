package org.getspout.spoutapi.event;

public interface EventListener<T extends SpoutEvent> extends java.util.EventListener {

	/**
	 * Event handler method
	 * 
	 * @param event Event object
	 */
	public void onEvent(T event);
}
