package org.getspout.spoutapi.gui;

import org.getspout.spoutapi.gui.Widget;

public interface Container extends Widget {

	/**
	 * Adds a single widget to a container
	 * @param child The widget to add
	 * @return Widget
	 */
	public Container addChild(Widget child);

	/**
	 * Adds a list of children to a container.
	 * @param children The widgets to add
	 * @return 
	 */
	public Container addChildren(Widget... children);

	/**
	 * Removes a single widget from this container
	 * @param child The widget to add
	 * @return
	 */
	public Container removeChild(Widget child);
	
	/**
	 * Get a list of widgets inside this container.
	 * @return 
	 */
	public Widget[] getChildren();
	
	public int getOffsetX();
	
	public int getOffsetY();
	
	/**
	 * Set the automatic layout type for children, triggered by setWidth() or setHeight()
	 * @param type ContainerType.VERTICAL, .HORIZONTAL or .OVERLAY
	 * @return the container
	 */
	public Container setLayout(ContainerType type);
	
	/**
	 * Get the automatic layout type for children
	 * @return the type of container
	 */
	public ContainerType getLayout();
	
	/**
	 * Set whether the container will be resized with it's parents
	 * @param fixed if it is a static size
	 * @return the container
	 */
	public Container setFixed(boolean fixed);
	
	/**
	 * Whether the container is fixed size or not
	 * @return 
	 */
	public boolean getFixed();
	
	/**
	 * Force the container to re-layout all non-fixed children.
	 * This will re-position and resize all child elements.
	 * This is automatically called when the container gets resized.
	 * @return 
	 */
	public Container updateLayout();
}