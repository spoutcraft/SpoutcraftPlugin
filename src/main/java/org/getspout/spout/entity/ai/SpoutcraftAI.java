package org.getspout.spout.entity.ai;

import net.minecraft.server.PathfinderGoal;
import org.getspout.spoutapi.entity.ai.SpoutAI;

public class SpoutcraftAI extends PathfinderGoal {
	
	private SpoutAI ai;
	
	public SpoutcraftAI(SpoutAI base) {
		this.ai = base;
	}

	@Override
	public boolean a() { //shouldExecute
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
