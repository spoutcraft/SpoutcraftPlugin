package org.getspout.spoutapi.packet;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

public class PacketPermissionUpdate implements SpoutPacket {
	private Map<String, Boolean> permissions;
	
	public PacketPermissionUpdate(Map<String, Boolean> permissions) {
		this.permissions = permissions;
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(permissions.size());
		for(Entry<String, Boolean> perm:permissions.entrySet()) {
			output.writeString(perm.getKey());
			output.writeBoolean(perm.getValue());
		}
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketPermissionUpdate;
	}

	@Override
	public int getVersion() {
		return 0;
	}
	
	@Override
	public void readData(SpoutInputStream input) throws IOException {}
	
	@Override
	public void run(int playerId) {}
	
	@Override
	public void failure(int playerId) {}
}
