package org.getspout.spoutapi.material;

public interface Material {
	
	public int getRawId();
	
	public int getRawData();
	
	public boolean isHasSubtypes();
	
	public String getName();
	
	public void setName(String name);
}
