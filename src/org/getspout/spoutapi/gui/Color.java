package org.getspout.spoutapi.gui;

public class Color {
	private float red;
	private float green;
	private float blue;
	private float alpha = 1;
	
	/**
	 * Constructs the color with RGB spec
	 * @param r Red part of the color, ranging from 0.0 to 1.0
	 * @param g Green part of the color, ranging from 0.0 to 1.0
	 * @param b Blue part of the color, ranging from 0.0 to 1.0
	 * @param a Alpha part of the color, ranging from 0.0 to 1.0
	 */
	public Color(float r, float g, float b, float a){
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}
	
	public Color(float r, float g, float b){
		this(r, g, b, 1);
	}
	
	/**
	 * 
	 * @return Red part of the color, as float
	 */
	public float getRedF(){
		return red;
	}
	
	/**
	 * 
	 * @return Green part of the color, as float
	 */
	public float getGreenF(){
		return green;
	}
	
	/**
	 * 
	 * @return Blue part of the color, as float
	 */
	public float getBlueF(){
		return blue;
	}
	
	/**
	 * 
	 * @return Blue part of the color, as float
	 */
	public float getAlphaF(){
		return alpha;
	}
	
	/**
	 * 
	 * @param r Red part of the color, ranging from 0.0 to 1.0
	 * @return the object to make call chainable.
	 */
	public Color setRed(float r){
		red = r;
		return this;
	}
	
	/**
	 * 
	 * @param g Green part of the color, ranging from 0.0 to 1.0
	 * @return the object to make call chainable.
	 */
	public Color setGreen(float g){
		green = g;
		return this;
	}
	
	/**
	 * 
	 * @param b Blue part of the color, ranging from 0.0 to 1.0
	 * @return the object to make call chainable.
	 */
	public Color setBlue(float b){
		blue = b;
		return this;
	}
	
	/**
	 * 
	 * @param b Blue part of the color, ranging from 0.0 to 1.0
	 * @return the object to make call chainable.
	 */
	public Color setAlpha(float a){
		alpha = a;
		return this;
	}
	
	/**
	 * Clones the object.
	 * @return a copy of the object.
	 */
	public Color clone() {
		return new Color(red, green, blue, alpha);
	}
	
	/**
	 * Gets a color value that will set the color to null on the client.
	 * @return
	 */
	public static Color remove(){
		return new Color(-2,-2,-2,-2);
	}
	
	/**
	 * Gets a color value that will be ignored on the client.
	 * @return
	 */
	public static Color ignore(){
		return new Color(-1,-1,-1,-1);
	}
}