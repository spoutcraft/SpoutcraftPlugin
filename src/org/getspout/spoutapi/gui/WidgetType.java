/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.gui;

import java.util.HashMap;

public enum WidgetType {
	Label(0, GenericLabel.class, false),
	HealthBar(1, HealthBar.class, false),
	BubbleBar(2, BubbleBar.class, false),
	ChatBar(3, ChatBar.class, false),
	ChatTextBox(4, ChatTextBox.class, false),
	ArmorBar(5, ArmorBar.class, false),
	Texture(6, GenericTexture.class, false),
	PopupScreen(7, GenericPopup.class, false),
	InGameScreen(8, InGameScreen.class, false),
	ItemWidget(9, GenericItemWidget.class, false),
	Button(10, GenericButton.class, false),
	Slider(11, GenericSlider.class, false),
	TextField(12, GenericTextField.class, false),
	Gradient(13, GenericGradient.class, false),
	Container(14, GenericContainer.class, true), 
	EntityWidget(15, GenericEntityWidget.class, false),
	OverlayScreen(16, GenericOverlayScreen.class, false),
	HungerBar(17, HungerBar.class, false),
	;
	
	private final int id;
	private final boolean serverOnly;
	private final Class<? extends Widget> widgetClass;
	private static final HashMap<Integer, WidgetType> lookupId = new HashMap<Integer, WidgetType>();
	WidgetType(final int id, final Class<? extends Widget> widgetClass, final boolean serverOnly) {
		this.id = id;
		this.widgetClass = widgetClass;
		this.serverOnly = serverOnly;
	}
	
	public int getId() {
		return id;
	}
	
	public Class<? extends Widget> getWidgetClass() {
		return widgetClass;
	}
	
	public static WidgetType getWidgetFromId(int id) {
		return lookupId.get(id);
	}
	
	static {
		for (WidgetType packet : values()) {
			lookupId.put(packet.getId(), packet);
		}
	}

	public boolean isServerOnly() {
		return serverOnly;
	}

}
