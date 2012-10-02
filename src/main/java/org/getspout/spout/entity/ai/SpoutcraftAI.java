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
package org.getspout.spout.entity.ai;

import net.minecraft.server.PathfinderGoal;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.entity.ai.SpoutAI;

public class SpoutcraftAI extends PathfinderGoal {
	private SpoutAI ai;

	public SpoutcraftAI(SpoutAI base) {
		this.ai = base;
		SpoutManager.getBiomeManager();
	}

	@Override
	public boolean a() {
		return ai.shouldExecute();
	}

	@Override
	public boolean b() {
		return ai.continueExecuting();
	}

	@Override
	public void e() {
		ai.startExecuting();
	}

	@Override
	public void c() {
		ai.resetTask();
	}

	@Override
	public void d() {
		ai.updateTask();
	}

	@Override
	public boolean g() {
		return ai.isContinuous();
	}

	@Override
	public void a(int i) {
		super.a(i);
	}

	@Override
	public int h() {
		return super.h();
	}
}
