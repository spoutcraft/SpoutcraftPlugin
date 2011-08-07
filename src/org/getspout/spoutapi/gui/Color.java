package org.getspout.spoutapi.gui;

public class Color {
    private float red;
    private float green;
    private float blue;
    
    public Color(float r, float g, float b){
        red = r;
        green = g;
        blue = b;
    }
    
    public float getRedF(){
        return red;
    }
    
    public float getGreenF(){
        return green;
    }
    
    public float getBlueF(){
        return blue;
    }
    
    public Color setRed(float r){
        red = r;
        return this;
    }
    
    public Color setGreen(float g){
        green = g;
        return this;
    }
    
    public Color setBlue(float b){
        blue = b;
        return this;
    }
    
    public Color clone() {
        return new Color(red, green, blue);
    }
}