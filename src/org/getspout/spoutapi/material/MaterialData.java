package org.getspout.spoutapi.material;

import org.getspout.spoutapi.material.block.*;

public class MaterialData {
	
	public static final Block air = new Air();
	public static final Block stone = new Solid(1);
	public static final Block grass = new Grass();
	public static final Block dirt = new Solid(3);
	public static final Block cobblestone = new Solid(4);
	public static final Block wood = new Solid(5);
	public static final Block sapling = new Sapling(0);
	public static final Block spruceSapling = new Sapling(1);
	public static final Block birchSapling = new Sapling(2);
	public static final Block bedrock = new Solid(7);
	public static final Block water = new GenericLiquid(9, true);
	public static final Block stationaryWater = new GenericLiquid(10, false);
	public static final Block lava = new GenericLiquid(11, true);
	public static final Block stationaryLava = new GenericLiquid(12, false);
	public static final Block sand = new Solid(12, true);
	public static final Block gravel = new Solid(13, true);
	public static final Block goldOre = new Solid(14);
	public static final Block ironOre = new Solid(15);
	public static final Block coalOre = new Solid(16);
	public static final Block log = new Tree(17, 0);
	public static final Block spruceLog = new Tree(17, 1);
	public static final Block birchLog = new Tree(17, 2);
	public static final Block leeaves = new Tree(18, 0);
	public static final Block spruceLeaves = new Tree(18, 1);
	public static final Block birchLeaves= new Tree(18, 2);
	public static final Block sponge = new Solid(19);
	public static final Block glass = new Solid(20);
	public static final Block lapisOre = new Solid(21);
	public static final Block lapisBlock = new Solid(22);
	public static final Block furnace = new Solid(23);
	public static final Block sandstone = new Solid(24);
	public static final Block noteblock = new Solid(25);
	public static final Block bed = new Solid(26);
	public static final Block poweredRail = new Solid(27);
	public static final Block detectorRail = new Solid(28);
	public static final Block pistonStickyBase = new Solid(29);
	public static final Block web = new Solid(30);
	public static final Block deadShrub = new LongGrass(31, 0);
	public static final Block tallGrass = new LongGrass(31, 1);
	public static final Block fern = new LongGrass(31, 2);
	public static final Block deadBush = new LongGrass(32, 0);
	public static final Block pistonBase = new Solid(33);
	public static final Block pistonExtension = new Solid(34);
	//public static final Block wool

}
