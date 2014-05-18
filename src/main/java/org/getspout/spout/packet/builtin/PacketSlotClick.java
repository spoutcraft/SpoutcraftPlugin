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
package org.getspout.spout.packet.builtin;

import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.event.slot.SlotEvent;
import org.getspout.spoutapi.event.slot.SlotExchangeEvent;
import org.getspout.spoutapi.event.slot.SlotPutEvent;
import org.getspout.spoutapi.event.slot.SlotShiftClickEvent;
import org.getspout.spoutapi.event.slot.SlotTakeEvent;
import org.getspout.spoutapi.gui.InGameHUD;
import org.getspout.spoutapi.gui.PopupScreen;
import org.getspout.spoutapi.gui.Screen;
import org.getspout.spoutapi.gui.Slot;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.packet.SpoutPacket;
import org.getspout.spoutapi.player.SpoutPlayer;

public class PacketSlotClick extends SpoutPacket {
	private UUID screen;
	private UUID slot;
	private int button;
	private boolean holdingShift;

	public PacketSlotClick() {
	}

	public PacketSlotClick(Slot slot, int button, boolean holdingShift) {
		screen = slot.getScreen().getId();
		this.slot = slot.getId();
		this.button = button;
		this.holdingShift = holdingShift;
	}

	public void readData(SpoutInputStream input) throws IOException {
		long msb = input.readLong();
		long lsb = input.readLong();
		screen = new UUID(msb, lsb);
		msb = input.readLong();
		lsb = input.readLong();
		slot = new UUID(msb, lsb);
		button = input.read();
		holdingShift = input.readBoolean();
	}

	public void writeData(SpoutOutputStream output) throws IOException {
		output.writeLong(screen.getMostSignificantBits());
		output.writeLong(screen.getLeastSignificantBits()); // 16
		output.writeLong(slot.getMostSignificantBits());
		output.writeLong(slot.getLeastSignificantBits()); // 32
		output.write(button); // mouseClick will usually be 0 (left) or 1 (right) - so this is safe unless the mouse has... 257 buttons :P
		output.writeBoolean(holdingShift);//34
	}

	public int getNumBytes() {
		return 34;
	}

	public void run(int playerId) {
		SpoutPlayer p = SpoutManager.getPlayerFromId(playerId);
		InGameHUD mainScreen = p.getMainScreen();
		PopupScreen popup = mainScreen.getActivePopup();
		Screen current = p.getCurrentScreen();

		Screen in = null;
		if (mainScreen != null && screen.equals(mainScreen.getId())) {
			in = mainScreen;
		}
		if (popup != null && screen.equals(popup.getId())) {
			in = popup;
		}
		if (current != null && screen.equals(current.getId())) {
			in = current;
		}
		if (in == null) {
			return;
		}
		if (!in.containsWidget(slot)) {
			return;
		}

		// Slot handling code goes here.
		Slot slot = (Slot) in.getWidget(this.slot);
		try {
			ItemStack stackOnCursor = p.getItemOnCursor();
			if (stackOnCursor == null) {
				stackOnCursor = new ItemStack(0);
			}
			ItemStack stackInSlot = slot.getItem();
			if ((stackOnCursor == null || stackOnCursor.getTypeId() == 0) && stackInSlot.getTypeId() == 0) {
				return; // Nothing to do
			}
			if (stackOnCursor.getTypeId() == 0 && stackInSlot.getTypeId() != 0 && button == 1) { // Split item
				int amountSlot = stackInSlot.getAmount() / 2;
				int amountCursor = stackInSlot.getAmount() - amountSlot;
				if (stackInSlot.getAmount() == 1) {
					amountSlot = 0;
					amountCursor = 1;
				}
				stackOnCursor = stackInSlot.clone();
				stackOnCursor.setAmount(amountCursor);
				stackInSlot.setAmount(amountSlot);
				if (amountSlot == 0) {
					stackInSlot = new ItemStack(0);
				}
				SlotEvent s = new SlotTakeEvent(p, slot, stackInSlot, !slot.onItemTake(stackOnCursor));
				Bukkit.getPluginManager().callEvent(s);
				if (!s.isCancelled()) {
					slot.setItem(stackInSlot);
				} else {
					slot.setDirty(true); // We need to tell the client that the operation was denied.
					return;
				}
			} else if (stackOnCursor != null && (stackInSlot.getTypeId() == 0 || (stackInSlot.getTypeId() == stackOnCursor.getTypeId() && stackInSlot.getDurability() == stackOnCursor.getDurability()))) { // Put item
				ItemStack toPut = stackOnCursor.clone();
				int putAmount = toPut.getAmount();
				if (button == 1) {
					putAmount = 1;
				}
				int amount = stackInSlot.getTypeId() == 0 ? 0 : stackInSlot.getAmount();
				amount += putAmount;
				int maxStackSize = toPut.getMaxStackSize();
				if (maxStackSize == -1) {
					maxStackSize = 64;
				}
				if (amount > maxStackSize) {
					putAmount -= amount - maxStackSize;
					amount = maxStackSize;
				}
				if (putAmount <= 0) {
					return;
				}
				toPut.setAmount(putAmount);
				SlotEvent s = new SlotPutEvent(p, slot, stackInSlot, !slot.onItemPut(toPut));
				Bukkit.getPluginManager().callEvent(s);
				if (!s.isCancelled()) {
					stackOnCursor.setAmount(stackOnCursor.getAmount() - putAmount);
					if (stackOnCursor.getAmount() == 0) {
						stackOnCursor = new ItemStack(0);
					}
					ItemStack put = toPut.clone();
					put.setAmount(amount);
					slot.setItem(put);
				} else {
					slot.setDirty(true); // We need to tell the client that the operation was denied.
				}
			} else if (stackOnCursor == null || stackOnCursor.getTypeId() == 0) { //Take item or shift click
				if (holdingShift) {
					slot.onItemShiftClicked();
					SlotEvent s = new SlotShiftClickEvent(p, slot);
					Bukkit.getPluginManager().callEvent(s);
				} else { // Take item
					SlotEvent s = new SlotTakeEvent(p, slot, stackInSlot, !slot.onItemTake(stackInSlot));
					Bukkit.getPluginManager().callEvent(s);
					if (!s.isCancelled()) {
						stackOnCursor = stackInSlot;
						slot.setItem(new ItemStack(0));
					} else {
						slot.setDirty(true); // We need to tell the client that the operation was denied.
					}
				}
			} else if (stackOnCursor.getTypeId() != stackInSlot.getTypeId() || stackOnCursor.getDurability() != stackInSlot.getDurability()) { // Exchange slot stack and cursor stack
				SlotEvent s = new SlotExchangeEvent(p, slot, stackInSlot, stackOnCursor.clone(), !slot.onItemExchange(stackInSlot, stackOnCursor.clone()));
				Bukkit.getPluginManager().callEvent(s);
				if (!s.isCancelled()) {
					slot.setItem(stackOnCursor.clone());
					stackOnCursor = stackInSlot;
				} else {
					slot.setDirty(true); // We need to tell the client that the operation was denied.
				}
			}

			if (stackOnCursor == null || stackOnCursor.getTypeId() == 0) {
				p.setItemOnCursor(null);
			} else {
				p.setItemOnCursor(stackOnCursor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void failure(int playerId) {
	}

	public PacketType getPacketType() {
		return PacketType.PacketSlotClick;
	}

	public int getVersion() {
		return 1;
	}
}
