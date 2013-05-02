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
package org.getspout.spout.block.mcblock;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import net.minecraft.server.v1_5_R3.Block;
import net.minecraft.server.v1_5_R3.BlockButtonAbstract;
import net.minecraft.server.v1_5_R3.BlockDiodeAbstract;
import net.minecraft.server.v1_5_R3.BlockFurnace;
import net.minecraft.server.v1_5_R3.BlockHalfTransparant;
import net.minecraft.server.v1_5_R3.BlockMinecartTrack;
import net.minecraft.server.v1_5_R3.BlockPressurePlateAbstract;
import net.minecraft.server.v1_5_R3.BlockPressurePlateBinary;
import net.minecraft.server.v1_5_R3.BlockPumpkin;
import net.minecraft.server.v1_5_R3.BlockRedstoneOre;
import net.minecraft.server.v1_5_R3.BlockRedstoneTorch;
import net.minecraft.server.v1_5_R3.BlockStem;
import net.minecraft.server.v1_5_R3.BlockStepAbstract;
import net.minecraft.server.v1_5_R3.Entity;
import net.minecraft.server.v1_5_R3.EntityHuman;
import net.minecraft.server.v1_5_R3.EntityPlayer;
import net.minecraft.server.v1_5_R3.EnumMobType;
import net.minecraft.server.v1_5_R3.IBlockAccess;
import net.minecraft.server.v1_5_R3.Material;
import net.minecraft.server.v1_5_R3.World;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.material.MaterialData;
import org.getspout.spoutapi.material.Tool;
import org.getspout.spoutapi.player.SpoutPlayer;

public final class CustomMCBlock implements MethodInterceptor {
	private final Block wrapped;
	private CustomMCBlock(Block toWrap) {
		this.wrapped = toWrap;
	}

	public Block getWrapped() {
		return wrapped;
	}

	private org.getspout.spoutapi.material.CustomBlock getCustomBlock(World world, int x, int y, int z) {
		short[] customIds = SpoutManager.getChunkDataManager().getCustomBlockIds(world.getWorld(), x >> 4, z >> 4);
		if (customIds != null) {
			int index = getIndex(x, y, z);
			short id = customIds[index];
			return MaterialData.getCustomBlock(id);
		}
		return null;
	}

	protected static int getIndex(int x, int y, int z) {
		return (x & 0xF) << 0xC | (z & 0xF) << 0x8 | (y & 0xFF);
	}

	@SuppressWarnings("rawtypes")
	private static Block createProxy(Block parent) {
		Enhancer enc = new Enhancer();
		enc.setSuperclass(parent.getClass());
		enc.setInterfaces(new Class[] { WrappedMCBlock.class });
		enc.setCallback(new CustomMCBlock(parent));
		enc.setClassLoader(CustomMCBlock.class.getClassLoader());

		Constructor<?>[] constructors = parent.getClass().getDeclaredConstructors();
		BlockConstructor use = null;
		for (Constructor<?> c : constructors) {
			c.setAccessible(true);
			for (BlockConstructor type : BlockConstructor.values()) {
				if (Arrays.deepEquals(c.getParameterTypes(), type.constructor)) {
					use = type;
					break;
				}
			}
		}

		if (use == null) {
			System.err.println("Unable to find matching constructor for class: " + parent.getClass().getName());
			for (Constructor<?> c : constructors) {
				System.err.println("    " + Arrays.toString(c.getParameterTypes()));
			}
			return null;
		}

		try {
			Block proxy;
			switch(use) {
				case None:
					proxy = (Block) enc.create(); break;
				case Id:
					proxy = (Block) enc.create(use.constructor, new Object[] {parent.id}); break;
				case IdAndStep:
					{
						boolean field2;
						if (parent instanceof BlockStepAbstract) {
							field2 = ((Boolean)getField(BlockStepAbstract.class, parent, "a")).booleanValue();
						} else if (parent instanceof BlockFurnace) {
							field2 = ((Boolean)getField(parent, "b")).booleanValue();
						} else if (parent instanceof BlockRedstoneTorch) {
							field2 = ((Boolean)getField(parent, "isOn")).booleanValue();
						} else if (parent instanceof BlockDiodeAbstract) {
							field2 = ((Boolean)getField(BlockDiodeAbstract.class, parent, "a")).booleanValue();
						} else {
							field2 = ((Boolean)getField(parent, "a")).booleanValue();
						}
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, field2});
					}
					break;
				case IdAndMaterial:
					proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, parent.material}); break;
				case IdAndTexture:
					proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, (Integer)getField(parent, "a")}); break;
				case IdTextureAndMaterial:
					proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, parent.material}); break;
				case IdMaterialAndFlag:
					proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, parent.material, false}); break;
				case IdAndName:
					{
						String name = (String) getField(parent, "a");
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, name});
					}
					break;
				case IdNameAndMaterial:
					{
						String name = (String) getField(parent, "a");
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, name, parent.material});
					}
					break;
				case IdNameMaterialAndDrop:
					{
						String name = (String) getField(BlockPressurePlateAbstract.class, parent, "a");
						int field4 = (Integer) getField(parent, "a");
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, name, parent.material, field4});
					}
					break;
				case IdBlockAndOther:
					{
						Block field2 = ((Block)getField(parent, "b"));
						int field3 = ((Integer)getField(parent, "c"));
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, field2, field3});
					}
					break;
				case IdMaterialAndDrop:
					{
						int field3 = ((Integer)getField(parent, "b"));
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, parent.material, field3});
					}
					break;
				case SignBlock:
					{
						Class field2 = ((Class)getField(parent, "a"));
						boolean field3 = ((Boolean)getField(parent, "b"));
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, field2, field3});
					}
					break;
				case IdTextureAndTicks:
					{
						boolean field3;
						if (parent instanceof BlockMinecartTrack || parent instanceof BlockRedstoneOre || parent instanceof BlockButtonAbstract || parent instanceof BlockPumpkin) {
							field3 = ((Boolean)getField(parent, "a")).booleanValue();
						} else {
							field3 = ((Boolean)getField(parent, "isTicking")).booleanValue();
						}
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, field3});
					}
					break;
				case PressurePlate:
					{
						EnumMobType field3 = ((EnumMobType)getField(parent, "a"));
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, field3, parent.material});
					}
					break;
				case HugeMushroom:
					{
						int field4 = ((Integer)getField(parent, "a"));
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, parent.material, field4});
					}
					break;
				case BlockStem:
					{
						Block field2;
						if (parent instanceof BlockStem) {
							field2 = ((Block)getField(parent, "blockFruit"));
						} else {
							field2 = Block.COBBLESTONE;
						}
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, field2});
					}
					break;
				case IdTextureMaterialAndTransparent:
					{
						boolean field4 = ((Boolean)getField(BlockHalfTransparant.class, parent, "a")).booleanValue();
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, parent.material, field4});
					}
					break;
				case IdTextureDataMaterialAndDrops:
					{
						int field3 = (Integer) getField(parent, "a");
						boolean field5 = ((Boolean)getField(parent, "b")).booleanValue();
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, field3, parent.material, field5});
					}
					break;
				case IdNameMaterialAndMobType:
					{
						String name = (String) getField(BlockPressurePlateAbstract.class, parent, "a");
						EnumMobType mobs = (EnumMobType) getField(BlockPressurePlateBinary.class, parent, "a");
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, name, parent.material, mobs});
					}
					break;
				case IdStringStringMaterialAndFlag:
					{
						String field2 = (String) getField(parent, "a");
						String field3 = (String) getField(parent, "c");
						boolean field5 = (Boolean) getField(parent, "b");
						proxy = (Block) enc.create(use.constructor, new Object[] {parent.id, field2, field3, parent.material, field5});
					}
					break;
				default: throw new IllegalStateException("Unknown type " +  use);
			}
			return proxy;
		} catch (RuntimeException e) {
			System.err.println("Error creating : " + parent.getClass().getName() + " with constructor: " + use.name());
			e.printStackTrace();
			return null;
		}
	}

	public static void replaceBlocks() {
		//Store proper lighting values for opacity
		final int[] lightOpacity = new int[Block.lightBlock.length];
		System.arraycopy(Block.lightBlock, 0, lightOpacity, 0, lightOpacity.length);
		//Store...whatever s is
		final boolean[] sArray = new boolean[Block.s.length];
		System.arraycopy(Block.s, 0, sArray, 0, Block.s.length);
		//Store...whatever u is
		final boolean[] uArray = new boolean[Block.u.length];
		System.arraycopy(Block.u, 0, sArray, 0, Block.u.length);
		//Store...whatever w is
		final boolean[] wArray = new boolean[Block.w.length];
		System.arraycopy(Block.w, 0, wArray, 0, Block.w.length);
		//Store proper lighting values for emission
		final int[] lightEmission = new int[Block.lightEmission.length];
		System.arraycopy(Block.lightEmission, 0, lightEmission, 0, Block.lightEmission.length);

		for (int i = 0; i < Block.byId.length; i++) {
			if (Block.byId[i] != null) {
				Block parent = Block.byId[i];
				Block.byId[i] = null;
				try {
					Block fake  = createProxy(parent);;
					if (fake != null) {
						Block.byId[i] = fake;
					} else {
						Block.byId[i] = parent;
					}
				} catch (Throwable t) {
					System.err.println("Error replacing : " + parent.getClass().getName());
					t.printStackTrace();
					Block.byId[i] = parent;
				}
			}
		}
		//Fix values
		System.arraycopy(lightOpacity, 0, Block.lightBlock, 0, lightOpacity.length);
		//System.arraycopy(sArray, 0, Block.s, 0, sArray.length);
		//System.arraycopy(uArray, 0, Block.u, 0, uArray.length);
		//System.arraycopy(wArray, 0, Block.w, 0, wArray.length);
		System.arraycopy(lightEmission, 0, Block.lightEmission, 0, lightEmission.length);
	}

	@SuppressWarnings("rawtypes")
	private static enum BlockConstructor {
		None(new Class[0]),
		Id(new Class[] {int.class}),
		IdAndStep(new Class[] {int.class, boolean.class}),
		IdAndMaterial(new Class[] {int.class, Material.class}),
		IdAndTexture(new Class[] {int.class, int.class}),
		IdAndName(new Class[] {int.class, String.class}),
		IdMaterialAndDrop(new Class[] {int.class, Material.class, int.class}),
		IdNameAndMaterial(new Class[] {int.class, String.class, Material.class}),
		IdNameMaterialAndDrop(new Class[] {int.class, String.class, Material.class, int.class}),
		IdMaterialAndFlag(new Class[] {int.class, Material.class, boolean.class}),
		IdBlockAndOther(new Class[] {int.class, Block.class, int.class}),
		SignBlock(new Class[] {int.class, Class.class, boolean.class}),
		PressurePlate(new Class[] {int.class, int.class, EnumMobType.class, Material.class}),
		HugeMushroom(new Class[] {int.class, Material.class, int.class, int.class}),
		BlockStem(new Class[] {int.class, Block.class}),
		IdTextureAndTicks(new Class[] {int.class, int.class, boolean.class}),
		IdTextureAndMaterial(new Class[] {int.class, int.class, Material.class}),
		IdTextureMaterialAndTransparent(new Class[] {int.class, int.class, Material.class, boolean.class}),
		IdTextureDataMaterialAndDrops(new Class[] {int.class, int.class, int.class, Material.class, boolean.class}),
		IdNameMaterialAndMobType(new Class[]{int.class, String.class, Material.class, EnumMobType.class}),
		IdStringStringMaterialAndFlag(new Class[] {int.class, String.class, String.class, Material.class, boolean.class});

		private final Class[] constructor;
		BlockConstructor(Class[] constructor) {
			this.constructor = constructor;
		}
		;
	}

	// TODO This causes redstone issues with glass (allows power through glass, vanilla does not) but it can't
	// Be solved from a plugin. Implemented workaround: Don't update glass unless non-opaque custom blocks are on the server.
	public static void updateGlass() {
		// Allow placement of blocks on glass
		try {
			Field field = Material.SHATTERABLE.getClass().getDeclaredField("I");
			field.setAccessible(true);
			field.setBoolean(Material.SHATTERABLE, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Block.lightBlock[Block.GLASS.id] = 0;
	}

	public static void resetBlocks() {
		for (int i = 0; i < Block.byId.length; i++) {
			if (Block.byId[i] != null) {
				Block parent = Block.byId[i];
				if (parent instanceof WrappedMCBlock) {
					WrappedMCBlock wrapped = (WrappedMCBlock) parent;
					Block.byId[i] = null;
					Block.byId[i] = wrapped.getWrapped();
				}
			}
		}
	}

	private static Object getField(Block parent, String fieldName) {
		return getField(parent.getClass(), parent, fieldName);
	}

	private static Object getField(Class<?> clazz, Block parent, String fieldName) {
		try {
			Field field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(parent);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	private static Method findRecursive(Class<?> clazz, String name, Class[] args) {
		for (Method m : clazz.getDeclaredMethods()) {
			if (m.getName().equals(name)) {
				if (Arrays.deepEquals(m.getParameterTypes(), args)) {
					return m;
				}
			}
		}
		if (clazz.getSuperclass() != null) {
			return findRecursive(clazz.getSuperclass(), name, args);
		}
		return null;
	}

	private final ConcurrentHashMap<Method, Method> fastMethodCache = new ConcurrentHashMap<Method, Method>(100);
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Method m = fastMethodCache.get(method);
		if (m == null) {
			// Special cases
			if (method.getName().equals("getWrapped")) {
				return getWrapped();
			} else if (method.getName().equals("setHardness")) {
				Field f = Block.class.getDeclaredField("strength");
				f.setAccessible(true);
				f.set(wrapped, args[0]);
				return null;
			} else {
				m = findRecursive(wrapped.getClass(), method.getName(), method.getParameterTypes());
		        if (m != null) {
		        	m.setAccessible(true);
		        	fastMethodCache.put(method, m);
		        } else {
		        	System.err.println("Unable to find method : " + method.getName() + " with: " + Arrays.toString(method.getParameterTypes()));	 
		        	return null;
				}
			}
		}
		// Overridden methods
		if (method.getName().equals("a") && Arrays.deepEquals(method.getParameterTypes(), new Class[] {IBlockAccess.class, int.class, int.class, int.class, int.class})) {
			World world = (World) args[0];
			int x = (Integer) args[1];
			int y = (Integer) args[2];
			int z = (Integer) args[3];
			int face = (Integer) args[4];

			org.getspout.spoutapi.material.CustomBlock block = getCustomBlock(world, x, y, z);
			if (block != null) {
				return block.isProvidingPowerTo(world.getWorld(), x, y, z, org.bukkit.craftbukkit.v1_5_R3.block.CraftBlock.notchToBlockFace(face));
			}
		} else if (method.getName().equals("c") && Arrays.deepEquals(method.getParameterTypes(), new Class[] {World.class, int.class, int.class, int.class, int.class})) {
			World world = (World) args[0];
			int x = (Integer) args[1];
			int y = (Integer) args[2];
			int z = (Integer) args[3];
			int face = (Integer) args[4];
			org.getspout.spoutapi.material.CustomBlock block = getCustomBlock(world, x, y, z);
			if (block != null) {
				return block.isProvidingPowerTo(world.getWorld(), x, y, z, org.bukkit.craftbukkit.v1_5_R3.block.CraftBlock.notchToBlockFace(face));
			}
		} else if (method.getName().equals("b") && Arrays.deepEquals(method.getParameterTypes(), new Class[] {World.class, int.class, int.class, int.class, Entity.class})) {
			World world = (World) args[0];
			int x = (Integer) args[1];
			int y = (Integer) args[2];
			int z = (Integer) args[3];
			Entity e = (Entity) args[4];
			org.getspout.spoutapi.material.CustomBlock block = getCustomBlock(world, x, y, z);
			if (block != null) {
				block.onEntityMoveAt(world.getWorld(), x, y, z, e.getBukkitEntity());
				return null;
			}
		} else if (method.getName().equals("interact")) {
			World world = (World) args[0];
			int x = (Integer) args[1];
			int y = (Integer) args[2];
			int z = (Integer) args[3];
			Entity human = (Entity) args[4];
			org.getspout.spoutapi.material.CustomBlock block = getCustomBlock(world, x, y, z);
			if (block != null && human instanceof EntityPlayer) {
				return block.onBlockInteract(world.getWorld(), x, y, z, ((SpoutPlayer) human.getBukkitEntity()));
			}
		} else if (method.getName().equals("getDamage")) {
			EntityHuman human = (EntityHuman) args[0];
			World world = (World) args[1];
			int x = (Integer) args[2];
			int y = (Integer) args[3];
			int z = (Integer) args[4];
			org.getspout.spoutapi.material.CustomBlock block = getCustomBlock(world, x, y, z);
			if (block != null) {
				if (block instanceof org.getspout.spoutapi.material.CustomBlock) {
					SpoutPlayer player = (SpoutPlayer) human.getBukkitEntity();
					float def;
					SpoutItemStack inHand = player.getItemInHand() == null ? null : new SpoutItemStack(player.getItemInHand());
					org.getspout.spoutapi.material.Material item = inHand.getMaterial();

					float hardness = block.getHardness();
					if (hardness <= 0F) {
						return m.invoke(wrapped, args);
					}

					def = (!human.a(wrapped) ? 1.0F / hardness / 100.0F : human.a(wrapped, false) / hardness / 30.0F); //TODO EntityHuman.a(Block, boolean) appears to not make any use of the flag variable...

					if (!(item instanceof CustomItem)) {
						return def;
					}

					if (!(item instanceof Tool)) {
						return def;
					}

					Tool tool = (Tool) item;

					float modifier = tool.getStrengthModifier(block);

					return modifier / hardness / (modifier > 1F ? 30F : 100F);
				}
			}
		} else if (method.getName().equals("remove")) {
			World world = (World) args[0];
			int x = (Integer) args[1];
			int y = (Integer) args[2];
			int z = (Integer) args[3];
			org.getspout.spoutapi.material.CustomBlock block = getCustomBlock(world, x, y, z);
			if (block != null) {
				block.onBlockDestroyed(world.getWorld(), x, y, z);
			}
		} else if (method.getName().equals("doPhysics")) {
			World world = (World) args[0];
			int x = (Integer) args[1];
			int y = (Integer) args[2];
			int z = (Integer) args[3];
			int face = (Integer) args[4];
			org.getspout.spoutapi.material.CustomBlock block = getCustomBlock(world, x, y, z);
			if (block != null) {
				block.onNeighborBlockChange(world.getWorld(), x, y, z, face);
			}
		}
			return m.invoke(wrapped, args);
	}
}

