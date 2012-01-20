package org.getspout.spoutapi.event;

@Deprecated
public interface EventSource {

	/**
	 * Register event listener for specified Event type
	 * 
	 * @param <T> event type (example: ButtonClickEvent)
	 * @param type Class of specified event
	 * @param listener Event listener for specified event
	 */
	public <T extends SpoutEvent> void addEventListener(Class<T> type, EventListener<T> listener);

	/**
	 * Call specified event listeners
	 * 
	 * @param <T> event type
	 * @param event Event object instance
	 */
	public <T extends SpoutEvent> void callEvent(T event);
}
