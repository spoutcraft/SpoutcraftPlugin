package org.getspout.spoutapi.packet;

import java.io.File;
import java.io.IOException;

import org.getspout.spout.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketRequestPrecache implements SpoutPacket {

	public PacketRequestPrecache() {
	}
	
	@Override
	public void readData(SpoutInputStream input) throws IOException {
		
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		
	}

	@Override
	public void run(int playerId) {
		File preCache = new File(Spout.getInstance().getDataFolder(), "precache.zip");
		if (preCache.exists()) {
			SpoutPlayer player = SpoutManager.getPlayerFromId(playerId);
			if (player != null) {
				player.sendPacket(new PacketSendPrecache(preCache));
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
