/*
 * This file is part of Spout API (http://wiki.getspout.org/).
 * 
 * Spout API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Spout API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.packet.PacketUtil;

public class GenericLabel extends GenericWidget implements Label{
	protected String text = "";
	protected WidgetAnchor align = WidgetAnchor.TOP_LEFT;
	protected Color color = new Color(1, 1, 1);
	protected boolean auto = true;
	public GenericLabel(){
		
	}

	public GenericLabel(String text) {
		this.text = text;
	}
	
	@Override
	public WidgetType getType() {
		return WidgetType.Label;
	}
	
	@Override
	public int getNumBytes() {
		return super.getNumBytes() + PacketUtil.getNumBytes(getText()) + 18;
	}
	
	@Override
	public int getVersion() {
		return super.getVersion() + 3;
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		this.setText(PacketUtil.readString(input));
		this.setAlign(WidgetAnchor.getAnchorFromId(input.readByte()));
		this.setAuto(input.readBoolean());
		this.setTextColor(PacketUtil.readColor(input));
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		PacketUtil.writeString(output, getText());
		output.writeByte(align.getId());
		output.writeBoolean(getAuto());
		PacketUtil.writeColor(output, getTextColor());
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public Label setText(String text) {
		this.text = text;
		return this;
	}
	
	@Override
	public boolean getAuto() {
		return auto;
	}
	
	@Override
	public Label setAuto(boolean auto) {
		this.auto = auto;
		return this;
	}

	@Override
	public WidgetAnchor getAlign() {
		return align;
	}

	@Override
	public Label setAlign(WidgetAnchor pos) {
		this.align = pos;
		return this;
	}

	@Override
	public Color getTextColor() {
		return color;
	}

	@Override
	public Label setTextColor(Color color) {
		this.color = color;
		return this;
	}
	
	@Override
	public void render() {

	}

	@Override
	@Deprecated
	public Align getAlignX() {
		switch (align) {
			case TOP_CENTER:
			case CENTER_CENTER:
			case BOTTOM_CENTER:
				return Align.SECOND;
			case TOP_RIGHT:
			case CENTER_RIGHT:
			case BOTTOM_RIGHT:
				return Align.THIRD;
			case TOP_LEFT:
			case CENTER_LEFT:
			case BOTTOM_LEFT:
			default:
				return Align.FIRST;
		}
	}

	@Override
	@Deprecated
	public Widget setAlignX(Align pos) {
		return this;
	}

	@Override
	@Deprecated
	public Align getAlignY() {
		switch (align) {
			case CENTER_LEFT:
			case CENTER_CENTER:
			case CENTER_RIGHT:
				return Align.SECOND;
			case BOTTOM_LEFT:
			case BOTTOM_CENTER:
			case BOTTOM_RIGHT:
				return Align.THIRD;
			case TOP_LEFT:
			case TOP_CENTER:
			case TOP_RIGHT:
			default:
				return Align.FIRST;
		}
	}

	@Override
	@Deprecated
	public Widget setAlignY(Align pos) {
		return this;
	}

}
