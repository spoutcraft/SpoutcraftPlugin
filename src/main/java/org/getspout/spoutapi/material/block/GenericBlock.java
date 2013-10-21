/*
 * This file is part of SpoutcraftPlugin.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
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
package org.getspout.spoutapi.material.block;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.material.Block;
import org.getspout.spoutapi.sound.SoundEffect;

public class GenericBlock implements Block {
	private final int id;
	private final int data;
	private final boolean subtypes;
	private final String name;
	private String customName;
	private SoundEffect stepSound = SoundEffect.STEP_STONE;

	private GenericBlock(String name, int id, int data, boolean subtypes) {
		this.name = name;
		this.id = id;
		this.data = data;
		this.subtypes = subtypes;
	}

	protected GenericBlock(String name, int id, int data) {
		this(name, id, data, true);
	}

	protected GenericBlock(String name, int id) {
		this(name, id, 0, false);
	}

	@Override
	public int getRawId() {
		return id;
	}

	@Override
	public int getRawData() {
		return data;
	}

	@Override
	public boolean hasSubtypes() {
		return subtypes;
	}

	@Override
	public String getName() {
		if (customName != null) {
			return customName;
		}
		return name;
	}

	@Override
	public String getNotchianName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.customName = name;
		SpoutManager.getMaterialManager().setItemName(this, name);
	}

	@Override
	public SoundEffect getStepSound() {
		return stepSound;
	}

	@Override
	public Block setStepSound(SoundEffect sound) {
		stepSound = sound;
		return this;
	}

	@Override
	public float getFriction() {
		return SpoutManager.getMaterialManager().getFriction(this);
	}

	@Override
	public Block setFriction(float friction) {
		SpoutManager.getMaterialManager().setFriction(this, friction);
		return this;
	}

	@Override
	public float getHardness() {
		return SpoutManager.getMaterialManager().getHardness(this);
	}

	@Override
	public Block setHardness(float hardness) {
		SpoutManager.getMaterialManager().setHardness(this, hardness);
		return this;
	}

	@Override
	public boolean isOpaque() {
		SpoutManager.getMaterialManager().isOpaque(this);
		return false;
	}

	@Override
	public Block setOpaque(boolean opaque) {
		SpoutManager.getMaterialManager().setOpaque(this, opaque);
		return this;
	}

	@Override
	public int getLightLevel() {
		return SpoutManager.getMaterialManager().getLightLevel(this);
	}

	@Override
	public Block setLightLevel(int level) {
		SpoutManager.getMaterialManager().setLightLevel(this, level);
		return this;
	}
}
