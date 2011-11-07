package org.getspout.spoutapi.event;

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
