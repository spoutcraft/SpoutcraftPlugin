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
public enum EventType {
	// Input Events \\
	Key_Binding,
	Key_Pressed,
	Key_Released,
	Render_Distance_Changed,

	// Inventory Events \\
	Inventory_Click,
	Inventory_Close,
	Inventory_Craft,
	Inventory_Open,
	Player_Enchant,

	// Permission Events - Left out because there is only one event \\

	// Screen Events \\
	Button_Click,
	Screen_Close,
	Screen_Open,
	Slider_Drag,
	TextField_Change,
	Screenshot_Received,

	// Sound Events - Left out because there is only one event \\

	// Spout Events \\
	Server_Tick,
	Spoutcraft_Enable,
	Spoutcraft_Failed;
}
