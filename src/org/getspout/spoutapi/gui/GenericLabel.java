package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.packet.PacketUtil;

public class GenericLabel extends GenericWidget implements Label{
	protected String text = "";
	protected Align vAlign = Align.FIRST;
	protected Align hAlign = Align.FIRST;
	protected int hexColor = 0x000000;
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
		return super.getNumBytes() + PacketUtil.getNumBytes(getText()) + 6;
	}
	
	@Override
	public int getVersion() {
		return 1;
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		this.setText(PacketUtil.readString(input));
		this.setAlignX(Align.getAlign(input.readByte()));
		this.setAlignY(Align.getAlign(input.readByte()));
		this.setHexColor(input.readInt());
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		PacketUtil.writeString(output, getText());
		output.writeByte(hAlign.getId());
		output.writeByte(vAlign.getId());
		output.writeInt(getHexColor());
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
	public Align getAlignX() {
		return vAlign;
	}

	@Override
	public Align getAlignY() {
		return hAlign;
	}
	
	@Override
	public Label setAlignX(Align pos) {
		this.hAlign = pos;
		return this;
	}

	@Override
	public Label setAlignY(Align pos) {
		this.vAlign = pos;
		return this;
	}

	@Override
	public int getHexColor() {
		return hexColor;
	}

	@Override
	public Label setHexColor(int hex) {
		hexColor = hex;
		return this;
	}
	
	public void render() {

	}

}
