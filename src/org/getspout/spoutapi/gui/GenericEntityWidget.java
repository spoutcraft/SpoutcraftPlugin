package org.getspout.spoutapi.gui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GenericEntityWidget extends GenericWidget implements EntityWidget {
	private int entityId = 0;
	
	public GenericEntityWidget() {
		
	}
	
	public GenericEntityWidget(int entityId) {
		this.entityId = entityId;
	}
	
	@Override
	public WidgetType getType() {
		return WidgetType.EntityWidget;
	}

	@Override
	public EntityWidget setEntityId(int id) {
		entityId = id;
		return this;
	}

	@Override
	public int getEntityId() {
		return entityId;
	}
	
	@Override
	public void readData(DataInputStream input) throws IOException {
		super.readData(input);
		entityId = input.readInt();
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(entityId);
	}
	
	@Override
	public EntityWidget copy() {
		return ((EntityWidget)super.copy()).setEntityId(getEntityId());
	}
}
