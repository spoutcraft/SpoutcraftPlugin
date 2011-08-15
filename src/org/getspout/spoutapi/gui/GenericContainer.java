package org.getspout.spoutapi.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.getspout.spoutapi.gui.GenericWidget;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.gui.WidgetType;

public class GenericContainer extends GenericWidget implements Container {

	protected List<Widget> children = new ArrayList<Widget>();
	
	public GenericContainer() {
		
	}
	
	public int getOffsetX() {
		int off = 0;
		if (((GenericContainer) getContainer()) != null) {
			off = ((GenericContainer) getContainer()).getOffsetX();
		}
		return off + getX();
	}
	
	public int getOffsetY() {
		int off = 0;
		if (((GenericContainer) getContainer()) != null) {
			off = ((GenericContainer) getContainer()).getOffsetY();
		}
		return off + getY();
	}

	@Override
	public Container addChild(Widget child) {
		this.children.add(child);
		child.setContainer(this);
		return this;
	}

	@Override
	public Container addChildren(Widget... children) {
		this.children.addAll(Arrays.asList(children));
		for (Widget child : children) {
			child.setContainer(this);
		}
		return this;
	}

	@Override
	public Widget[] getChildren() {
		Widget[] list = new Widget[children.size()];
		children.toArray(list);
		return list;
	}

	@Override
	public void setDirty(boolean dirty) {
		super.setDirty(dirty);
		for (Widget widget : children) {
			widget.setDirty(dirty);
		}
	}

	@Override
	public Container setVisible(boolean enable) {
		super.setVisible(enable);
		for (Widget widget : children) {
			widget.setVisible(enable);
		}
		return this;
	}

	@Override
	public Container setPriority(RenderPriority priority) {
		super.setPriority(priority);
		for (Widget widget : children) {
			widget.setPriority(priority);
		}
		return this;
	}

	@Override
	public WidgetType getType() {
		return WidgetType.Container;
	}
	
	@Override
	public Widget setX(int pos) {
		super.setX(pos);
		for (Widget widget : children) {
			widget.updateOffset();
		}
		return this;
	}
	
	@Override
	public Widget setY(int pos) {
		super.setY(pos);
		for (Widget widget : children) {
			widget.updateOffset();
		}
		return this;
	}

	@Override
	public void render() {
	}

	@Override
	public Container removeChild(Widget child) {
		children.remove(child);
		return this;
	}
}