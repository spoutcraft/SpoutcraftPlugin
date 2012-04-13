/*
 * This file is part of SpoutPluginAPI (http://www.spout.org/).
 *
 * SpoutPluginAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPluginAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.packet;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.gui.WidgetType;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

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
	public void readData(SpoutInputStream input) throws IOException {
		int id = input.readInt();
		long msb = input.readLong();
		long lsb = input.readLong();
		
		int size = input.readInt();
		byte[] widgetData = new byte[size];
		input.read(widgetData);
		SpoutInputStream data = new SpoutInputStream(ByteBuffer.wrap(widgetData));
		
		int version = input.readShort();
		screen = new UUID(msb, lsb);
		WidgetType widgetType = WidgetType.getWidgetFromId(id);
		if (widgetType != null) {
			try {
				widget = widgetType.getWidgetClass().newInstance();
				if (widget.getVersion() == version) {
					widget.readData(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(widget.getType().getId());
		output.writeLong(screen.getMostSignificantBits());
		output.writeLong(screen.getLeastSignificantBits());
		
		SpoutOutputStream data = new SpoutOutputStream();
		widget.writeData(data);
		ByteBuffer buffer = data.getRawBuffer();
		byte[] widgetData = new byte[buffer.capacity() - buffer.remaining()];
		System.arraycopy(buffer.array(), 0, widgetData, 0, widgetData.length);
		
		output.writeInt(widgetData.length);
		output.writeShort((short) widget.getVersion());
		output.write(widgetData);
	}

	@Override
	public void run(int PlayerId) {

	}

	@Override
	public void failure(int id) {

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
