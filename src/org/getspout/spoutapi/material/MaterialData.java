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
	public static final Block dispenser = new Solid(23);
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
	public static final Block whiteWool = new Wool(35,0);
	public static final Block orangeWool = new Wool(35,1);
	public static final Block magentaWool = new Wool(35,2);
	public static final Block lightBlueWool = new Wool(35,3);
	public static final Block yellowWool = new Wool(35,4);
	public static final Block limeWool = new Wool(35,5);
	public static final Block pinkWool = new Wool(35,6);
	public static final Block greyWool = new Wool(35,7);
	public static final Block lightGreyWool = new Wool(35,8);
	public static final Block cyanWool = new Wool(35,9);
	public static final Block purpleWool = new Wool(35,10);
	public static final Block blueWool = new Wool(35,11);
	public static final Block brownWool = new Wool(35,12);
	public static final Block greenWool = new Wool(35,13);
	public static final Block redWool = new Wool(35,14);
	public static final Block blackWool = new Wool(35,15);
	//Purple error block wool, 35 - 16 ?
	//public static final Block movedByPiston
	public static final Block dandelion = new Solid(37);
	public static final Block rose = new Solid(38);
	public static final Block brownMushroom = new Solid(39);
	public static final Block redMushroom = new Solid(40);
	public static final Block goldBlock = new Solid(41);
	public static final Block ironBlock = new Solid(42);
	//public static final Block doubleSlabs
	//public static final Block slabs
	public static final Block brick = new Solid(45);
	public static final Block tnt = new Solid(46);
	public static final Block bookshelf = new Solid(47);
	public static final Block mossStone = new Solid(48);
	public static final Block obsidian = new Solid(49);
	//public static final Block torch
	//public static final Block fire
	public static final Block monsterSpawner = new Solid(52);
	//public static final Block woodenStairs
	public static final Block chest = new Solid(54);
	//public static final Block redstoneWire
	public static final Block diamondOre = new Solid(57);
	public static final Block craftingTable = new Solid(58);
	//public static final Block seeds
	public static final Block farmland = new Solid(60);
	public static final Block furnace = new Solid(61);
	public static final Block burningfurnace = new Solid(62);
	//public static final Block signPost
	public static final Block woodenDoor = new Solid(64);
	//public static final Block ladders
	//public static final Block wallSign
	//public static final Block lever
	//public static final Block stonePressurePlate
	public static final Block ironDoor = new Solid(71);
	//public static final Block woodenPressurePlate
	public static final Block redstoneOre = new Solid(73);
	public static final Block glowingRedstoneOre = new Solid(74);
	//public static final Block redstoneTorchOff
	//public static final Block redstoneTorchOn
	//public static final stoneButton
	public static final Block snow = new Solid(78);
	public static final Block Ice = new Solid(79);
	public static final Block snowBlock = new Solid(80);
	public static final Block cactus = new Solid(81);
	public static final Block clayBlock = new Solid(82);
	//public static final Block sugarCane
	public static final Block jukebox = new Solid(84);
	public static final Block fence = new Solid(85);
	public static final Block pumpkin = new Solid(86);
	public static final Block netherrack = new Solid(87);
	public static final Block soulSand = new Solid(88);
	public static final Block glowstoneBlow = new Solid(89);
	//public static final Block portal
	public static final Block jackOLantern = new Solid(91);
	//public static final Block cake
	//public static final Block redstoneRepeaterOff
	//public static final Block redstoneRepeaterOn
	//public static final Block lockedChest
	public static final Block trapdoor = new Solid(96);
	
	/*
	 * 1.8 Blocks
	 */
	//public static final Block silverfishStone = new Solid(97);
	//public static final Block stoneBricks = new Solid(98);
	//public static final Block mushroomA = new Solid(99);
	//public static final Block mushroomB = new Solid(100);
	//public static final Block ironBars = new Solid(101);
	//public static final Block glassPane = new Solid(102);
	//public static final Block watermelon = new Solid(103);
	//public static final Block unknown1 = new Solid(104);
	//public static final Block unknown2 = new Solid(105);
	//public static final Block vines = new Solid(106);
	//public static final Block fenceGate = new Solid(107);
	//public static final Block brickStairs = new Solid(108);
	//public static final Block stoneBrickStairs = new Solid(109);
	
	
	

}
