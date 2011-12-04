package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.block.design.BlockDesign;
import org.getspout.spoutapi.block.design.GenericBlockDesign;

public class PacketCustomBlockDesign implements SpoutPacket {

	private short customId;
	private BlockDesign design;
	
	public PacketCustomBlockDesign() {
	}
	
	public PacketCustomBlockDesign(short customId, BlockDesign design) {
		this.design = design;
		this.customId = customId;
	}

	public int getNumBytes() {
		int designBytes = (design == null) ? design.getResetNumBytes() : design.getNumBytes();
		return designBytes + 2;
	}
	
	public void readData(DataInputStream input) throws IOException {
		customId = input.readShort();
		design = new GenericBlockDesign();
		design.read(input);
		if (design.getReset()) {
			design = null;
		}
	}
	
	public void writeData(DataOutputStream output) throws IOException {
		output.writeShort(customId);
		if (design != null) {
			design.write(output);
		}
		else {
			new GenericBlockDesign().writeReset(output);
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
		return design.getVersion() + 2;
	}

}
