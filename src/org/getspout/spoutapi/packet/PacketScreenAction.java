package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.screen.ScreenCloseEvent;
import org.getspout.spoutapi.gui.InGameScreen;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketScreenAction implements SpoutPacket{
	protected byte action = -1;
	protected byte accepted = 1;
	@Override
	public int getNumBytes() {
		return 2;
	}
	
	public PacketScreenAction() { }
	
	public PacketScreenAction(ScreenAction action) {
		this.action = (byte) action.getId();
		this.accepted = 2;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		action = input.readByte();
		accepted = input.readByte();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeByte(action);
		output.writeByte(accepted);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		if (player != null && action == ScreenAction.ScreenClose.getId()) {
			if (player.getMainScreen().getActivePopup() != null) {
				ScreenCloseEvent event = new ScreenCloseEvent(player, player.getMainScreen().getActivePopup());
				Bukkit.getServer().getPluginManager().callEvent(event);
				if (event.isCancelled()) {
					accepted = 0;
				}
				else {
					((InGameScreen)player.getMainScreen()).clearPopup();
					accepted = 1;
				}
				player.sendPacket(this); //return the response
			}
		}
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketScreenAction;
	}

}
