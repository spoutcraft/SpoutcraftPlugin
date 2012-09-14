/*
 * This file is part of SpoutPlugin.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
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
package org.getspout.spoutapi.material.item;

import java.io.IOException;

import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.material.Weapon;
import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.packet.PacketType;

public class GenericCustomWeapon extends GenericCustomItem implements Weapon {

	private int damage;
	private int attackSpeed;
	private boolean blockFlag;
	private int durability;
	
	public GenericCustomWeapon(Plugin plugin, String name, String texture) {
		super(plugin, name, texture);
	}
	
	@Override
	public Tool setMaxDurability(short durability) {
		this.durability = durability;
		return this;
	}
	
	@Override
	public short getMaxDurability() {
		return durability;
	}

	@Override
	public int getDamage() {
		return damage;
	}
	
	@Override
	public Weapon setDamage(int damage) {
		this.damage = damage;
		return this;
	}
	
	@Override
	public int getAttackSpeed() {
		return attackSpeed;
	}
	
	@Override
	public Weapon setAttackSpeed(int speed) {
		attackSpeed = speed;
		return this;
	}
	
	@Override
	public boolean isBlockFlag() {
		return canBlock;
	}
	
	@Override
	public Weapon setBlockFlag(boolean canBlock) {
		blockFlag = canBlock;
		return this;
	}
	
	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		setMaxDurability(input.readShort());
		setDamage(input.readInt());
		setAttackSpeed(input.readInt());
		setBlockFlag(input.readBoolean());
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		
		output.writeShort(durability);
		output.writeInt(damage);
		output.writeInt(attackSpeed);
		output.writeBoolean(blockFlag);
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomWeapon;
	}

	@Override
	public int getVersion() {
		return super.getVersion() + 0;
	}
}
