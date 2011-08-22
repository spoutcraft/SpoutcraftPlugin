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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	protected boolean reverse = false;

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
		child.savePos();
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
		child.restorePos();
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
	public Container setReverse(boolean reverse) {
		this.reverse = reverse;
		return this;
	}

	@Override
	public boolean getReverse() {
		return reverse;
	}

	@Override
	public Container updateLayout() {
		if (getWidth() > 0 && getHeight() > 0 && !children.isEmpty()) {
			List<Widget> visibleChildren = new ArrayList<Widget>();
			int totalwidth = 0, totalheight = 0, vcount = 0, hcount = 0;
			// We only layout visible children, invisible ones have zero physical presence on screen
			for (Widget widget : children) {
				if (widget.isVisible()) {
					visibleChildren.add(widget);
				}
			}
			// Reverse drawing order if we need to
			if (reverse) {
				Collections.reverse(visibleChildren);
			}
			// First - get the total space by fixed widgets and borders
			for (Widget widget : visibleChildren) {
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
						totalheight = Math.max(totalheight, widget.getMinHeight() + widget.getMarginTop() + widget.getMarginBottom());
						vcount = 1;
						hcount++;
					}
				}
			}
			// Work out the width and height for children
			int newwidth = (getWidth() - totalwidth) / Math.max(1, hcount);
			int newheight = (getHeight() - totalheight) / Math.max(1, vcount);
			// Deal with minWidth and minHeight - change newwidth/newheight if needed
			for (Widget widget : visibleChildren) {
				if (!widget.isFixed()) {
					if (type == ContainerType.VERTICAL) {
						if (widget.getMinHeight() > newheight) {
							totalheight += widget.getMinHeight() - newheight;
							newheight = (getHeight() - totalheight) / Math.max(1, vcount);
						} else if (newheight > widget.getMaxHeight()) {
							totalheight += widget.getMaxHeight();
							vcount--;
							newheight = (getHeight() - totalheight) / Math.max(1, vcount);
						}
					} else if (type == ContainerType.HORIZONTAL) {
						if (widget.getMinWidth() > newwidth) {
							totalwidth += widget.getMinWidth() - newwidth;
							newwidth = (getWidth() - totalwidth) / Math.max(1, hcount);
						} else if (newwidth > widget.getMaxWidth()) {
							totalwidth += widget.getMaxWidth();
							hcount--;
							newwidth = (getWidth() - totalwidth) / Math.max(1, hcount);
						}
					}
				}
			}
			newheight = Math.max(newheight, 0);
			newwidth = Math.max(newwidth, 0);
			// Resize any non-fixed widgets
			for (Widget widget : visibleChildren) {
				if (!widget.isFixed()) {
					if (widget.getHeight() - widget.getMarginTop() - widget.getMarginBottom() != Math.max(widget.getMinHeight(), Math.min(widget.getMaxHeight(), newheight))) {
						widget.setHeight(Math.max(widget.getMinHeight(), Math.min(widget.getMaxHeight(), newheight)) - widget.getMarginTop() - widget.getMarginBottom());
						widget.setDirty(true);
					}
					if (widget.getWidth() - widget.getMarginLeft() - widget.getMarginRight() != Math.max(widget.getMinWidth(), Math.min(widget.getMaxWidth(), newwidth))) {
						widget.setWidth(Math.max(widget.getMinWidth(), Math.min(widget.getMaxWidth(), newwidth)) - widget.getMarginLeft() - widget.getMarginRight());
						widget.setDirty(true);
					}
					if (type == ContainerType.VERTICAL) {
						totalheight += newheight;
					} else if (type == ContainerType.HORIZONTAL) {
						totalwidth += newwidth;
					}
				}
			}
			// Work out the new top-left position taking into account Align
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
			// Move all children into the correct position
			for (Widget widget : visibleChildren) {
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
		return this;
	}
}