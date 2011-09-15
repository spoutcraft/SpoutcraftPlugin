package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class GenericOverlayScreen extends GenericScreen implements OverlayScreen {

	int entityId;
	ScreenType screenType;
	
	public GenericOverlayScreen(int entityId, ScreenType type) {
		System.out.println("Created OverlayScreen of "+type);
		screenType = type;
		this.entityId = entityId;
	}

	@Override
	public int getNumBytes() {
		return super.getNumBytes() + 4;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		screenType = ScreenType.getType(input.readInt());
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		output.write(screenType.getCode());
	}

	@Override
	public WidgetType getType() {
		return WidgetType.OverlayScreen;
	}

	@Override
	public ScreenType getScreenType() {
		return screenType;
	}
}
