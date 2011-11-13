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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.event.AbstractEventSource;
import org.getspout.spoutapi.packet.PacketUtil;

public abstract class GenericWidget extends AbstractEventSource implements Widget {

	protected int X = 100;
	protected int Y = 100;
	protected int width = 50;
	protected int height = 50;
	protected boolean visible = true;
	protected transient boolean dirty = true;
	protected transient Screen screen = null;
	protected RenderPriority priority = RenderPriority.Normal;
	protected UUID id = UUID.randomUUID();
	protected String tooltip = "";
	protected String plugin = "Spoutcraft";
	protected WidgetAnchor anchor = WidgetAnchor.SCALE;
	// Server side layout
	protected Container container = null;
	protected boolean fixed = false;
	protected int marginTop = 0, marginRight = 0, marginBottom = 0, marginLeft = 0;
	protected int minWidth = 0, maxWidth = 427, minHeight = 0, maxHeight = 240;
	protected int orig_x = 0, orig_y = 0;
	protected boolean autoDirty = true;

	public GenericWidget() {
	}

	@Override
	public int getNumBytes() {
		return 38 + PacketUtil.getNumBytes(tooltip) + PacketUtil.getNumBytes(plugin != null ? plugin : "Spoutcraft");
	}

	@Override
	public int getVersion() {
		return 3;
	}

	public GenericWidget(int X, int Y, int width, int height) {
		this.X = X;
		this.Y = Y;
		this.width = width;
		this.height = height;
	}

	@Override
	public Widget setAnchor(WidgetAnchor anchor) {
		if (anchor != null && getAnchor().equals(anchor)) {
			this.anchor = anchor;
			autoDirty();
		}
		return this;
	}

	@Override
	public WidgetAnchor getAnchor() {
		return anchor;
	}

	@Override
	public Plugin getPlugin() {
		return Bukkit.getServer().getPluginManager().getPlugin(plugin == null || plugin.equals("Spoutcraft") ? "Spout" : plugin);
	}

	@Override
	public Widget setPlugin(Plugin plugin) {
		if (plugin != null) {
			this.plugin = plugin.getDescription().getName();
		}
		return this;
	}

	@Override
	public void readData(DataInputStream input) throws IOException {
		setX(input.readInt());
		setY(input.readInt());
		setWidth(input.readInt());
		setHeight(input.readInt());
		setAnchor(WidgetAnchor.getAnchorFromId(input.readByte()));
		setVisible(input.readBoolean());
		setPriority(RenderPriority.getRenderPriorityFromId(input.readInt()));
		long msb = input.readLong();
		long lsb = input.readLong();
		this.id = new UUID(msb, lsb);
		setTooltip(PacketUtil.readString(input));
		setPlugin(Bukkit.getServer().getPluginManager().getPlugin(PacketUtil.readString(input)));
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException {
		output.writeInt(getX());
		output.writeInt(getY());
		output.writeInt(getWidth());
		output.writeInt(getHeight());
		output.writeByte(getAnchor().getId());
		output.writeBoolean(isVisible());
		output.writeInt(priority.getId());
		output.writeLong(getId().getMostSignificantBits());
		output.writeLong(getId().getLeastSignificantBits());
		PacketUtil.writeString(output, getTooltip());
		PacketUtil.writeString(output, plugin != null ? plugin : "Spoutcraft");
	}

	@Override
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public Screen getScreen() {
		return screen;
	}

	@Override
	public Widget setScreen(Screen screen) {
		return setScreen(null, screen);
	}

	@Override
	public Widget setScreen(Plugin plugin, Screen screen) {
		if (getScreen() != null && screen != null && !getScreen().equals(screen)) {
			this.screen.removeWidget(this);
		}
		this.screen = screen;
		if (plugin != null) {
			this.plugin = plugin.getDescription().getName();
		}
		return this;
	}

	@Override
	public RenderPriority getPriority() {
		return priority;
	}

	@Override
	public Widget setPriority(RenderPriority priority) {
		if (priority != null && !getPriority().equals(priority)) {
			this.priority = priority;
			autoDirty();
		}
		return this;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public Widget setWidth(int width) {
		width = Math.max(getMinWidth(), Math.min(width, getMaxWidth()));
		if (getWidth() != width) {
			this.width = width;
			updateSize();
			autoDirty();
		}
		return this;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public Widget setHeight(int height) {
		height = Math.max(getMinHeight(), Math.min(height, getMaxHeight()));
		if (getHeight() != height) {
			this.height = height;
			updateSize();
			autoDirty();
		}
		return this;
	}

	@Override
	public int getX() {
		return X;
	}

	@Override
	public int getY() {
		return Y;
	}

	@Override
	public Widget setX(int pos) {
		if (getX() != pos) {
			this.X = pos;
			autoDirty();
		}
		return this;
	}

	@Override
	public Widget setY(int pos) {
		if (getY() != pos) {
			this.Y = pos;
			autoDirty();
		}
		return this;
	}

	@Override
	public Widget shiftXPos(int modX) {
		setX(getX() + modX);
		return this;
	}

	@Override
	public Widget shiftYPos(int modY) {
		setY(getY() + modY);
		return this;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	@Override
	public Widget setVisible(boolean enable) {
		if (isVisible() != enable) {
			visible = enable;
			updateSize();
			autoDirty();
		}
		return this;
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof Widget && other.hashCode() == hashCode();
	}

	@Override
	public void onTick() {
	}

	@Override
	public Widget setTooltip(String t) {
		if (t != null && !getTooltip().equals(t)) {
			tooltip = t;
			autoDirty();
		}
		return this;
	}

	@Override
	public String getTooltip() {
		return tooltip;
	}

	@Override
	public Container getContainer() {
		return container;
	}

	@Override
	public boolean hasContainer() {
		return container != null;
	}

	@Override
	public void setContainer(Container container) {
		if (hasContainer() && container != null && getContainer().equals(container)) {
			this.container.removeChild(this);
		}
		this.container = container;
	}

	@Override
	public Widget setFixed(boolean fixed) {
		if (isFixed() != fixed) {
			this.fixed = fixed;
			updateSize();
		}
		return this;
	}

	@Override
	@Deprecated
	public boolean getFixed() {
		return isFixed();
	}

	@Override
	public boolean isFixed() {
		return fixed;
	}

	@Override
	public Widget setMargin(int marginAll) {
		return setMargin(marginAll, marginAll, marginAll, marginAll);
	}

	@Override
	public Widget setMargin(int marginTopBottom, int marginLeftRight) {
		return setMargin(marginTopBottom, marginLeftRight, marginTopBottom, marginLeftRight);
	}

	@Override
	public Widget setMargin(int marginTop, int marginLeftRight, int marginBottom) {
		return setMargin(marginTop, marginLeftRight, marginBottom, marginLeftRight);
	}

	@Override
	public Widget setMargin(int marginTop, int marginRight, int marginBottom, int marginLeft) {
		if (getMarginTop() != marginTop || getMarginRight() != marginRight || getMarginBottom() != marginBottom || getMarginLeft() != marginLeft) {
			this.marginTop = marginTop;
			this.marginRight = marginRight;
			this.marginBottom = marginBottom;
			this.marginLeft = marginLeft;
			updateSize();
			autoDirty();
		}
		return this;
	}

	@Override
	public Widget setMarginTop(int marginTop) {
		if (getMarginTop() != marginTop) {
			this.marginTop = marginTop;
			updateSize();
			autoDirty();
		}
		return this;
	}

	@Override
	public Widget setMarginRight(int marginRight) {
		if (getMarginRight() != marginRight) {
			this.marginRight = marginRight;
			updateSize();
			autoDirty();
		}
		return this;
	}

	@Override
	public Widget setMarginBottom(int marginBottom) {
		if (getMarginBottom() != marginBottom) {
			this.marginBottom = marginBottom;
			updateSize();
			autoDirty();
		}
		return this;
	}

	@Override
	public Widget setMarginLeft(int marginLeft) {
		if (getMarginLeft() != marginLeft) {
			this.marginLeft = marginLeft;
			updateSize();
			autoDirty();
		}
		return this;
	}

	@Override
	public int getMarginTop() {
		return marginTop;
	}

	@Override
	public int getMarginRight() {
		return marginRight;
	}

	@Override
	public int getMarginBottom() {
		return marginBottom;
	}

	@Override
	public int getMarginLeft() {
		return marginLeft;
	}

	@Override
	public Widget setMinWidth(int min) {
		min = Math.max(min, 0);
		if (getMinWidth() != min) {
			minWidth = min;
			updateSize();
			setWidth(width); // Enforce our new size if needed
		}
		return this;
	}

	@Override
	public int getMinWidth() {
		return minWidth;
	}

	@Override
	public Widget setMaxWidth(int max) {
		max = max <= 0 ? 427 : max;
		if (getMaxWidth() != max) {
			maxWidth = max;
			updateSize();
			setWidth(width); // Enforce our new size if needed
		}
		return this;
	}

	@Override
	public int getMaxWidth() {
		return maxWidth;
	}

	@Override
	public Widget setMinHeight(int min) {
		min = Math.max(min, 0);
		if (getMinHeight() != min) {
			minHeight = min;
			updateSize();
			setHeight(height); // Enforce our new size if needed
		}
		return this;
	}

	@Override
	public int getMinHeight() {
		return minHeight;
	}

	@Override
	public Widget setMaxHeight(int max) {
		max = max <= 0 ? 240 : max;
		if (getMaxHeight() != max) {
			maxHeight = max;
			updateSize();
			setHeight(height); // Enforce our new size if needed
		}
		return this;
	}

	@Override
	public int getMaxHeight() {
		return maxHeight;
	}

	@Override
	public Widget savePos() {
		orig_x = getX();
		orig_y = getY();
		return this;
	}

	@Override
	public Widget restorePos() {
		setX(orig_x);
		setY(orig_y);
		return this;
	}

	@Override
	public Widget copy() {
		try {
			Widget copy = getType().getWidgetClass().newInstance();
			copy	.setX(getX())
					.setY(getY())
					.setWidth(getWidth())
					.setHeight(getHeight())
					.setVisible(isVisible())
					.setPriority(getPriority())
					.setTooltip(getTooltip())
					.setAnchor(getAnchor())
					.setMargin(getMarginTop(), getMarginRight(), getMarginBottom(), getMarginLeft())
					.setMinWidth(getMinWidth())
					.setMaxWidth(getMaxWidth())
					.setMinHeight(getMinHeight())
					.setMaxHeight(getMaxHeight())
					.setFixed(isFixed())
					.setAutoDirty(isAutoDirty());
			return copy;
		} catch (Exception e) {
			throw new IllegalStateException("Unable to create a copy of " + getClass() + ". Does it have a valid widget type?");
		}
	}

	@Override
	public Widget updateSize() {
		if (hasContainer()) {
			container.updateSize();
		}
		return this;
	}

	@Override
	public Widget setAutoDirty(boolean dirty) {
		return this;
	}

	@Override
	public boolean isAutoDirty() {
		return autoDirty;
	}

	@Override
	public void autoDirty() {
		if (isAutoDirty()) {
			setDirty(true);
		}
	}
}
