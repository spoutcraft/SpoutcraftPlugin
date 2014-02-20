/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 SpoutcraftDev <http://spoutcraft.org//>
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
package org.getspout.spoutapi.packet.standard;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

/**
 * Represents Packet103SetSlot
 */
public interface MCPacket103SetSlot extends MCPacket {
	/**
	 * Indicates the Window identified by the packet
	 */
	public enum Window {
		UNKNOWN(-1),
		PLAYER(0),
		WORKBENCH(1),
		FURNACE(2),
		CHEST(3),
		DOUBLECHEST(4),
		DISPENSER(5);
		/**
		 * The raw value contained in the packet.
		 */
		public final int id;

		private Window(int id) {
			this.id = id;
		}

		/**
		 * Gets a Window enum that represents the raw id.
		 * @param id the raw id to get an enum value for
		 * @return a Window enum value that represents the raw id
		 */
		public static Window getWindowById(int id) {
			switch (id) {
				case 0:
					return PLAYER;
				case 1:
					return WORKBENCH;
				case 2:
					return FURNACE;
				case 3:
					return CHEST;
				case 4:
					return DOUBLECHEST;
				case 5:
					return DISPENSER;
				default:
					return UNKNOWN;
			}
		}
	}

	/**
	 * Gives a name to the window/slot pair contained in the packet.
	 */
	public enum Slot {
		UNKNOWN(-1, 0),
		CURSOR(-1, -1),
		PLAYER_CRAFT_RESULT(0, 0),
		PLAYER_CRAFT_INPUT_1(0, 1),
		PLAYER_CRAFT_INPUT_2(0, 2),
		PLAYER_CRAFT_INPUT_3(0, 3),
		PLAYER_CRAFT_INPUT_4(0, 4),
		PLAYER_ARMOR_HEAD(0, 5),
		PLAYER_ARMOR_CHEST(0, 6),
		PLAYER_ARMOR_LEGS(0, 7),
		PLAYER_ARMOR_FEET(0, 8),
		PLAYER_INVENTORY_01(0, 9),
		PLAYER_INVENTORY_02(0, 10),
		PLAYER_INVENTORY_03(0, 11),
		PLAYER_INVENTORY_04(0, 12),
		PLAYER_INVENTORY_05(0, 13),
		PLAYER_INVENTORY_06(0, 14),
		PLAYER_INVENTORY_07(0, 15),
		PLAYER_INVENTORY_08(0, 16),
		PLAYER_INVENTORY_09(0, 17),
		PLAYER_INVENTORY_10(0, 18),
		PLAYER_INVENTORY_11(0, 19),
		PLAYER_INVENTORY_12(0, 20),
		PLAYER_INVENTORY_13(0, 21),
		PLAYER_INVENTORY_14(0, 22),
		PLAYER_INVENTORY_15(0, 23),
		PLAYER_INVENTORY_16(0, 24),
		PLAYER_INVENTORY_17(0, 25),
		PLAYER_INVENTORY_18(0, 26),
		PLAYER_INVENTORY_19(0, 27),
		PLAYER_INVENTORY_20(0, 28),
		PLAYER_INVENTORY_21(0, 29),
		PLAYER_INVENTORY_22(0, 30),
		PLAYER_INVENTORY_23(0, 31),
		PLAYER_INVENTORY_24(0, 32),
		PLAYER_INVENTORY_25(0, 33),
		PLAYER_INVENTORY_26(0, 34),
		PLAYER_INVENTORY_27(0, 35),
		PLAYER_HELD_1(0, 36),
		PLAYER_HELD_2(0, 37),
		PLAYER_HELD_3(0, 38),
		PLAYER_HELD_4(0, 39),
		PLAYER_HELD_5(0, 40),
		PLAYER_HELD_6(0, 41),
		PLAYER_HELD_7(0, 42),
		PLAYER_HELD_8(0, 43),
		PLAYER_HELD_9(0, 44),
		WORKBENCH_CRAFT_RESULT(1, 0),
		WORKBENCH_CRAFT_INPUT_1(1, 1),
		WORKBENCH_CRAFT_INPUT_2(1, 2),
		WORKBENCH_CRAFT_INPUT_3(1, 3),
		WORKBENCH_CRAFT_INPUT_4(1, 4),
		WORKBENCH_CRAFT_INPUT_5(1, 5),
		WORKBENCH_CRAFT_INPUT_6(1, 6),
		WORKBENCH_CRAFT_INPUT_7(1, 7),
		WORKBENCH_CRAFT_INPUT_8(1, 8),
		WORKBENCH_CRAFT_INPUT_9(1, 9),
		WORKBENCH_INVENTORY_01(1, 10),
		WORKBENCH_INVENTORY_02(1, 11),
		WORKBENCH_INVENTORY_03(1, 12),
		WORKBENCH_INVENTORY_04(1, 13),
		WORKBENCH_INVENTORY_05(1, 14),
		WORKBENCH_INVENTORY_06(1, 15),
		WORKBENCH_INVENTORY_07(1, 16),
		WORKBENCH_INVENTORY_08(1, 17),
		WORKBENCH_INVENTORY_09(1, 18),
		WORKBENCH_INVENTORY_10(1, 19),
		WORKBENCH_INVENTORY_11(1, 20),
		WORKBENCH_INVENTORY_12(1, 21),
		WORKBENCH_INVENTORY_13(1, 22),
		WORKBENCH_INVENTORY_14(1, 23),
		WORKBENCH_INVENTORY_15(1, 24),
		WORKBENCH_INVENTORY_16(1, 25),
		WORKBENCH_INVENTORY_17(1, 26),
		WORKBENCH_INVENTORY_18(1, 27),
		WORKBENCH_INVENTORY_19(1, 28),
		WORKBENCH_INVENTORY_20(1, 29),
		WORKBENCH_INVENTORY_21(1, 30),
		WORKBENCH_INVENTORY_22(1, 31),
		WORKBENCH_INVENTORY_23(1, 32),
		WORKBENCH_INVENTORY_24(1, 33),
		WORKBENCH_INVENTORY_25(1, 34),
		WORKBENCH_INVENTORY_26(1, 35),
		WORKBENCH_INVENTORY_27(1, 36),
		WORKBENCH_HELD_1(1, 37),
		WORKBENCH_HELD_2(1, 38),
		WORKBENCH_HELD_3(1, 39),
		WORKBENCH_HELD_4(1, 40),
		WORKBENCH_HELD_5(1, 41),
		WORKBENCH_HELD_6(1, 42),
		WORKBENCH_HELD_7(1, 43),
		WORKBENCH_HELD_8(1, 44),
		WORKBENCH_HELD_9(1, 45),
		FURNACE_INPUT(2, 0),
		FURNACE_FUEL(2, 1),
		FURNACE_OUTPUT(2, 2),
		FURNACE_INVENTORY_01(2, 3),
		FURNACE_INVENTORY_02(2, 4),
		FURNACE_INVENTORY_03(2, 5),
		FURNACE_INVENTORY_04(2, 6),
		FURNACE_INVENTORY_05(2, 7),
		FURNACE_INVENTORY_06(2, 8),
		FURNACE_INVENTORY_07(2, 9),
		FURNACE_INVENTORY_08(2, 10),
		FURNACE_INVENTORY_09(2, 11),
		FURNACE_INVENTORY_10(2, 12),
		FURNACE_INVENTORY_11(2, 13),
		FURNACE_INVENTORY_12(2, 14),
		FURNACE_INVENTORY_13(2, 15),
		FURNACE_INVENTORY_14(2, 16),
		FURNACE_INVENTORY_15(2, 17),
		FURNACE_INVENTORY_16(2, 18),
		FURNACE_INVENTORY_17(2, 19),
		FURNACE_INVENTORY_18(2, 20),
		FURNACE_INVENTORY_19(2, 21),
		FURNACE_INVENTORY_20(2, 22),
		FURNACE_INVENTORY_21(2, 23),
		FURNACE_INVENTORY_22(2, 24),
		FURNACE_INVENTORY_23(2, 25),
		FURNACE_INVENTORY_24(2, 26),
		FURNACE_INVENTORY_25(2, 27),
		FURNACE_INVENTORY_26(2, 28),
		FURNACE_INVENTORY_27(2, 29),
		FURNACE_HELD_1(2, 30),
		FURNACE_HELD_2(2, 31),
		FURNACE_HELD_3(2, 32),
		FURNACE_HELD_4(2, 33),
		FURNACE_HELD_5(2, 34),
		FURNACE_HELD_6(2, 35),
		FURNACE_HELD_7(2, 36),
		FURNACE_HELD_8(2, 37),
		FURNACE_HELD_9(2, 38),
		CHEST_CONTENTS_01(3, 0),
		CHEST_CONTENTS_02(3, 1),
		CHEST_CONTENTS_03(3, 2),
		CHEST_CONTENTS_04(3, 3),
		CHEST_CONTENTS_05(3, 4),
		CHEST_CONTENTS_06(3, 5),
		CHEST_CONTENTS_07(3, 6),
		CHEST_CONTENTS_08(3, 7),
		CHEST_CONTENTS_09(3, 8),
		CHEST_CONTENTS_10(3, 9),
		CHEST_CONTENTS_11(3, 10),
		CHEST_CONTENTS_12(3, 11),
		CHEST_CONTENTS_13(3, 12),
		CHEST_CONTENTS_14(3, 13),
		CHEST_CONTENTS_15(3, 14),
		CHEST_CONTENTS_16(3, 15),
		CHEST_CONTENTS_17(3, 16),
		CHEST_CONTENTS_18(3, 17),
		CHEST_CONTENTS_19(3, 18),
		CHEST_CONTENTS_20(3, 19),
		CHEST_CONTENTS_21(3, 20),
		CHEST_CONTENTS_22(3, 21),
		CHEST_CONTENTS_23(3, 22),
		CHEST_CONTENTS_24(3, 23),
		CHEST_CONTENTS_25(3, 24),
		CHEST_CONTENTS_26(3, 25),
		CHEST_CONTENTS_27(3, 26),
		CHEST_INVENTORY_01(3, 27),
		CHEST_INVENTORY_02(3, 28),
		CHEST_INVENTORY_03(3, 29),
		CHEST_INVENTORY_04(3, 30),
		CHEST_INVENTORY_05(3, 31),
		CHEST_INVENTORY_06(3, 32),
		CHEST_INVENTORY_07(3, 33),
		CHEST_INVENTORY_08(3, 34),
		CHEST_INVENTORY_09(3, 35),
		CHEST_INVENTORY_10(3, 36),
		CHEST_INVENTORY_11(3, 37),
		CHEST_INVENTORY_12(3, 38),
		CHEST_INVENTORY_13(3, 39),
		CHEST_INVENTORY_14(3, 40),
		CHEST_INVENTORY_15(3, 41),
		CHEST_INVENTORY_16(3, 42),
		CHEST_INVENTORY_17(3, 43),
		CHEST_INVENTORY_18(3, 44),
		CHEST_INVENTORY_19(3, 45),
		CHEST_INVENTORY_20(3, 46),
		CHEST_INVENTORY_21(3, 47),
		CHEST_INVENTORY_22(3, 48),
		CHEST_INVENTORY_23(3, 49),
		CHEST_INVENTORY_24(3, 50),
		CHEST_INVENTORY_25(3, 51),
		CHEST_INVENTORY_26(3, 52),
		CHEST_INVENTORY_27(3, 53),
		CHEST_HELD_1(3, 54),
		CHEST_HELD_2(3, 55),
		CHEST_HELD_3(3, 56),
		CHEST_HELD_4(3, 57),
		CHEST_HELD_5(3, 58),
		CHEST_HELD_6(3, 59),
		CHEST_HELD_7(3, 60),
		CHEST_HELD_8(3, 61),
		CHEST_HELD_9(3, 62),
		DOUBLECHEST_CONTENTS_01(4, 0),
		DOUBLECHEST_CONTENTS_02(4, 1),
		DOUBLECHEST_CONTENTS_03(4, 2),
		DOUBLECHEST_CONTENTS_04(4, 3),
		DOUBLECHEST_CONTENTS_05(4, 4),
		DOUBLECHEST_CONTENTS_06(4, 5),
		DOUBLECHEST_CONTENTS_07(4, 6),
		DOUBLECHEST_CONTENTS_08(4, 7),
		DOUBLECHEST_CONTENTS_09(4, 8),
		DOUBLECHEST_CONTENTS_10(4, 9),
		DOUBLECHEST_CONTENTS_11(4, 10),
		DOUBLECHEST_CONTENTS_12(4, 11),
		DOUBLECHEST_CONTENTS_13(4, 12),
		DOUBLECHEST_CONTENTS_14(4, 13),
		DOUBLECHEST_CONTENTS_15(4, 14),
		DOUBLECHEST_CONTENTS_16(4, 15),
		DOUBLECHEST_CONTENTS_17(4, 16),
		DOUBLECHEST_CONTENTS_18(4, 17),
		DOUBLECHEST_CONTENTS_19(4, 18),
		DOUBLECHEST_CONTENTS_20(4, 19),
		DOUBLECHEST_CONTENTS_21(4, 20),
		DOUBLECHEST_CONTENTS_22(4, 21),
		DOUBLECHEST_CONTENTS_23(4, 22),
		DOUBLECHEST_CONTENTS_24(4, 23),
		DOUBLECHEST_CONTENTS_25(4, 24),
		DOUBLECHEST_CONTENTS_26(4, 25),
		DOUBLECHEST_CONTENTS_27(4, 26),
		DOUBLECHEST_CONTENTS_28(4, 27),
		DOUBLECHEST_CONTENTS_29(4, 28),
		DOUBLECHEST_CONTENTS_30(4, 29),
		DOUBLECHEST_CONTENTS_31(4, 30),
		DOUBLECHEST_CONTENTS_32(4, 31),
		DOUBLECHEST_CONTENTS_33(4, 32),
		DOUBLECHEST_CONTENTS_34(4, 33),
		DOUBLECHEST_CONTENTS_35(4, 34),
		DOUBLECHEST_CONTENTS_36(4, 35),
		DOUBLECHEST_CONTENTS_37(4, 36),
		DOUBLECHEST_CONTENTS_38(4, 37),
		DOUBLECHEST_CONTENTS_39(4, 38),
		DOUBLECHEST_CONTENTS_40(4, 39),
		DOUBLECHEST_CONTENTS_41(4, 40),
		DOUBLECHEST_CONTENTS_42(4, 41),
		DOUBLECHEST_CONTENTS_43(4, 42),
		DOUBLECHEST_CONTENTS_44(4, 43),
		DOUBLECHEST_CONTENTS_45(4, 44),
		DOUBLECHEST_CONTENTS_46(4, 45),
		DOUBLECHEST_CONTENTS_47(4, 46),
		DOUBLECHEST_CONTENTS_48(4, 47),
		DOUBLECHEST_CONTENTS_49(4, 48),
		DOUBLECHEST_CONTENTS_50(4, 49),
		DOUBLECHEST_CONTENTS_51(4, 50),
		DOUBLECHEST_CONTENTS_52(4, 51),
		DOUBLECHEST_CONTENTS_53(4, 52),
		DOUBLECHEST_CONTENTS_54(4, 53),
		DOUBLECHEST_INVENTORY_01(4, 54),
		DOUBLECHEST_INVENTORY_02(4, 55),
		DOUBLECHEST_INVENTORY_03(4, 56),
		DOUBLECHEST_INVENTORY_04(4, 57),
		DOUBLECHEST_INVENTORY_05(4, 58),
		DOUBLECHEST_INVENTORY_06(4, 59),
		DOUBLECHEST_INVENTORY_07(4, 60),
		DOUBLECHEST_INVENTORY_08(4, 61),
		DOUBLECHEST_INVENTORY_09(4, 62),
		DOUBLECHEST_INVENTORY_10(4, 63),
		DOUBLECHEST_INVENTORY_11(4, 64),
		DOUBLECHEST_INVENTORY_12(4, 65),
		DOUBLECHEST_INVENTORY_13(4, 66),
		DOUBLECHEST_INVENTORY_14(4, 67),
		DOUBLECHEST_INVENTORY_15(4, 68),
		DOUBLECHEST_INVENTORY_16(4, 69),
		DOUBLECHEST_INVENTORY_17(4, 70),
		DOUBLECHEST_INVENTORY_18(4, 71),
		DOUBLECHEST_INVENTORY_19(4, 72),
		DOUBLECHEST_INVENTORY_20(4, 73),
		DOUBLECHEST_INVENTORY_21(4, 74),
		DOUBLECHEST_INVENTORY_22(4, 75),
		DOUBLECHEST_INVENTORY_23(4, 76),
		DOUBLECHEST_INVENTORY_24(4, 77),
		DOUBLECHEST_INVENTORY_25(4, 78),
		DOUBLECHEST_INVENTORY_26(4, 79),
		DOUBLECHEST_INVENTORY_27(4, 80),
		DOUBLECHEST_HELD_1(4, 81),
		DOUBLECHEST_HELD_2(4, 82),
		DOUBLECHEST_HELD_3(4, 83),
		DOUBLECHEST_HELD_4(4, 84),
		DOUBLECHEST_HELD_5(4, 85),
		DOUBLECHEST_HELD_6(4, 86),
		DOUBLECHEST_HELD_7(4, 87),
		DOUBLECHEST_HELD_8(4, 88),
		DOUBLECHEST_HELD_9(4, 89),
		DISPENSER_CONTENTS_1(5, 0),
		DISPENSER_CONTENTS_2(5, 1),
		DISPENSER_CONTENTS_3(5, 2),
		DISPENSER_CONTENTS_4(5, 3),
		DISPENSER_CONTENTS_5(5, 4),
		DISPENSER_CONTENTS_6(5, 5),
		DISPENSER_CONTENTS_7(5, 6),
		DISPENSER_CONTENTS_8(5, 7),
		DISPENSER_CONTENTS_9(5, 8),
		DISPENSER_INVENTORY_01(5, 9),
		DISPENSER_INVENTORY_02(5, 10),
		DISPENSER_INVENTORY_03(5, 11),
		DISPENSER_INVENTORY_04(5, 12),
		DISPENSER_INVENTORY_05(5, 13),
		DISPENSER_INVENTORY_06(5, 14),
		DISPENSER_INVENTORY_07(5, 15),
		DISPENSER_INVENTORY_08(5, 16),
		DISPENSER_INVENTORY_09(5, 17),
		DISPENSER_INVENTORY_10(5, 18),
		DISPENSER_INVENTORY_11(5, 19),
		DISPENSER_INVENTORY_12(5, 20),
		DISPENSER_INVENTORY_13(5, 21),
		DISPENSER_INVENTORY_14(5, 22),
		DISPENSER_INVENTORY_15(5, 23),
		DISPENSER_INVENTORY_16(5, 24),
		DISPENSER_INVENTORY_17(5, 25),
		DISPENSER_INVENTORY_18(5, 26),
		DISPENSER_INVENTORY_19(5, 27),
		DISPENSER_INVENTORY_20(5, 28),
		DISPENSER_INVENTORY_21(5, 29),
		DISPENSER_INVENTORY_22(5, 30),
		DISPENSER_INVENTORY_23(5, 31),
		DISPENSER_INVENTORY_24(5, 32),
		DISPENSER_INVENTORY_25(5, 33),
		DISPENSER_INVENTORY_26(5, 34),
		DISPENSER_INVENTORY_27(5, 35),
		DISPENSER_HELD_1(5, 36),
		DISPENSER_HELD_2(5, 37),
		DISPENSER_HELD_3(5, 38),
		DISPENSER_HELD_4(5, 39),
		DISPENSER_HELD_5(5, 40),
		DISPENSER_HELD_6(5, 41),
		DISPENSER_HELD_7(5, 42),
		DISPENSER_HELD_8(5, 43),
		DISPENSER_HELD_9(5, 44);
		/**
		 * The raw value contained in the packet.
		 */
		public final int rawWindowId;
		/**
		 * The raw value contained in the packet.
		 */
		public final int rawSlotId;

		private Slot(int window, int slot) {
			rawWindowId = window;
			rawSlotId = slot;
			Store(window, slot, this);
		}

		// This is a separate method because Java doesn't let enums access static fields in the constructor. Hacky work-around.
		private static void Store(int window, int slot, Slot value) {
			if (slots == null) {
				slots = new HashMap<Integer, HashMap<Integer, Slot>>();
			}
			if (!slots.containsKey(window)) {
				slots.put(window, new HashMap<Integer, Slot>());
			}
			slots.get(window).put(slot, value);
		}

		// No way I'm writing a huge switch nest to do a lookup. Use some hash tables instead.
		// This is also easier to use in the future if any more windows were added.
		private static HashMap<Integer, HashMap<Integer, Slot>> slots;

		/**
		 * Gets a Slot enum value based on the raw values in the packet.
		 * @param window the window id from the packet
		 * @param slot   the slot id from the packet
		 * @return an enum value that represents the data in the packet
		 */
		public static Slot getSlotByRawValues(int window, int slot) {
			if (!slots.containsKey(window)) {
				return UNKNOWN;
			}
			if (!slots.get(window).containsKey(slot)) {
				return UNKNOWN;
			}
			return slots.get(window).get(slot);
		}

		/**
		 * Gets the Window enum value that represents the window in the packet.
		 * @return an enum value that represents the window in the packet
		 */
		public Window getWindow() {
			return Window.getWindowById(rawWindowId);
		}

		/**
		 * Indicates whether the slot is in the player's inventory window.
		 * @return true if the slot is in the player's inventory window or false otherwise
		 */
		public boolean isPlayerInventory() {
			return rawWindowId == 0;
		}

		/**
		 * Indicates whether the slot is in a workbench window.
		 * @return true if the slot is in a workbench window or false otherwise
		 */
		public boolean isWorkbench() {
			return rawWindowId == 1;
		}

		/**
		 * Indicates whether the slot is in a furnace window.
		 * @return true if the slot is in a furnace window or false otherwise
		 */
		public boolean isFurnace() {
			return rawWindowId == 2;
		}

		/**
		 * Indicates whether the slot is in a single chest window.
		 * @return true if the slot is in a single chest window or false otherwise
		 */
		public boolean isChest() {
			return rawWindowId == 3;
		}

		/**
		 * Indicates whether the slot is in a double chest window.
		 * @return true if the slot is in a double chest window or false otherwise
		 */
		public boolean isDoubleChest() {
			return rawWindowId == 4;
		}

		/**
		 * Indicates whether the slot is in a dispenser window.
		 * @return true if the slot is in a dispenser window or false otherwise
		 */
		public boolean isDispenser() {
			return rawWindowId == 5;
		}

		/**
		 * Indicates whether the slot is in the player's inventory section of the window.
		 * @return true if the slot is in the player's inventory or false otherwise
		 */
		public boolean isInventory() {
			switch (rawWindowId) {
				case 0:
				case 5:
					return rawSlotId >= 9 && rawSlotId <= 35;
				case 1:
					return rawSlotId >= 10 && rawSlotId <= 36;
				case 2:
					return rawSlotId >= 3 && rawSlotId <= 29;
				case 3:
					return rawSlotId >= 27 && rawSlotId <= 53;
				case 4:
					return rawSlotId >= 54 && rawSlotId <= 80;
				default:
					return false;
			}
		}

		/**
		 * Indicates whether the slot is in the player's held items bar.
		 * @return true if the slot is in the held items or false otherwise
		 */
		public boolean isHeldItems() {
			switch (rawWindowId) {
				case 0:
				case 5:
					return rawSlotId >= 36 && rawSlotId <= 44;
				case 1:
					return rawSlotId >= 37 && rawSlotId <= 45;
				case 2:
					return rawSlotId >= 30 && rawSlotId <= 38;
				case 3:
					return rawSlotId >= 54 && rawSlotId <= 62;
				case 4:
					return rawSlotId >= 81 && rawSlotId <= 89;
				default:
					return false;
			}
		}

		/**
		 * Indicates whether the slot is in the player's armor bar.
		 * This is only valid for player inventory.
		 * @return true if the slot is in the armor or false otherwise
		 */
		public boolean isArmor() {
			return rawWindowId == 0 && rawSlotId >= 5 && rawSlotId <= 8;
		}

		/**
		 * Indicates whether the slot is in the contents of a container.
		 * This is only valid for chests and dispensers.
		 * @return true if the slot is in contents or false otherwise
		 */
		public boolean isContents() {
			switch (rawWindowId) {
				case 3:
					return rawSlotId >= 0 && rawSlotId <= 26;
				case 4:
					return rawSlotId >= 0 && rawSlotId <= 53;
				case 5:
					return rawSlotId >= 0 && rawSlotId <= 8;
				default:
					return false;
			}
		}

		/**
		 * Indicates whether the slot is in any crafting or furnace input.
		 * This is only valid for player inventory, workbenches, and furnaces.
		 * @return true if the slot is in crafting or furnace input or false otherwise
		 */
		public boolean isInput() {
			switch (rawWindowId) {
				case 0:
					return rawSlotId >= 1 && rawSlotId <= 4;
				case 1:
					return rawSlotId >= 1 && rawSlotId <= 9;
				case 2:
					return rawSlotId == 0 || rawSlotId == 1;
				default:
					return false;
			}
		}

		/**
		 * Indicates whether the slot is in any crafting or furnace output.
		 * This is only valid for player inventory, workbenches, and furnaces.
		 * @return
		 */
		public boolean isOutput() {
			switch (rawWindowId) {
				case 0:
				case 1:
					return rawSlotId == 0;
				case 2:
					return rawSlotId == 2;
				default:
					return false;
			}
		}
	}

	/**
	 * Get the slot that the packet indicated.
	 * @return an enum value representing the data in the packet
	 */
	public Slot getSlot();

	/**
	 * Set the slot in the packet to a new value.
	 * @param slot the new slot value
	 */
	public void setSlot(Slot slot);

	/**
	 * Get the window that the packet indicated.
	 * @return an enum value representing the data in the packet
	 */
	public Window getWindow();

	/**
	 * Get the raw slot value from the packet.
	 * @return the integer value from the packet
	 */
	public int getRawSlot();

	/**
	 * Set the slot in the packet to a new value.
	 * @param slot the new slot value
	 */
	public void setRawSlot(int slot);

	/**
	 * Get the raw window value from the packet.
	 * @return the integer value from the packet
	 */
	public int getRawWindow();

	/**
	 * Set the window in the packet to a new value.
	 * @param window the new window value
	 */
	public void setRawWindow(int window);

	/**
	 * Get the ItemStack from the packet.
	 * Can be null, representing an empty slot.
	 * @return a Bukkit ItemStack representing the value from the packet
	 */
	public ItemStack getItemStack();

	/**
	 * Set the item stack in the packet to a new value.
	 * Can be null, representing an empty slot.
	 * @param itemStack a Bukkit ItemStack representing the new value
	 */
	public void setItemStack(ItemStack itemStack);
}
