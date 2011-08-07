package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GenericGradient extends GenericWidget implements Gradient {
	
	protected int color1, color2;
	
	public GenericGradient() {
		
	}
	
	public Gradient setTopColor(int color) {
		this.color1 = color;
		return this;
	}
	
	public Gradient setBottomColor(int color) {
		this.color2 = color;
		return this;
	}
	
	public int getTopColor() {
		return this.color1;
	}
	
	public int getBottomColor() {
		return this.color2;
	}

	@Override
	public WidgetType getType() {
		return WidgetType.Gradient;
	}
	
	@Override
	public int getNumBytes() {
		return super.getNumBytes() + 8;
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		this.setTopColor(input.readInt());
		this.setBottomColor(input.readInt());
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(getTopColor());
		output.writeInt(getBottomColor());
	}
	
	@Override
	public void render() {
		//
	}

}
