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
package org.getspout.spoutapi.material.item;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import org.getspout.spoutapi.io.SpoutInputStream;
import org.getspout.spoutapi.io.SpoutOutputStream;
import org.getspout.spoutapi.material.Food;
import org.getspout.spoutapi.packet.PacketType;
import org.getspout.spoutapi.Spout;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.material.CustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;
import org.getspout.spoutapi.sound.SoundEffect;

public class GenericCustomFood extends GenericCustomItem implements Food {
	private int hunger;
	private Float duration = 1.0F;
	private SoundEffect sound = SoundEffect.BURP;
	protected static HashMap<String, Integer> players = new HashMap<String, Integer>();

	public GenericCustomFood(Plugin plugin, String name, String texture, int hungerRestored) {
		super(plugin, name, texture);
		hunger = hungerRestored;
	}
	
	public GenericCustomFood(Plugin plugin, String name, String texture, int hungerRestored, Float duration) {
		super(plugin, name, texture, hungerRestored);
		this.duration = duration;
	}

	@Override
	public int getHungerRestored() {
		return hunger;
	}

	@Override
	public void readData(SpoutInputStream input) throws IOException {
		super.readData(input);
		hunger = input.read();
	}

	@Override
	public void writeData(SpoutOutputStream output) throws IOException {
		super.writeData(output);
		output.write(getHungerRestored());
	}

	@Override
	public PacketType getPacketType() {
		return PacketType.PacketCustomFood;
	}
	
	public void setEatDuration(Float duration) {
		this.duration = duration;
	}
	
	public Float getEatDuration() {
		return duration;
	}
	
	public void setSound(SoundEffect sound) {
		this.sound = sound;
	}
	
	public SoundEffect getSound() {
		return sound;
	}

	public boolean onEaten(SpoutPlayer p) {
		return true;
	}
	
	
	protected static void onTick() {
		for(String name : players.keySet()) {
			if(Spout.getServer().getPlayerExact(name) == null) {
				GenericCustomFood.players.remove(name);
				return;
			}
			
			Player p = Spout.getServer().getPlayerExact(name);
			
			CustomItem ci = (CustomItem)new SpoutItemStack(p.getItemInHand()).getMaterial();
			
			if(ci instanceof GenericCustomFood) {
				GenericCustomFood gcf = (GenericCustomFood)ci;
				Integer time = TestCustomFood.players.get(name);
				
				if(time >= (gcf.getEatDuration()*40)) {
					if(gcf.onEaten((SpoutPlayer)p)) {
						if (p.getItemInHand().getAmount() == 1) {
							p.setItemInHand(new ItemStack(Material.AIR));
						}
						else {
							int amount = p.getItemInHand().getAmount();
							p.getItemInHand().setAmount(amount - 1);
						}

						p.setFoodLevel(p.getFoodLevel()+ gcf.getHungerRestored());
						SpoutManager.getSoundManager().playGlobalSoundEffect(tcf.getSound(), p.getLocation(), 10, 98);
						
						GenericCustomFood.players.remove(name);
					}
				}
				else {
					GenericCustomFood.players.put(name, time+1);
				}
			  }
			  else {
				  GenericCustomFood.players.remove(name);
			  }
		}
	}
}
