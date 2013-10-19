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
import javax.xml.bind.TypeConstraintException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;

/**
 * This is the base class of all other widgets, and should never be used
 * directly.
 * <p/>
 * If you subclass this for a custom type of widget then you must make sure
 * that isDirty() always returns false otherwise the widget will try to be sent
 * to the client and will cause an exception to be thrown.
 */
public abstract class Widget {
	/**
	 * Set if this is Spoutcraft (client), cleared if it is Spout (server)...
	 */
	static final protected transient boolean isSpoutcraft = false;
	protected int X = 0;
	protected int Y = 0;
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
	protected transient boolean hasPosition = false;
	protected transient boolean hasSize = false;
	// Animation
	protected WidgetAnim animType = WidgetAnim.NONE;
	protected float animValue = 1f;
	protected short animCount = 0;
	protected short animTicks = 20;
	protected final byte ANIM_REPEAT = (1 << 0);
	protected final byte ANIM_RESET = (1 << 1);
	protected final byte ANIM_RUNNING = (1 << 2);
	protected final byte ANIM_STOPPING = (1 << 3);
	protected byte animFlags = 0;
	protected transient int animTick = 0; // Current tick
	protected transient int animFrame = 0; // Current frame

	public Widget() {
	}

	public Widget(int X, int Y, int width, int height) {
		this.X = X;
		this.Y = Y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Is this running on Spoutcraft (ie, not on the server) - declared final in GenericWidget!
	 * @return if it's running on a client
	 */
	final public boolean isSpoutcraft() {
		return isSpoutcraft;
	}

	/**
	 * The version this widget is. Mismatched versions will fail to be created.
	 * @return version
	 */
	public int getVersion() {
		return 6;
	}

	/**
	 * The type of widget this is. Required for proper synchronization between the server and client.
	 * @return widget type
	 */
	abstract public WidgetType getType();

	public Widget setAnchor(WidgetAnchor anchor) {
		if (anchor != null && !getAnchor().equals(anchor)) {
			this.anchor = anchor;
			autoDirty();
		}
		return this;
	}

	/**
	 * Get the current anchor position
	 * @return
	 */
	public WidgetAnchor getAnchor() {
		return anchor;
	}

	/**
	 * Gets the plugin that attached this widget to the screen, or null if this screen is unattached.
	 * @return plugin that attached this widget to the screen
	 */
	public Plugin getPlugin() {
		return Bukkit.getServer().getPluginManager().getPlugin(plugin == null || plugin.equals("Spoutcraft") ? "Spout" : plugin);
	}

	/**
	 * Internal use only.
	 * @param plugin
	 * @return this
	 */
	public Widget setPlugin(Plugin plugin) {
		if (plugin != null) {
			this.plugin = plugin.getDescription().getName();
		}
		return this;
	}

	/**
	 * Called after this widget this created for serialization.
	 * @param input
	 * @throws IOException
	 */
	public void readData(SpoutInputStream input) throws IOException {
		setX(input.readInt());
		setY(input.readInt());
		setWidth(input.readInt());
		setHeight(input.readInt());
		setAnchor(WidgetAnchor.getAnchorFromId(input.read()));
		setVisible(input.readBoolean());
		setPriority(RenderPriority.getRenderPriorityFromId(input.readInt()));
		setTooltip(input.readString());
		setPlugin(Bukkit.getServer().getPluginManager().getPlugin(input.readString()));
		animType = WidgetAnim.getAnimationFromId(input.read());
		animFlags = (byte) input.read();
		animValue = input.readFloat();
		animTicks = input.readShort();
		animCount = input.readShort();
	}

	/**
	 * Called when this widget is serialized to the client.
	 * <p/>
	 * Note: ensure that any changes here are reflected in {@link getNumBytes()} and are also present on the client.
	 * @param output
	 * @throws IOException
	 */
	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeInt(getX());
		output.writeInt(getY());
		output.writeInt(getWidth());
		output.writeInt(getHeight());
		output.write(getAnchor().getId());
		output.writeBoolean(isVisible());
		output.writeInt(priority.getId());
		output.writeString(getTooltip());
		output.writeString(plugin != null ? plugin : "Spoutcraft");
		output.write(animType.getId());
		output.write(animFlags);
		output.writeFloat(animValue);
		output.writeShort(animTicks);
		output.writeShort(animCount);
	}

	/**
	 * Marks this widget as needing an update on the client. It will be updated after the next onTick call, and marked as setDirty(false)
	 * Every widget is dirty immediately after creation
	 * @param dirty
	 */
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	/**
	 * Is true if this widget has been marked dirty
	 * @return dirty
	 */
	public boolean isDirty() {
		return dirty;
	}

	/**
	 * Returns a unique id for this widget
	 * @return id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Gets the screen this widget is attached to, or null if unattached
	 * @return screen
	 */
	public Screen getScreen() {
		return screen;
	}

	/**
	 * Sets the screen and plugin this widget is attached to. Should not be used normally, is handled with screen.attachWidget() is called.
	 * @param screen this is attached to
	 * @param plugin this is attached to
	 * @return widget
	 */
	public Widget setScreen(Plugin plugin, Screen screen) {
		if (getScreen() != null && screen != null && !getScreen().equals(screen)) {
			getScreen().removeWidget(this);
		}
		this.screen = screen;
		if (plugin != null) {
			this.plugin = plugin.getDescription().getName();
		}
		return this;
	}

	/**
	 * Gets the render priority for this widget. Highest priorities render first (in the background), the lowest priorities render on top (in the foreground).
	 * @return priority.
	 */
	public RenderPriority getPriority() {
		return priority;
	}

	/**
	 * Sets the render priority for this widget. Highest priorities render first (in the background), the lowest priorities render on top (in the foreground).
	 * @param priority to render at
	 * @return widget
	 */
	public Widget setPriority(RenderPriority priority) {
		if (priority != null && !getPriority().equals(priority)) {
			this.priority = priority;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the width of this widget, in pixels
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of this widget, in pixels
	 * @param width to set
	 * @return widget
	 */
	public Widget setWidth(int width) {
		hasSize = true;
		width = Math.max(getMinWidth(), Math.min(width, getMaxWidth()));
		if (getWidth() != width) {
			this.width = width;
			updateSize();
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the height of this widget, in pixels
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of this widget, in pixels
	 * @param height to set
	 * @return widget
	 */
	public Widget setHeight(int height) {
		hasSize = true;
		height = Math.max(getMinHeight(), Math.min(height, getMaxHeight()));
		if (getHeight() != height) {
			this.height = height;
			updateSize();
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the x coordinate of this widget. Widgets (and screens) render from the top left cornor the screen. 0,0 represents the top left corner.
	 * @return x-coordinate
	 */
	public int getX() {
		return X;
	}

	/**
	 * Gets the y coordinate of this widget. Widgets (and screens) render from the top left cornor the screen. 0,0 represents the top left corner.
	 * @return y-coordinate
	 */
	public int getY() {
		return Y;
	}

	/**
	 * Sets the x coordinate of this widget. Widgets (and screens) render from the top left cornor the screen. 0,0 represents the top left corner.
	 * @param pos to set
	 * @return widget
	 */
	public Widget setX(int pos) {
		hasPosition = true;
		if (getX() != pos) {
			X = pos;
			autoDirty();
		}
		return this;
	}

	/**
	 * Sets the y coordinate of this widget. Widgets (and screens) render from the top left cornor the screen. 0,0 represents the top left corner.
	 * @param pos to set
	 * @return widget
	 */
	public Widget setY(int pos) {
		hasPosition = true;
		if (getY() != pos) {
			Y = pos;
			autoDirty();
		}
		return this;
	}

	/**
	 * Shifts this widget the given number of pixels in the x direction.
	 * @param x pixels to shift
	 * @return widget
	 */
	public Widget shiftXPos(int modX) {
		setX(getX() + modX);
		return this;
	}

	/**
	 * Shifts this widget the given number of pixels in the y direction
	 * @param y pixels to shift
	 * @return widget
	 */
	public Widget shiftYPos(int modY) {
		setY(getY() + modY);
		return this;
	}

	/**
	 * Is true if this widget is visible and rendering on the screen
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Sets the visibility of this widget. If true, it will render normally. If false, it will not appear on the screen.
	 * @param enable the visibility
	 * @return widget
	 */
	public Widget setVisible(boolean enable) {
		if (isVisible() != enable) {
			visible = enable;
			updateSize();
			if (hasContainer()) {
				getContainer().deferLayout();
			}
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

	/**
	 * Called each tick this widget is updated. This widget is processed for isDirty() immediately afterwords.
	 */
	public void onTick() {
	}

	/**
	 * Set the widget's tooltip.
	 * Returns the current instance of the widget to make chainable calls.
	 */
	public Widget setTooltip(String t) {
		if (t != null && !getTooltip().equals(t)) {
			tooltip = t;
			autoDirty();
		}
		return this;
	}

	/**
	 * Gets the widget's tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * Gets the widget's container
	 */
	public Container getContainer() {
		return container;
	}

	/**
	 * Does the widget have a container
	 */
	public boolean hasContainer() {
		return container != null;
	}

	/**
	 * Sets the parant container for this widget
	 */
	public void setContainer(Container container) {
		if (hasContainer() && container != null && !getContainer().equals(container)) {
			getContainer().removeChild(this);
		}
		this.container = container;
	}

	/**
	 * Container Layout - Set whether the widget will be resized with its container
	 * @param fixed if it is a static size
	 * @return the container
	 */
	public Widget setFixed(boolean fixed) {
		if (isFixed() != fixed) {
			this.fixed = fixed;
			updateSize();
		}
		return this;
	}

	/**
	 * Container Layout - Whether the widget is fixed size inside its container
	 * @return
	 */
	public boolean isFixed() {
		return fixed;
	}

	// NOTE: Margins follow the same order as CSS

	/**
	 * Container Layout - Padding to use for automatic container layout - not included in dimensions
	 * @param marginAll
	 * @return
	 */
	public Widget setMargin(int marginAll) {
		return setMargin(marginAll, marginAll, marginAll, marginAll);
	}

	/**
	 * Container Layout - Padding to use for automatic container layout - not included in dimensions
	 * @param marginTopBottom
	 * @param marginLeftRight
	 * @return
	 */
	public Widget setMargin(int marginTopBottom, int marginLeftRight) {
		return setMargin(marginTopBottom, marginLeftRight, marginTopBottom, marginLeftRight);
	}

	/**
	 * Container Layout - Padding to use for automatic container layout - not included in dimensions
	 * @param marginTop
	 * @param marginLeftRight
	 * @param marginBottom
	 * @return
	 */
	public Widget setMargin(int marginTop, int marginLeftRight, int marginBottom) {
		return setMargin(marginTop, marginLeftRight, marginBottom, marginLeftRight);
	}

	/**
	 * Container Layout - Padding to use for automatic container layout - not included in dimensions
	 * @param marginTop
	 * @param marginRight
	 * @param marginBottom
	 * @param marginLeft
	 * @return
	 */
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

	/**
	 * Container Layout - Padding to use for automatic container layout - not included in dimensions
	 * @param marginTop
	 * @return
	 */
	public Widget setMarginTop(int marginTop) {
		if (getMarginTop() != marginTop) {
			this.marginTop = marginTop;
			updateSize();
			autoDirty();
		}
		return this;
	}

	/**
	 * Container Layout - Padding to use for automatic container layout - not included in dimensions
	 * @param marginRight
	 * @return
	 */
	public Widget setMarginRight(int marginRight) {
		if (getMarginRight() != marginRight) {
			this.marginRight = marginRight;
			updateSize();
			autoDirty();
		}
		return this;
	}

	/**
	 * Container Layout - Padding to use for automatic container layout - not included in dimensions
	 * @param marginBottom
	 * @return
	 */
	public Widget setMarginBottom(int marginBottom) {
		if (getMarginBottom() != marginBottom) {
			this.marginBottom = marginBottom;
			updateSize();
			autoDirty();
		}
		return this;
	}

	/**
	 * Container Layout - Padding to use for automatic container layout - not included in dimensions
	 * @param marginLeft
	 * @return
	 */
	public Widget setMarginLeft(int marginLeft) {
		if (getMarginLeft() != marginLeft) {
			this.marginLeft = marginLeft;
			updateSize();
			autoDirty();
		}
		return this;
	}

	/**
	 * Container Layout - Get the margin used for container layout
	 * @return
	 */
	public int getMarginTop() {
		return marginTop;
	}

	/**
	 * Container Layout - Get the margin used for container layout
	 * @return
	 */
	public int getMarginRight() {
		return marginRight;
	}

	/**
	 * Container Layout - Get the margin used for container layout
	 * @return
	 */
	public int getMarginBottom() {
		return marginBottom;
	}

	/**
	 * Container Layout - Get the margin used for container layout
	 * @return
	 */
	public int getMarginLeft() {
		return marginLeft;
	}

	/**
	 * Container Layout - Set the minimum width for this widget
	 * @param min
	 * @return
	 */
	public Widget setMinWidth(int min) {
		min = Math.max(min, 0);
		if (getMinWidth() != min) {
			minWidth = min;
			updateSize();
			setWidth(width); // Enforce our new size if needed
		}
		return this;
	}

	/**
	 * Container Layout - Get the minimum width for this widget
	 * @return
	 */
	public int getMinWidth() {
		return minWidth;
	}

	/**
	 * Container Layout - Set the maximum width for this widget
	 * @param min
	 * @return
	 */
	public Widget setMaxWidth(int max) {
		max = max <= 0 ? 427 : max;
		if (getMaxWidth() != max) {
			maxWidth = max;
			updateSize();
			setWidth(width); // Enforce our new size if needed
		}
		return this;
	}

	/**
	 * Container Layout - Get the maximum width for this widget
	 * @return
	 */
	public int getMaxWidth() {
		return maxWidth;
	}

	/**
	 * Container Layout - Set the minimum height for this widget
	 * @param min
	 * @return
	 */
	public Widget setMinHeight(int min) {
		min = Math.max(min, 0);
		if (getMinHeight() != min) {
			minHeight = min;
			updateSize();
			setHeight(height); // Enforce our new size if needed
		}
		return this;
	}

	/**
	 * Container Layout - Get the minimum height for this widget
	 * @return
	 */
	public int getMinHeight() {
		return minHeight;
	}

	/**
	 * Container Layout - Set the maximum height for this widget
	 * @param min
	 * @return
	 */
	public Widget setMaxHeight(int max) {
		max = max <= 0 ? 240 : max;
		if (getMaxHeight() != max) {
			maxHeight = max;
			updateSize();
			setHeight(height); // Enforce our new size if needed
		}
		return this;
	}

	/**
	 * Container Layout - Get the maximum height for this widget
	 * @return
	 */
	public int getMaxHeight() {
		return maxHeight;
	}

	/**
	 * Container Layout - Save the position for later restoration
	 * @return
	 */
	public Widget savePos() {
		orig_x = getX();
		orig_y = getY();
		return this;
	}

	/**
	 * Container Layout - Restore the earlier saved position
	 * @return
	 */
	public Widget restorePos() {
		setX(orig_x);
		setY(orig_y);
		return this;
	}

	/**
	 * Returns a copy of this widget with a new UUID.
	 * <p/>
	 * Copies will not be equal to each other, but will have the same internal data.
	 * <p/>
	 * Note: the copy will not be attached to a screen, nor be part of a container even if the original was.
	 * <p/>
	 * Warning: copy will not work on screens.
	 * @return a copy of this widget
	 */
	public Widget copy() {
		try {
			Widget copy = getType().getWidgetClass().newInstance();
			copy.setX(getX()) // Easier reading
					.setY(getY())
					.setWidth(getWidth())
					.setHeight(getHeight())
					.setVisible(isVisible())
					.setPriority(getPriority())
					.setTooltip(getTooltip())
					.setAnchor(getAnchor())
					.setMargin(getMarginTop(), getMarginRight(), getMarginBottom(), getMarginLeft()) //
					.setMinWidth(getMinWidth())
					.setMaxWidth(getMaxWidth())
					.setMinHeight(getMinHeight())
					.setMaxHeight(getMaxHeight())
					.setFixed(isFixed())
					.setAutoDirty(isAutoDirty())
					.animate(animType, animValue, animCount, animTicks, (animFlags & ANIM_REPEAT) != 0, (animFlags & ANIM_RESET) != 0);
			return copy;
		} catch (Exception e) {
			throw new IllegalStateException("Unable to create a copy of " + getClass() + ". Does it have a valid widget type?");
		}
	}

	/**
	 * Called when any dimension or limit changes
	 * @return widget
	 */
	public Widget updateSize() {
		if (hasContainer()) {
			container.updateSize();
		}
		return this;
	}

	/**
	 * Sets whether this widget should automatically be marked as dirty when it is changed.
	 * @param dirty if it should be automatic (default: true)
	 * @return widget
	 */
	public Widget setAutoDirty(boolean dirty) {
		return this;
	}

	/**
	 * Check whether this widget is automatically being marked as dirty.
	 * @return if autodirty is on
	 */
	public boolean isAutoDirty() {
		return autoDirty;
	}

	/**
	 * Sets the dirty flag automatically is isAutoDirty() returns true.
	 */
	public void autoDirty() {
		if (isAutoDirty()) {
			setDirty(true);
		}
	}

	/**
	 * Setup a simple automatic animation that automatically repeats and resets when finished.
	 * Please note that some animation types are limited to certain types of widget.
	 * All animation is carried out on the client, so it isn't possible to update
	 * the server side values affected by the animation...
	 * @param type  the type of animation to use
	 * @param value a custom value used by some types (default: 1)
	 * @param count how many frames
	 * @param ticks how many ticks per "frame"
	 * @return widget
	 */
	public Widget animate(WidgetAnim type, float value, short count, short ticks) {
		animate(type, value, count, ticks, true, true);
		return this;
	}

	/**
	 * Setup a simple automatic animation that resets when finished.
	 * Please note that some animation types are limited to certain types of widget.
	 * All animation is carried out on the client, so it isn't possible to update
	 * the server side values affected by the animation...
	 * @param type   the type of animation to use
	 * @param value  a custom value used by some types (default: 1)
	 * @param count  how many frames
	 * @param ticks  how many ticks per "frame"
	 * @param repeat should the animation be repeated
	 * @return widget
	 */
	public Widget animate(WidgetAnim type, float value, short count, short ticks, boolean repeat) {
		animate(type, value, count, ticks, repeat, true);
		return this;
	}

	/**
	 * Setup a simple automatic animation.
	 * Please note that some animation types are limited to certain types of widget.
	 * All animation is carried out on the client, so it isn't possible to update
	 * the server side values affected by the animation...
	 * @param type   the type of animation to use
	 * @param value  a custom value used by some types (default: 1)
	 * @param count  how many frames
	 * @param ticks  how many ticks per "frame"
	 * @param repeat should the animation be repeated
	 * @param reset  should it reset back to the first frame after finishing
	 * @return widget
	 */
	public Widget animate(WidgetAnim type, float value, short count, short ticks, boolean repeat, boolean reset) {
		if (!type.check(this)) {
			throw new TypeConstraintException("Cannot use Animation." + type.name() + " on " + getType().toString());
		}
		animType = type;
		animValue = value;
		animCount = count;
		animTicks = ticks;
		animFlags = (byte) ((repeat ? ANIM_REPEAT : 0) | (reset ? ANIM_RESET : 0));
		animTick = 0;
		animFrame = 0;
		autoDirty();
		return this;
	}

	/**
	 * Start the animation.
	 * @return widget
	 */
	public Widget animateStart() {
		if (animType != WidgetAnim.NONE) {
			animFlags |= ANIM_RUNNING;
			autoDirty();
		}
		return this;
	}

	/**
	 * Stop the animation, optionally letting it finish a loop.
	 * If the "reset" option was set when creating the animation it will go
	 * back to the first frame, otherwise it will stop where it is.
	 * @param finish should it finish the current loop (if repeating)
	 * @return widget
	 */
	public Widget animateStop(boolean finish) {
		if ((animFlags & ANIM_RUNNING) != 0 && finish) {
			animFlags |= ANIM_STOPPING;
			autoDirty();
		} else {
			animFlags &= ~ANIM_RUNNING;
			autoDirty();
		}
		return this;
	}

	/**
	 * This handles animation every frame.
	 * NOTE: On the server the default animation handler doesn't do anything as
	 * all animation is handled on the client. If you are writing an animation
	 * handler then please keep bandwidth use in mind...
	 */
	public void onAnimate() {
		if ((animFlags & ANIM_RUNNING) == 0 || animTicks == 0 || ++animTick % animTicks != 0) {
			return;
		}
		// We're running, and it's ready for our next frame...
		if (++animFrame == animCount) {
			animFrame = 0;
			if ((animFlags & ANIM_STOPPING) != 0 || (animFlags & ANIM_REPEAT) == 0) {
				animFlags &= ~ANIM_RUNNING;
			}
		}
	}

	/**
	 * This is called when the animation stops, and can be used for chaining
	 * together animations.
	 * This is called whether the stop was automatic or manual, and occurs at
	 * the start of the final frame (so the frame hasn't had any ticks of
	 * visibility yet).
	 * NOTE: On the server the values changed in the animation <b>will not<b>
	 * have changed, this is due to the animation being client side. If you
	 * didn't tell the animation to reset after finishing then please remember
	 * to change them!
	 */
	public void onAnimateStop() {
	}

	/**
	 * Returns true if the widget has had its position set.
	 * @return true if it has a position
	 */
	public boolean hasPosition() {
		return hasPosition;
	}

	/**
	 * Returns true if a widget has had its size set.
	 * @return if it has a size
	 */
	public boolean hasSize() {
		return hasSize;
	}
}
