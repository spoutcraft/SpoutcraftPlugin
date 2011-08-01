package org.getspout.spoutapi.event.screen;

import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.Slider;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SliderDragEvent extends ScreenEvent {
	protected Slider slider;
	protected float position;
	protected float old;
	public SliderDragEvent(SpoutPlayer player, Screen screen, Slider slider, float position) {
		super("SliderDragEvent", player, screen);
		this.slider = slider;
		this.position = position;
		this.old = slider.getSliderPosition();
	}
	
	public Slider getSlider() {
		return slider;
	}
	
	public float getOldPosition() {
		return old;
	}
	
	public float getNewPosition() {
		return position;
	}
	
	public void setNewPosition(float position) {
		this.position = position;
	}

}
