package org.getspout.spoutapi.player;

public enum EntitySkinType {
	DEFAULT(0),
	SPIDER_EYES(1),
	SHEEP_FUR(2),
	WOLF_ANGRY(3),
	WOLF_TAMED(4),
	PIG_SADDLE(5),
	GHAST_MOUTH(6),
	;
	
	private final byte id;
	private EntitySkinType(int id){
		this.id = (byte)id;
	}
	
	public byte getId(){
		return id;
	}
	
}
