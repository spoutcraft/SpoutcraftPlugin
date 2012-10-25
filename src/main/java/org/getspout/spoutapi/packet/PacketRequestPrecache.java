package org.getspout.spoutapi.packet;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.getspout.spout.Spout;
import org.getspout.spout.precache.PrecacheManager;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketRequestPrecache implements SpoutPacket {
	
	private String plugin;
	
	public PacketRequestPrecache() {
	}
	
	@Override
	public void readData(SpoutInputStream input) throws IOException {
		plugin = input.readString();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeString(plugin);
	}

	@Override
	public void run(int playerId) {
		
		Plugin plugin = Bukkit.getPluginManager().getPlugin(this.plugin);
		
		File preCache = PrecacheManager.getPluginCacheZip(plugin);
		if (preCache.exists()) {
			SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
			if (player != null) {
				player.sendPacket(new PacketSendPrecache(plugin, preCache));
			}
		}
	}

	@Override
	public void failure(int playerId) {
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketRequestPrecache;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
