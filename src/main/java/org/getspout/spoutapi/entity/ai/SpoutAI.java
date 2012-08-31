package org.getspout.spoutapi.entity.ai;

public interface SpoutAI {

	public boolean shouldExecute();
	
	public boolean continueExecuting();
	
	public boolean isContinuous();
	
	public void startExecuting();
	
	public void resetTask();
	
	public void updateTask();
	
}
