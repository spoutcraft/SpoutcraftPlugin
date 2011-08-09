package org.getspout.spoutapi.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.gui.WidgetType;

public class PacketWidget implements SpoutPacket {
	protected Widget widget;
	protected UUID screen;
	public PacketWidget() {

	}
	
	public PacketWidget(Widget widget, UUID screen) {
		this.widget = widget;
		this.screen = screen;
	}

	@Override
	public int getNumBytes() {
		return (widget != null ? widget.getNumBytes() : 0) + 26;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		int id = input.readInt();
		long msb = input.readLong();
		long lsb = input.readLong();
		int size = input.readInt();
		int version = input.readShort();
		screen = new UUID(msb, lsb);
		WidgetType widgetType = WidgetType.getWidgetFromId(id);
		if (widgetType != null) {
			try {
				widget = widgetType.getWidgetClass().newInstance();
				if (widget.getVersion() == version) {
					widget.readData(input);
				}
				else {
					input.skipBytes(size);
					System.out.println("Received invalid widget: " + widgetType + " v: " + version + " current v: " + widget.getVersion());
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(widget.getType().getId());
		output.writeLong(screen.getMostSignificantBits());
		output.writeLong(screen.getLeastSignificantBits());
		output.writeInt(widget.getNumBytes());
		output.writeShort(widget.getVersion());
		widget.writeData(output);
	}

	@Override
	public void run(int PlayerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketWidget;
	}
	
	@Override
	public int getVersion() {
		return 1;
	}

}
