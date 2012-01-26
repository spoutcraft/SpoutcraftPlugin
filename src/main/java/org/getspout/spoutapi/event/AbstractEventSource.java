/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"rawtypes", "unchecked"})
@Deprecated
public abstract class AbstractEventSource implements EventSource {
	protected Map<Class<? extends SpoutEvent>, Set<EventListener>> eventListeners = new HashMap<Class<? extends SpoutEvent>, Set<EventListener>>();

	@Override
	public <T extends SpoutEvent> void addEventListener(Class<T> type, EventListener<T> listener) {
		Set<EventListener> listeners = this.eventListeners.get(type);

		if (listeners == null) {
			this.eventListeners.put(type, listeners = new HashSet<EventListener>());
		}

		listeners.add(listener);
	}

	@Override
	public <T extends SpoutEvent> void callEvent(T event) {
		for (Class<? extends SpoutEvent> cls : this.eventListeners.keySet()) {
			if (!cls.isAssignableFrom(event.getClass())) {
				continue;
			}

			for (EventListener listener : this.eventListeners.get(cls)) {
				listener.onEvent(event);
			}
		}
	}
}
