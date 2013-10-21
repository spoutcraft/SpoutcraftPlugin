/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * SpoutcraftPlugin is licensed under the GNU Lesser General Public License.
 *
 * SpoutcraftPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutcraftPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spoutapi.gui;

/**
 * This defines a Color for use within other widgets.
 * <p/>
 * Colors are made up of red, green, blue and alpha (transparency). If the
 * alpha is not used then it is assumed to be opaque (solid).
 */
public class Color {
	protected int red;
	protected int green;
	protected int blue;
	protected int alpha = 0xFF;

	/**
	 * Constructs the color with RGB spec
	 * @param r Red part of the color, ranging from 0.0 to 1.0
	 * @param g Green part of the color, ranging from 0.0 to 1.0
	 * @param b Blue part of the color, ranging from 0.0 to 1.0
	 * @param a Alpha part of the color, ranging from 0.0 to 1.0
	 */
	public Color(float r, float g, float b, float a) {
		red = (int) (r * 255.0f);
		green = (int) (g * 255.0f);
		blue = (int) (b * 255.0f);
		alpha = (int) (a * 255.0f);
	}

	public Color(float r, float g, float b) {
		this(r, g, b, 1);
	}

	/**
	 * Constructs the color with RGB spec
	 * @param r Red part of the color, ranging from 0 to 255
	 * @param g Green part of the color, ranging from 0 to 255
	 * @param b Blue part of the color, ranging from 0 to 255
	 * @param a Alpha part of the color, ranging from 0 to 255
	 */
	public Color(int r, int g, int b, int a) {
		this.red = r;
		this.green = g;
		this.blue = b;
		this.alpha = a;
	}

	public Color(int r, int g, int b) {
		this(r, g, b, 255);
	}

	public Color(int argb) {
		this((argb & 0xFF0000) >>> 16, (argb & 0xFF00) >>> 8, argb & 0xFF, argb >>> 24);
	}

	/**
	 * Construct the color using RGBA hex string
	 * <p/>
	 * Note: Alpha channel isn't supported due limitations of Integer.parseInt
	 * @param color Hex string encoded color in RRGGBB format (example: FF0000 - red)
	 */
	public Color(String color) {
		this(Integer.parseInt(color, 16)); // It's completely okay about possible NumberFormatException
	}

	/**
	 * @return Red part of the color, as float
	 */
	public float getRedF() {
		return red / 255.0f;
	}

	/**
	 * @return Red part of the color, as int
	 */
	public int getRedI() {
		return red;
	}

	/**
	 * @return Green part of the color, as float
	 */
	public float getGreenF() {
		return green / 255.0f;
	}

	/**
	 * @return Green part of the color, as int
	 */
	public int getGreenI() {
		return green;
	}

	/**
	 * @return Blue part of the color, as float
	 */
	public float getBlueF() {
		return blue / 255.0f;
	}

	/**
	 * @return Blue part of the color, as int
	 */
	public int getBlueI() {
		return blue;
	}

	/**
	 * @return Alpha part of the color, as float
	 */
	public float getAlphaF() {
		return alpha / 255.0f;
	}

	/**
	 * @return Alpha part of the color, as int
	 */
	public int getAlphaI() {
		return alpha;
	}

	/**
	 * @param r Red part of the color, ranging from 0.0 to 1.0
	 * @return the object to make call chainable.
	 */
	public Color setRed(float r) {
		red = (int) (r * 255.0f);
		return this;
	}

	/**
	 * @param r Red part of the color, ranging from 0 to 255
	 * @return the object to make call chainable.
	 */
	public Color setRed(int r) {
		red = r;
		return this;
	}

	/**
	 * @param g Green part of the color, ranging from 0.0 to 1.0
	 * @return the object to make call chainable.
	 */
	public Color setGreen(float g) {
		green = (int) (g * 255.0f);
		return this;
	}

	/**
	 * @param g Green part of the color, ranging from 0 to 255
	 * @return the object to make call chainable.
	 */
	public Color setGreen(int g) {
		green = g;
		return this;
	}

	/**
	 * @param b Blue part of the color, ranging from 0.0 to 1.0
	 * @return the object to make call chainable.
	 */
	public Color setBlue(float b) {
		blue = (int) (b * 255.0f);
		return this;
	}

	/**
	 * @param b Blue part of the color, ranging from 0 to 255
	 * @return the object to make call chainable.
	 */
	public Color setBlue(int b) {
		blue = b;
		return this;
	}

	/**
	 * @param a Alpha part of the color, ranging from 0.0 to 1.0
	 * @return the object to make call chainable.
	 */
	public Color setAlpha(float a) {
		alpha = (int) (a * 255.0f);
		return this;
	}

	/**
	 * @param a Alpha part of the color, ranging from 0 to 255
	 * @return the object to make call chainable.
	 */
	public Color setAlpha(int a) {
		alpha = a;
		return this;
	}

	/**
	 * Clones the object.
	 * @return a copy of the object.
	 */
	@Override
	public Color clone() {
		return new Color(red, green, blue, alpha);
	}

	/**
	 * Gets a color value that will set the color to null on the client.
	 * @return
	 */
	public static Color remove() {
		return new Color(-2F, -2F, -2F, -2F);
	}

	/**
	 * Gets a color value that will be ignored on the client.
	 * @return
	 */
	public static Color ignore() {
		return new Color(-1F, -1F, -1F, -1F);
	}

	public int toInt() {
		return (getAlphaI() & 0xFF) << 24 | (getRedI() & 0xFF) << 16 | (getGreenI() & 0xFF) << 8 | (getBlueI() & 0xFF);
	}

	@Override
	public String toString() {
		return "Color(" + this.red + ", " + this.green + ", " + this.blue + ", " + this.alpha + ")";
	}
}
