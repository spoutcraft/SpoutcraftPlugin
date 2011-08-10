package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ScreenCloseEvent;
import org.getspout.spoutapi.event.screen.ScreenEvent;
import org.getspout.spoutapi.event.screen.ScreenOpenEvent;
import org.getspout.spoutapi.gui.InGameScreen;
import org.getspout.spoutapi.gui.ScreenType;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketScreenAction implements SpoutPacket{
	protected byte action = -1;
	protected byte screen = -1; // UnknownScreen
		
	@Override
	public int getNumBytes() {
		return 2;
	}
	
	public PacketScreenAction() { }
	
	public PacketScreenAction(ScreenAction action,ScreenType screen) {
		this.action = (byte) action.getId();
		this.screen = (byte) screen.getCode();
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		action = input.readByte();
		screen = input.readByte();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(action);
		output.writeByte(screen);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		ScreenEvent event;
		switch(ScreenAction.getScreenActionFromId(action)){
			case Close:
				event = new ScreenCloseEvent(player, player.getMainScreen().getActivePopup(), ScreenType.getType(this.screen) );
				Bukkit.getServer().getPluginManager().callEvent(event);
				if (!event.isCancelled()) {
					this.action = (byte) ScreenAction.Close.getId();
					player.sendPacket(this);
				}
				break;
			case Open:
				event = new ScreenOpenEvent(player, player.getMainScreen().getActivePopup(), ScreenType.getType(this.screen));
				Bukkit.getServer().getPluginManager().callEvent(event);
				if (!event.isCancelled()) {
					this.action = (byte) ScreenAction.Open.getId();
					player.sendPacket(this);
				}
				break;
		}
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketScreenAction;
	}
	
	@Override
	public int getVersion() {
		return 1;
	}

}
