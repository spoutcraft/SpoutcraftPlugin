package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.plugin.Plugin;

public class GenericPopup extends GenericScreen implements PopupScreen{
	protected boolean transparent = false;
	public GenericPopup() {
		
	}
	
	@Override
	public int getVersion() {
		return super.getVersion() + 0;
	}
	
	@Override
	public int getNumBytes() {
		return super.getNumBytes() + 1;
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		this.setTransparent(input.readBoolean());
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		output.writeBoolean(isTransparent());
	}
	
	@Override
	public boolean isTransparent() {
		return transparent;
	}

	@Override
	public PopupScreen setTransparent(boolean value) {
		this.transparent = value;
		return this;
	}
	
	@Override
	public Widget setScreen(Plugin plugin, Screen screen) {
		if (this.screen != null && screen != null && screen != this.screen) {
			((InGameHUD) this.screen).closePopup();
		}
		return super.setScreen(plugin, screen);
	}
	
	@Override
	public WidgetType getType() {
		return WidgetType.PopupScreen;
	}
	
	@Override
	public boolean close() {
		if (getScreen() != null) {
			if (getScreen() instanceof InGameScreen) {
				return ((InGameScreen)getScreen()).closePopup();
			}
		}
		return false;
	}
}
