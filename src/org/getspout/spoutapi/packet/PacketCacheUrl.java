package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketCacheUrl implements SpoutPacket {
	private String plugin;
	private String url;
	
	public PacketCacheUrl() {
		
	}
	
	public PacketCacheUrl(String plugin, String url) {
		this.plugin = plugin;
		this.url = url;
	}

	@Override
	public int getNumBytes() {
		return PacketUtil.getNumBytes(plugin) + PacketUtil.getNumBytes(url);
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		this.plugin = PacketUtil.readString(input);
		this.url = PacketUtil.readString(input);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		PacketUtil.writeString(output, plugin);
		PacketUtil.writeString(output, url);
	}

	@Override
	public void run(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void failure(int playerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCacheUrl;
	}

	@Override
	public int getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

}
