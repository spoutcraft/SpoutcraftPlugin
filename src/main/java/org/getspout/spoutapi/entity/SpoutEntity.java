package org.getspout.spoutapi.entity;

import java.util.List;
import org.getspout.spoutapi.entity.ai.SpoutAI;

public interface SpoutEntity {
	
	public void addTask(SpoutAI task);
	
	public List<SpoutAI> getTasks();
	
}
