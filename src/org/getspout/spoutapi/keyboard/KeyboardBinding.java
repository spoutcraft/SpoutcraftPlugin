package org.getspout.spoutapi.keyboard;

import org.getspout.spoutapi.player.SpoutPlayer;

public interface KeyboardBinding {
	
	public void onPreKeyPress(SpoutPlayer player);
	
	public void onPostKeyPress(SpoutPlayer player);
	
	public void onPreKeyRelease(SpoutPlayer player);
	
	public void onPostKeyRelease(SpoutPlayer player);
}
