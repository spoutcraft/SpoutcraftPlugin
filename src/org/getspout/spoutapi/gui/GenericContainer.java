package org.getspout.spoutapi.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.getspout.spoutapi.gui.GenericWidget;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.gui.WidgetType;

public class GenericContainer extends GenericWidget implements Container {

	protected List<Widget> children = new ArrayList<Widget>();
	
	public GenericContainer() {
		
	}
	
	@Override
	public int getOffsetX() {
		int off = 0;
		if (((GenericContainer) getContainer()) != null) {
			off = ((GenericContainer) getContainer()).getOffsetX();
		}
		return off + getX();
	}
	
	@Override
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
		if (this.screen != null) {
			this.screen.attachWidget(this.plugin, child);
		}
		return this;
	}

	@Override
	public Container addChildren(Widget... children) {
		this.children.addAll(Arrays.asList(children));
		for (Widget child : children) {
			child.setContainer(this);
			this.screen.attachWidget(this.plugin, child);
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
		child.setContainer(null);
		if (this.screen != null) {
			this.screen.removeWidget(child);
		}
		return this;
	}

	@Override
	public Container setScreen(Screen screen) {
		for (Widget child : children) {
			if (screen != null) {
				screen.attachWidget(this.plugin, child);
			} else if (this.screen != null) {
				this.screen.removeWidget(child);
			}
		}
		super.setScreen(screen);
		return this;
	}
	
	@Override
	public Widget findUserId(String userID) {
		for (Widget child : children) {
			if (child.getUserId().equals(userID)) {
				return child;
			}
			if (child instanceof Container) {
				if ((child = ((Container)child).findUserId(userID)) != null) {
					return child;
				}
			}
		}
		return null;
	}

	@Override
	public Widget findId(UUID id) {
		for (Widget child : children) {
			if (child.getId().equals(id)) {
				return child;
			}
			if (child instanceof Container) {
				if ((child = ((Container)child).findId(id)) != null) {
					return child;
				}
			}
		}
		return null;
	}
}