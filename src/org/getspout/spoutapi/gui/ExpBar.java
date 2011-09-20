package org.getspout.spoutapi.gui;

import java.util.UUID;

public class ExpBar extends GenericWidget {

	public ExpBar() {
		super();
		setX(427 / 2 - 91); // 122
		setY(211);
		setAnchor(WidgetAnchor.BOTTOM_CENTER);
	}

	@Override
	public UUID getId() {
		return new UUID(0, 6);
	}

	public WidgetType getType() {
		return WidgetType.ExpBar;
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 1;
	}
}
