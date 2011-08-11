package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.entity.Entity;

public class PacketEntitySkin implements SpoutPacket {
	protected String texture = "";
	protected int entityId;
	protected boolean isMainTexture = true;
	
	public PacketEntitySkin(Entity entity, String texture, boolean main){
		this.entityId = entity.getEntityId();
		this.texture = texture;
		this.isMainTexture = main;
	}
	
	@Override
	public int getNumBytes() {
		return PacketUtil.getNumBytes(texture) + 4 + 1;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		entityId = input.readInt();
		isMainTexture = input.readBoolean();
		texture = PacketUtil.readString(input);
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(entityId);
		output.writeBoolean(isMainTexture);
		PacketUtil.writeString(output, texture);
	}

	@Override
	public void run(int PlayerId) {
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketEntitySkin;
	}

	@Override
	public int getVersion() {
		return 0;
	}

}
