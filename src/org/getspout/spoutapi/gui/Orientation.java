package org.getspout.spoutapi.gui;

public enum Orientation {
	/**
	 * Horizontal axis (left-right)
	 */
	HORIZONTAL,
	/**
	 * Vertical axis (top-bottom)
	 */
	VERTICAL,
	;
	
	public Orientation getOther() {
		switch(this) {
		case HORIZONTAL:
			return VERTICAL;
		case VERTICAL:
			return HORIZONTAL;
		}
		return null;
	}
}
