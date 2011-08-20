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
	protected WidgetAnchor align = WidgetAnchor.TOP_LEFT;

	public GenericContainer() {
	}

	public GenericContainer(Widget... children) {
		// Shortcuts because we don't have any of the insertChild values setup yet
		this.children.addAll(Arrays.asList(children));
		for (Widget child : children) {
			child.setContainer(this);
		}
	}

	@Override
	public Container addChild(Widget child) {
		return insertChild(-1, child);
	}

	@Override
	public Container insertChild(int index, Widget child) {
		if (index < 0 || index > this.children.size()) {
			this.children.add(child);
		} else {
			this.children.add(index, child);
		}
		child.setContainer(this);
		child.shiftXPos(super.getX());
		child.shiftYPos(super.getY());
		child.setAnchor(super.getAnchor());
		// Relayout if we are already using layout - otherwise this will return immediately
		updateLayout();
		if (this.screen != null) {
			this.screen.attachWidget(this.plugin, child);
		}
		return this;
	}

	@Override
	public Container addChildren(Widget... children) {
		for (Widget child : children) {
			this.insertChild(-1, child);
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
	public boolean isDirty() {
		return false;
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
	public Container setAnchor(WidgetAnchor anchor) {
		super.setAnchor(anchor);
		for (Widget widget : children) {
			widget.setAnchor(anchor);
		}
		return this;
	}

	@Override
	public WidgetType getType() {
		return WidgetType.Container;
	}

	@Override
	public Widget setX(int pos) {
		int delta = pos - super.getX();
		super.setX(pos);
		for (Widget widget : children) {
			widget.shiftXPos(delta);
		}
		return this;
	}

	@Override
	public Widget setY(int pos) {
		int delta = pos - super.getY();
		super.setY(pos);
		for (Widget widget : children) {
			widget.shiftYPos(delta);
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
		child.shiftXPos(-super.getX()); // Put it back where it was outside the container
		child.shiftYPos(-super.getY()); // Put it back where it was outside the container
		if (this.screen != null) {
			this.screen.removeWidget(child);
		}
		updateLayout();
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
		updateLayout();
		return this;
	}

	@Override
	public ContainerType getLayout() {
		return type;
	}

	@Override
	public Container setAlign(WidgetAnchor align) {
		this.align = align;
		updateLayout();
		return this;
	}

	@Override
	public WidgetAnchor getAlign() {
		return align;
	}

	@Override
	public Container updateLayout() {
		if (getWidth() > 0 && getHeight() > 0 && !children.isEmpty()) {
			int totalwidth = 0, totalheight = 0, vcount = 0, hcount = 0;
			for (Widget widget : children) {
				if (widget.isVisible()) {
					if (type == ContainerType.VERTICAL) {
						if (widget.isFixed()) {
							totalwidth = Math.max(totalwidth, widget.getWidth() + widget.getMarginLeft() + widget.getMarginRight());
							totalheight += widget.getHeight() + widget.getMarginTop() + widget.getMarginBottom();
						} else {
							totalwidth = Math.max(totalwidth, widget.getMarginLeft() + widget.getMarginRight());
							totalheight += widget.getMarginTop() + widget.getMarginBottom();
							hcount = 1;
							vcount++;
						}
					} else if (type == ContainerType.HORIZONTAL) {
						if (widget.isFixed()) {
							totalwidth += widget.getWidth() + widget.getMarginLeft() + widget.getMarginRight();
							totalheight = Math.max(totalheight, widget.getHeight() + widget.getMarginTop() + widget.getMarginBottom());
						} else {
							totalwidth += widget.getMarginLeft() + widget.getMarginRight();
							totalheight = Math.max(totalheight, widget.getMarginTop() + widget.getMarginBottom());
							vcount = 1;
							hcount++;
						}
					}
				}
			}
			int newwidth = (getWidth() - totalwidth) / Math.max(1, hcount);
			int newheight = (getHeight() - totalheight) / Math.max(1, vcount);
			for (Widget widget : children) {
				if (widget.isVisible() && !widget.isFixed()) {
					if (widget.getHeight() - widget.getMarginTop() - widget.getMarginBottom() != newheight) {
						widget.setHeight(newheight - widget.getMarginTop() - widget.getMarginBottom());
						widget.setDirty(true);
					}
					if (widget.getWidth() - widget.getMarginLeft() - widget.getMarginRight() != newwidth) {
						widget.setWidth(newwidth - widget.getMarginLeft() - widget.getMarginRight());
						widget.setDirty(true);
					}
					if (type == ContainerType.VERTICAL) {
						totalheight += newheight;
					} else if (type == ContainerType.HORIZONTAL) {
						totalwidth += newwidth;
					}
				}
			}
			int left = super.getX();
			int top = super.getY();

			if (align == WidgetAnchor.TOP_CENTER || align == WidgetAnchor.CENTER_CENTER || align == WidgetAnchor.BOTTOM_CENTER) {
				left += (super.getWidth() - totalwidth) / 2;
			} else if (align == WidgetAnchor.TOP_RIGHT || align == WidgetAnchor.CENTER_RIGHT || align == WidgetAnchor.BOTTOM_RIGHT) {
				left += super.getWidth() - totalwidth;
			}
			if (align == WidgetAnchor.CENTER_LEFT || align == WidgetAnchor.CENTER_CENTER || align == WidgetAnchor.CENTER_RIGHT) {
				top += (super.getHeight() - totalheight) / 2;
			} else if (align == WidgetAnchor.BOTTOM_LEFT || align == WidgetAnchor.BOTTOM_CENTER || align == WidgetAnchor.BOTTOM_RIGHT) {
				top += super.getHeight() - totalheight;
			}

			for (Widget widget : children) {
				if (widget.isVisible()) {
					if (widget.getY() + widget.getMarginTop() != top) {
						widget.setY(top + widget.getMarginTop());
						widget.setDirty(true);
					}
					if (widget.getX() + widget.getMarginLeft() != left) {
						widget.setX(left + widget.getMarginLeft());
						widget.setDirty(true);
					}
					if (type == ContainerType.VERTICAL) {
						top += widget.getHeight() + widget.getMarginTop() + widget.getMarginBottom();
					} else if (type == ContainerType.HORIZONTAL) {
						left += widget.getWidth() + widget.getMarginLeft() + widget.getMarginRight();
					}
				}
			}
		}
		return this;
	}
}