package org.getspout.spoutapi.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.getspout.spoutapi.gui.ContainerType;
import org.getspout.spoutapi.gui.GenericWidget;
import org.getspout.spoutapi.gui.RenderPriority;
import org.getspout.spoutapi.gui.Widget;
import org.getspout.spoutapi.gui.WidgetType;

public class GenericContainer extends GenericWidget implements Container {

	protected List<Widget> children = new ArrayList<Widget>();
	protected ContainerType type = ContainerType.VERTICAL;
	protected boolean fixed = false;
	
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
	public Container setHeight(int height) {
		super.setHeight(height);
		this.updateLayout();
		return this;
	}

	@Override
	public Container setWidth(int width) {
		super.setWidth(width);
		this.updateLayout();
		return this;
	}

	@Override
	public Container setLayout(ContainerType type) {
		this.type = type;
		return this;
	}

	@Override
	public ContainerType getLayout() {
		return type;
	}

	@Override
	public Container setFixed(boolean fixed) {
		this.fixed = fixed;
		return this;
	}

	@Override
	public boolean getFixed() {
		return fixed;
	}

	@Override
	public Container updateLayout() {
		int totalwidth = this.getWidth(), totalheight = this.getHeight(), vert = 1, horiz = 1, left = 0, top = 0;
		if (type == ContainerType.VERTICAL) {
			vert = children.size();
			for (Widget widget : children) {
				if (widget instanceof GenericContainer && ((GenericContainer) widget).fixed) {
					totalheight -= widget.getHeight();
				}
			}
		}
		if (type == ContainerType.HORIZONTAL) {
			horiz = children.size();
			for (Widget widget : children) {
				if (widget instanceof GenericContainer && ((GenericContainer) widget).fixed) {
					totalwidth -= widget.getWidth();
				}
			}
		}
		for (Widget widget : children) {
			if (!(widget instanceof GenericContainer && ((GenericContainer) widget).fixed)) {
				widget.setHeight(totalheight / vert).setY(top);
				widget.setWidth(totalwidth / horiz).setX(left);
				switch(type) {
					case VERTICAL:
						top += totalheight / vert;
						break;
					case HORIZONTAL:
						left += totalwidth / horiz;
						break;
				}
			}
		}
		return this;
	}
}