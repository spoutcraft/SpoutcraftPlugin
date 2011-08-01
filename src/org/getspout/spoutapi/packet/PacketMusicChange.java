package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.sound.BackgroundMusicEvent;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.sound.Music;

public class PacketMusicChange implements SpoutPacket{
	protected int id;
	protected int volumePercent;
	boolean cancel = false;
	
	public PacketMusicChange() {
		
	}
	
	public PacketMusicChange(int music, int volumePercent) {
		this.id = music;
		this.volumePercent = volumePercent;
	}
	
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public int getNumBytes() {
		return 9;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		id = input.readInt();
		volumePercent = input.readInt();
		cancel =  input.readBoolean();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(id);
		output.writeInt(volumePercent);
		output.writeBoolean(cancel);
	}

	@Override
	public void run(int playerId) {
		SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
		Music music = Music.getMusicFromId(id);
		if (player != null && music != null) {
			BackgroundMusicEvent event = new BackgroundMusicEvent(music, volumePercent, player);
			Bukkit.getServer().getPluginManager().callEvent(event);
			if (event.isCancelled()) {
				cancel = true;
			}
			player.sendPacket(this);
		}
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketMusicChange;
	}

}
