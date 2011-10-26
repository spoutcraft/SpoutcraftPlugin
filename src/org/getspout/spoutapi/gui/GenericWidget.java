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

public abstract class GenericWidget extends AbstractEventSource implements Widget{
	protected int X = 0;
	protected int Y = 0;
	protected int width = 0;
	protected int height = 0;
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
		this.anchor = anchor;
		return this;
	}

	@Override
	public WidgetAnchor getAnchor() {
		return anchor;
	}
	
	@Override
	public Plugin getPlugin() {
		return (plugin == null || plugin.equals("Spoutcraft") ? Bukkit.getServer().getPluginManager().getPlugin("Spout") : Bukkit.getServer().getPluginManager().getPlugin(plugin));
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
		if (this.screen != null && screen != null && screen != this.screen) {
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
		this.priority = priority;
		return this;
	}
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public Widget setWidth(int width) {
		width = Math.max(getMinWidth(), Math.min(width, getMaxWidth()));
		if (this.width != width) {
			this.width = width;
			updateSize();
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
		if (this.height != height) {
			this.height = height;
			updateSize();
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
		this.X = pos;
		return this;
	}

	@Override
	public Widget setY(int pos) {
		this.Y = pos;
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
		if (visible != enable) {
			visible = enable;
			updateSize();
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
	public Widget setTooltip(String t){
		tooltip = t;
		return this;
	}
	
	@Override
	public String getTooltip(){
		return tooltip;
	}
	
	@Override
	public Container getContainer() {
		return container;
	}
	
	@Override
	public void setContainer(Container container) {
		if (this.container != null && container != null && container != this.container) {
			this.container.removeChild(this);
		}
		this.container = container;
	}

	@Override
	public Widget setFixed(boolean fixed) {
		if (this.fixed != fixed) {
			this.fixed = fixed;
			updateSize();
		}
		return this;
	}

	@Override
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
		if (this.marginTop != marginTop || this.marginRight != marginRight || this.marginBottom != marginBottom || this.marginLeft != marginLeft) {
			this.marginTop = marginTop;
			this.marginRight = marginRight;
			this.marginBottom = marginBottom;
			this.marginLeft = marginLeft;
			updateSize();
		}
		return this;
	}

	@Override
	public Widget setMarginTop(int marginTop) {
		if (this.marginTop != marginTop) {
			this.marginTop = marginTop;
			updateSize();
		}
		return this;
	}

	@Override
	public Widget setMarginRight(int marginRight) {
		if (this.marginRight != marginRight) {
			this.marginRight = marginRight;
			updateSize();
		}
		return this;
	}

	@Override
	public Widget setMarginBottom(int marginBottom) {
		if (this.marginBottom != marginBottom) {
			this.marginBottom = marginBottom;
			updateSize();
		}
		return this;
	}

	@Override
	public Widget setMarginLeft(int marginLeft) {
		if (this.marginLeft != marginLeft) {
			this.marginLeft = marginLeft;
			updateSize();
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
		if (minWidth != min) {
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
		if (maxWidth != max) {
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
		if (minHeight != min) {
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
		if (maxHeight != max) {
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
		orig_x = X;
		orig_y = Y;
		return this;
	}

	@Override
	public Widget restorePos() {
		X = orig_x;
		Y = orig_y;
		return this;
	}
	
	@Override
	public Widget copy() {
		try {
			Widget copy = getType().getWidgetClass().newInstance();
			copy.setX(getX()).setY(getY()).setWidth(getWidth()).setHeight(getHeight()).setVisible(isVisible());
			copy.setPriority(getPriority()).setTooltip(getTooltip()).setAnchor(getAnchor());
			copy.setMargin(getMarginTop(), getMarginRight(), getMarginBottom(), getMarginLeft());
			copy.setMinWidth(getMinWidth()).setMaxWidth(getMaxWidth()).setMinHeight(getMinHeight()).setMaxHeight(getMaxHeight());
			copy.setFixed(isFixed());
			return copy;
		}
		catch (Exception e) {
			throw new IllegalStateException("Unable to create a copy of " + getClass() + ". Does it have a valid widget type?");
		}
	}

	@Override
	public Widget updateSize() {
		if (container != null) {
			container.updateSize();
		}
		return this;
	}
}
