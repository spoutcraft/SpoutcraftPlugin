package org.getspout.spoutapi.packet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.getspout.spout.precache.PrecacheTuple;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

import org.bukkit.plugin.Plugin;

public class PacketValidatePrecache implements SpoutPacket {
	int count;
	PrecacheTuple[] plugins;

	public PacketValidatePrecache(HashMap<Plugin, Long> pluginMap) {
		count = pluginMap.size();
		plugins = new PrecacheTuple[count];
		int i = 0;
		for (Entry entry : pluginMap.entrySet()) {
			Plugin p = (Plugin) entry.getKey();
			plugins[i] = new PrecacheTuple(
					p.getDescription().getName(),
					p.getDescription().getVersion(),
					(Long) entry.getValue()
			);
			i++;
		}
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		count = input.readInt();
		if (count > 0) {
			plugins = new PrecacheTuple[count];
			for (int i = 0; i < count; i++) {
				String plugin = input.readString();
				String version = input.readString();
				long crc = input.readLong();
				plugins[i] = new PrecacheTuple(plugin, version, crc);
			}
		}
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(count);
		for (int i = 0; i < count; i++) {
			output.writeString(plugins[i].getPlugin());
			output.writeString(plugins[i].getVersion());
			output.writeLong(plugins[i].getCrc());
		}
	}

	@Override
	public void run(int playerId) {
	}

	@Override
	public void failure(int playerId) {

	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketValidatePrecache;
	}

	@Override
	public int getVersion() {
		return 0;
	}
}
