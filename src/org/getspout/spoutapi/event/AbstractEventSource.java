package org.getspout.spoutapi.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractEventSource implements EventSource {

	protected Map<Class<? extends SpoutEvent>, Set<EventListener>> eventListeners = new HashMap<Class<? extends SpoutEvent>, Set<EventListener>>();
	
	@Override
	public <T extends SpoutEvent> void addEventListener(Class<T> type, EventListener<T> listener) {
		Set<EventListener> listeners = this.eventListeners.get(type);
		
		if(listeners == null){
			this.eventListeners.put(type, listeners = new HashSet<EventListener>());
		}
		
		listeners.add(listener);
	}

	@Override
	public <T extends SpoutEvent> void callEvent(T event) {		
		for(Class<? extends SpoutEvent> cls : this.eventListeners.keySet()){
			if(!cls.isAssignableFrom(event.getClass())){
				continue;
			}
			
			for (EventListener listener : this.eventListeners.get(cls)){
				listener.onEvent(event);
			}
		}
	}
	
}
