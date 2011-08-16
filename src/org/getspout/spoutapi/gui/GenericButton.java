package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.getspout.spoutapi.packet.PacketUtil;

public class GenericButton extends GenericControl implements Button {

	protected GenericLabel label = (GenericLabel) new GenericLabel().setAlign(WidgetAnchor.TOP_CENTER);
	protected String disabledText = "";
	protected int hoverColor = 0xffffa0;
	public GenericButton() {
		
	}
	
	@Override
	public int getVersion() {
		return super.getVersion() + 0;
	}
	
	public GenericButton(String text) {
		setText(text);
	}
	
	@Override
	public int getNumBytes() {
		return super.getNumBytes() + label.getNumBytes() + PacketUtil.getNumBytes(getDisabledText()) + 4;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		label.readData(input);
		setDisabledText(PacketUtil.readString(input));
		setHoverColor(input.readInt());
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		label.writeData(output);
		PacketUtil.writeString(output, getDisabledText());
		output.writeInt(getHoverColor());
	}

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public Label setText(String text) {
		label.setText(text);
		return this;
	}
	
	@Override
	@Deprecated
	public Align getAlignX() {
		return label.getAlignX();
	}
	
	@Override
	@Deprecated
	public Widget setAlignX(Align pos) {
		label.setAlignX(pos);
		return this;
	}

	@Override
	public int getHexColor() {
		return label.getHexColor();
	}

	@Override
	public Label setHexColor(int hex) {
		label.setHexColor(hex);
		return this;
	}

	@Override
	public String getDisabledText() {
		return disabledText;
	}

	@Override
	public Button setDisabledText(String text) {
		disabledText = text;
		return this;
	}
	
	@Override
	public int getHoverColor() {
		return hoverColor;
	}
	
	@Override
	public Button setHoverColor(int hexColor) {
		this.hoverColor = hexColor;
		return this;
	}
	
	@Override
	public WidgetType getType() {
		return WidgetType.Button;
	}
	
	@Override
	public void render() {}

	@Override
	public Label setAuto(boolean auto) {
		label.setAuto(auto);
		return this;
	}

	@Override
	public boolean getAuto() {
		return label.getAuto();
	}

	@Override
	@Deprecated
	public Align getAlignY() {
		return label.getAlignY();
	}

	@Override
	@Deprecated
	public Widget setAlignY(Align pos) {
		return label.setAlignY(pos);
	}

	@Override
	public WidgetAnchor getAlign() {
		return label.getAlign();
	}

	@Override
	public Widget setAlign(WidgetAnchor pos) {
		return label.setAlign(pos);
	}

}
