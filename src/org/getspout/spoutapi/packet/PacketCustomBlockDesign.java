package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericBlockDesign;

public class PacketCustomBlockDesign implements SpoutPacket {

	private Integer blockId;
	private Integer metaData;
	private String name;
	private boolean opaque;
	private BlockDesign design;
	
	public PacketCustomBlockDesign() {
	}
	
	public PacketCustomBlockDesign(String name, boolean opaque, Integer blockId, Integer metaData, BlockDesign design) {
		this.design = design;
		this.blockId = blockId;
		this.metaData = metaData;
		this.opaque = opaque;
		this.name = name;
	}
	
	private void setBlockId(Integer blockId) {
		if (blockId == null) {
			this.blockId = -1;
		} else {
			this.blockId = blockId;
		}
	}
	
	private void setMetaData(Integer metaData) {
		if (metaData == null) {
			this.metaData = 0;
		} else {
			this.metaData = metaData;
		}
	}
	
	protected Integer getBlockId() {
		return blockId == -1 ? null : blockId;
	}
	
	protected Integer getMetaData() {
		return blockId == -1 ? null : metaData;
	}
	
	public int getNumBytes() {
		int designBytes = (design == null) ? design.getResetNumBytes() : design.getNumBytes();
		return 8 + designBytes + PacketUtil.getNumBytes(name) + 1;
	}
	
	public void readData(DataInputStream input) throws IOException {
		setBlockId(input.readInt());
		setMetaData(input.readInt());
		name = PacketUtil.readString(input);
		opaque = input.readBoolean();
		//TODO: Make this work like the Widget packet
		design = new GenericBlockDesign();
		design.read(input);
	}
	
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(blockId == null ? -1 : blockId);
		output.writeInt(metaData == null ? 0 : metaData);
		PacketUtil.writeString(output, name);
		output.writeBoolean(opaque);
		if (design != null) {
			design.write(output);
		} else {
			design.writeReset(output);
		}
	}
	
	public void run(int id) {
	}

	@Override
	public void failure(int id) {
		
	}

	public PacketType getPacketType() {
		return PacketType.PacketCustomBlockDesign;
	}

	@Override
	public int getVersion() {
		return design.getVersion() + 1;
	}

}
