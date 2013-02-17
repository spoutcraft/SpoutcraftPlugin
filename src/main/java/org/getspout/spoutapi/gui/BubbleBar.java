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
import java.util.UUID;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * The Spout implementation of the default Bubble Bar.
 */
public class BubbleBar extends GenericWidget implements Widget {
	private int icons = 10;
	private int iconOffset = 8;

	public BubbleBar() {
		super();
		setDirty(false);
		setX(427 / 2 + 82); // 295
		setY(191);
		setWidth(getWidth()); // Don't know the default - ignored, but prevents warnings...
		setAnchor(WidgetAnchor.BOTTOM_CENTER);
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		setMaxNumBubbles(input.readInt());
		setIconOffset(input.readInt());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.writeInt(getMaxNumBubbles());
		output.writeInt(getIconOffset());
	}

	@Override
	public WidgetType getType() {
		return WidgetType.BubbleBar;
	}

	@Override
	public UUID getId() {
		return new UUID(0, 1);
	}

	/**
	 * Gets the maximum number of bubbles displayed on the HUD.
	 * <p/>
	 * Air is scaled to fit the number of bubbles appropriately.
	 * @return bubbles displayed
	 */
	public int getMaxNumBubbles() {
		return icons;
	}

	/**
	 * Sets the maximum number of bubbles displayed on the HUD.
	 * <p/>
	 * Air is scaled to fit the number of bubbles appropriately.
	 * @param bubbles to display
	 * @return this
	 */
	public BubbleBar setMaxNumBubbles(int bubbles) {
		this.icons = bubbles;
		return this;
	}

	/**
	 * Gets the number of pixels each bubbles is offset when drawing the next bubble.
	 * @return pixel offset
	 */
	public int getIconOffset() {
		return iconOffset;
	}

	/**
	 * Sets the number of pixels each bubbles is offset when drawing the next bubble.
	 * @param offset when drawing hearts
	 * @return this
	 */
	public BubbleBar setIconOffset(int offset) {
		iconOffset = offset;
		return this;
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 1;
	}
}
