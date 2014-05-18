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
package org.getspout.spoutapi.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import org.getspout.spout.packet.builtin.PacketParticle;
import org.getspout.spoutapi.player.SpoutPlayer;

public class Particle {
	private String name;
	private Location location;
	private Vector motion;
	private int maxAge;
	private double range = 16;
	private float scale;
	private float gravity = 0F;
	private float particleRed = -1F;
	private float particleBlue = -1F;
	private float particleGreen = -1F;
	private int amount = 10;

	public Particle(ParticleType particle, Location location, Vector motion) {
		this(particle.name, location, motion);
	}

	public Particle(String particle, Location location, Vector motion) {
		this.setName(particle);
		this.setLocation(location);
		this.setMotion(motion);
		setMaxAge((new Random()).nextInt(100) + 100);
		setScale((new Random().nextFloat() * 0.5F + 0.5F) * 2.0F);
	}

	/**
	 * Gets the name of this type of particle
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this type of particle
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the spawn location for this particle
	 * @return particle
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the spawn location for this particle
	 * @param location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Gets the direction of motion for this particle
	 * @return motion
	 */
	public Vector getMotion() {
		return motion;
	}

	/**
	 * Sets the direction of motion for this particle
	 * @param motion to set
	 */
	public Particle setMotion(Vector motion) {
		this.motion = motion;
		return this;
	}

	/**
	 * Get the maximum age (in ticks) that this particle will last
	 * @return age
	 */
	public int getMaxAge() {
		return maxAge;
	}

	/**
	 * Sets the maximum age (in ticks) that the particle will last
	 * @param maxAge
	 */
	public Particle setMaxAge(int maxAge) {
		this.maxAge = maxAge;
		return this;
	}

	/**
	 * Gets the red color for the particle, or -1 if it uses the default color. Values should be (0-1)
	 * @return color
	 */
	public float getParticleRed() {
		return particleRed;
	}

	/**
	 * Sets the red color for the particle, or -1 if it uses the default color. Values should be (0-1)
	 * @param particleRed
	 */
	public Particle setParticleRed(float particleRed) {
		this.particleRed = particleRed;
		return this;
	}

	/**
	 * Gets the blue color for the particle, or -1 if it uses the default color. Values should be (0-1)
	 * @return color
	 */
	public float getParticleBlue() {
		return particleBlue;
	}

	/**
	 * Sets the blue color for the particle, or -1 if it uses the default color. Values should be (0-1)
	 * @return color
	 */
	public Particle setParticleBlue(float particleBlue) {
		this.particleBlue = particleBlue;
		return this;
	}

	/**
	 * Gets the gree color for the particle, or -1 if it uses the default color. Values should be (0-1)
	 * @return color
	 */
	public float getParticleGreen() {
		return particleGreen;
	}

	/**
	 * Gets the green color for the particle, or -1 if it uses the default color. Values should be (0-1)
	 * @return color
	 */
	public Particle setParticleGreen(float particleGreen) {
		this.particleGreen = particleGreen;
		return this;
	}

	/**
	 * Gets the scale of the particle
	 * @return scale
	 */
	public float getScale() {
		return scale;
	}

	/**
	 * Sets the scale of the particle
	 * @param scale
	 */
	public Particle setScale(float scale) {
		this.scale = scale;
		return this;
	}

	/**
	 * Gets the gravity modifier for the particle (0 = no gravity)
	 * @return gravity
	 */
	public float getGravity() {
		return gravity;
	}

	/**
	 * Sets the gravity modifier for the particle (0 = no gravity)
	 * @param gravity
	 */
	public Particle setGravity(float gravity) {
		this.gravity = gravity;
		return this;
	}

	/**
	 * Spawns this particle - making it visible to the given player
	 * @param player to spawn the particle for
	 */
	public void spawn(SpoutPlayer player) {
		if (player.isSpoutCraftEnabled() && player.getLocation().distance(location) <= range) {
			player.sendPacket(new PacketParticle(this));
		}
	}

	/**
	 * Spawns this particle in the world, making it visible to all players
	 */
	public void spawn() {
		if (location != null && name != null) {
			for (Player p : location.getWorld().getPlayers()) {
				if (p instanceof SpoutPlayer) {
					spawn(((SpoutPlayer) p));
				}
			}
		}
	}

	public int getAmount() {
		return amount;
	}

	public Particle setAmount(int amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Gets the range this particle will be visible at
	 * @return range
	 */
	public double getRange() {
		return range;
	}

	/**
	 * Sets the range this particle will be visible at
	 * Client graphics settings may override this
	 * @param range
	 */
	public Particle setRange(double range) {
		this.range = range;
		return this;
	}

	public enum ParticleType {
		BUBBLE("bubble"),
		SUSPENDED("suspended"),
		DEPTHSUSPEND("depthsuspend"),
		TOWNAURA("townaura"),
		CRIT("crit"),
		MAGICCRIT("magicCrit"),
		SMOKE("smoke"),
		MOBSPELL("mobSpell"),
		SPELL("spell"),
		NOTE("note"),
		PORTAL("portal"),
		ENCHANTMENTTABLE("enchantmenttable"),
		EXPLODE("explode"),
		FLAME("flame"),
		LAVA("lava"),
		FOOTSTEP("footstep"),
		SPLASH("splash"),
		LARGESMOKE("largesmoke"),
		CLOUD("cloud"),
		REDDUST("reddust"),
		SNOWBALLPOOF("snowballpoof"),
		DRIPWATER("dripWater"),
		DRIPLAVA("dripLava"),
		SNOWSHOVEL("snowshovel"),
		SLIME("slime"),
		HEART("heart"),
		UNKNOWN(null),;
		final String name;

		ParticleType(final String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public static ParticleType getTypeFromName(String name) {
			for (ParticleType type : ParticleType.values()) {
				if (type.name != null && type.name.equals(name)) {
					return type;
				}
			}
			return UNKNOWN;
		}
	}
}
