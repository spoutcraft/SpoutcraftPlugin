package org.getspout.spoutapi.gui;

public class GenericOverlayScreen extends GenericScreen implements OverlayScreen {

	ScreenType screenType;
	
	public GenericOverlayScreen(int entityId, ScreenType type) {
		super(entityId);
		screenType = type;
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
