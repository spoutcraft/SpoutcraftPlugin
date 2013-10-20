/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.gui;

import java.io.IOException;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * This defines a control for a scrollbar.
 */
public abstract class Scrollable extends Control {
	protected ScrollBarPolicy sbpVert;
	protected ScrollBarPolicy sbpHoriz = sbpVert = ScrollBarPolicy.SHOW_IF_NEEDED;
	protected int innerSizeHoriz = 0, innerSizeVert = 0;
	protected int scrollX = 0, scrollY = 0;
	private Color background = new Color(0F, 0F, 0F, 0.6F);

	public Scrollable() {
	}

	/**
	 * Gets the inner size for given axis, in pixels.
	 * @param axis of the size vector
	 * @return the size of the viewport on given axis
	 */
	public int getInnerSize(Orientation axis) {
		switch (axis) {
			case HORIZONTAL:
				return innerSizeHoriz;
			case VERTICAL:
				return innerSizeVert;
		}
		return 0;
	}

	/**
	 * Gets the scroll position, in pixels.
	 * @param axis of the scroll vector
	 * @return the position of the viewport on given axis
	 */
	public int getScrollPosition(Orientation axis) {
		switch (axis) {
			case HORIZONTAL:
				return scrollX;
			case VERTICAL:
				return scrollY;
		}
		return 0;
	}

	/**
	 * Sets the scroll position on the axis, in pixels.
	 * The allowed scroll amount ranges from 0 to getMaximumScrollPosition for given axis.
	 * @param axis     the axis to scroll
	 * @param position the position of the viewport on given axis
	 */
	public void setScrollPosition(Orientation axis, int position) {
		if (position < 0) {
			position = 0;
		}
		if (position > getMaximumScrollPosition(axis)) {
			position = getMaximumScrollPosition(axis);
		}
		switch (axis) {
			case HORIZONTAL:
				scrollX = position;
				break;
			case VERTICAL:
				scrollY = position;
				break;
		}
	}

	/**
	 * Adds x and y to the Horizontal and Vertical scroll position.
	 * @param x
	 * @param y
	 */
	public void scroll(int x, int y) {
		int currentX, currentY;
		currentX = getScrollPosition(Orientation.HORIZONTAL);
		currentX += x;
		currentY = getScrollPosition(Orientation.VERTICAL);
		currentY += y;
		setScrollPosition(Orientation.HORIZONTAL, currentX);
		setScrollPosition(Orientation.VERTICAL, currentY);
	}

	/**
	 * Adjusts the scroll position so that the given rectangle will fit into the viewport.
	 * If the given rect is too big, it will scroll to the x or y position of the rectangle.
	 * @param rect
	 */
	public void ensureVisible(Rectangle rect) {
		int scrollTop = getScrollPosition(Orientation.VERTICAL);
		int scrollLeft = getScrollPosition(Orientation.HORIZONTAL);
		int scrollBottom = scrollTop + getViewportSize(Orientation.VERTICAL) - 10;
		int scrollRight = scrollLeft + getViewportSize(Orientation.HORIZONTAL) - 10;
		boolean correctHorizontal = false;
		boolean correctVertical = false;
		if (scrollTop <= rect.getTop() && rect.getBottom() <= scrollBottom) {
			// Fits vertical
			if (scrollLeft <= rect.getLeft() && rect.getRight() <= scrollRight) {
				// Fits completely
				// Nothing to do.
				return;
			} else {
				// Doesn't fit horizontal
				correctHorizontal = true;
			}
		} else {
			// Doesn't fit vertical
			correctVertical = true;
			if (scrollLeft <= rect.getLeft() && rect.getRight() <= scrollRight) {
				// But does fit horizontal
			} else {
				// Doesn't fit horizontal too
				correctHorizontal = true;
			}
		}
		if (correctHorizontal) {
			if (scrollRight < rect.getRight()) {
				setScrollPosition(Orientation.HORIZONTAL, rect.getRight() - getViewportSize(Orientation.HORIZONTAL) + 10);
			}
			if (scrollLeft > rect.getLeft()) {
				setScrollPosition(Orientation.HORIZONTAL, rect.getLeft());
			}
		}
		if (correctVertical) {
			if (scrollBottom < rect.getBottom()) {
				setScrollPosition(Orientation.VERTICAL, rect.getBottom() - getViewportSize(Orientation.VERTICAL) + 10);
			}
			if (scrollTop > rect.getTop()) {
				setScrollPosition(Orientation.VERTICAL, rect.getTop());
			}
		}
	}

	/**
	 * Gets the maximum allowed scroll position.
	 * @param axis
	 * @return the maximum scroll position. Use the return value to scroll to the very bottom.
	 * @returns 0 if no scrolling is possible, that means the whole content would fit into the viewport.
	 */
	public int getMaximumScrollPosition(Orientation axis) {
		return (int) Math.max(0, getInnerSize(axis) - (axis == Orientation.HORIZONTAL ? getWidth() : getHeight()));
	}

	/**
	 * Depending on the set ScrollBarPolicy, returns wether to show or not to show a scrollbar on the given axis
	 * @param axis to check if it should view a scrollbar
	 * @return if the scrollbar should be shown for that axis
	 */
	public boolean needsScrollBar(Orientation axis) {
		ScrollBarPolicy policy = getScrollBarPolicy(axis);
		switch (policy) {
			case SHOW_IF_NEEDED:
				return getMaximumScrollPosition(axis) > 0;
			case SHOW_ALWAYS:
				return true;
			case SHOW_NEVER:
				return false;
			default:
				return true;
		}
	}

	/**
	 * Sets the scrollbar policy for the given axis
	 * @param axis
	 * @param policy
	 */
	public void setScrollBarPolicy(Orientation axis, ScrollBarPolicy policy) {
		switch (axis) {
			case HORIZONTAL:
				sbpHoriz = policy;
				break;
			case VERTICAL:
				sbpVert = policy;
				break;
		}
	}

	/**
	 * Gets the scrollbar policy for the given axis
	 * @param axis
	 * @return the scrollbar policy for the given axis
	 */
	public ScrollBarPolicy getScrollBarPolicy(Orientation axis) {
		switch (axis) {
			case HORIZONTAL:
				return sbpHoriz;
			case VERTICAL:
				return sbpVert;
		}
		return null;
	}

	/**
	 * Set the size of the internal area
	 * @param axis
	 * @param size
	 */
	protected void setInnerSize(Orientation axis, int size) {
		switch (axis) {
			case HORIZONTAL:
				innerSizeHoriz = size;
				break;
			case VERTICAL:
				innerSizeVert = size;
				break;
		}
	}

	/**
	 * Gets the size of the rectangle inside the scrollable. This is usually getWidth/Height() - 16 when the corresponding scrollbar is visible.
	 * @param axis
	 * @return
	 */
	public int getViewportSize(Orientation axis) {
		int size = (int) (axis == Orientation.HORIZONTAL ? getWidth() : getHeight());
		if (needsScrollBar(axis.getOther())) {
			return size - 16;
		}
		return size;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		sbpHoriz = ScrollBarPolicy.getById(input.readInt());
		sbpVert = ScrollBarPolicy.getById(input.readInt());
		scrollX = input.readInt();
		scrollY = input.readInt();
		innerSizeHoriz = input.readInt();
		innerSizeVert = input.readInt();
		setBackgroundColor(input.readColor());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(sbpHoriz.getId());
		output.writeInt(sbpVert.getId());
		output.writeInt(scrollX);
		output.writeInt(scrollY);
		output.writeInt(innerSizeHoriz);
		output.writeInt(innerSizeVert);
		output.writeColor(getBackgroundColor());
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 1;
	}

	/**
	 * Gets the background color of this list
	 * @return color
	 */
	public Color getBackgroundColor() {
		return background;
	}

	/**
	 * Sets the background color of this list
	 * @param color to set
	 * @return this
	 */
	public Scrollable setBackgroundColor(Color color) {
		background = color;
		return this;
	}
}
