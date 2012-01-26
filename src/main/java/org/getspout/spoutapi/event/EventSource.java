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
