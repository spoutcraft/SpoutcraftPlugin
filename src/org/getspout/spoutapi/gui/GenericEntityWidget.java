package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GenericEntityWidget extends GenericWidget implements EntityWidget {


	private int entityid = 0;
	
	@Override
	public WidgetType getType() {
		return WidgetType.EntityWidget;
	}

	@Override
	public void render() {
	}

	@Override
	public void setEntityID(int entity) {
		entityid = entity;
	}

	@Override
	public int getEntityID() {
		return entityid;
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		entityid = input.readInt();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(entityid);
	}
}
