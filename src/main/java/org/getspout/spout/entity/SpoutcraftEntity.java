/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.getspout.spout.entity;

import java.util.List;
import org.getspout.spoutapi.entity.SpoutEntity;
import org.getspout.spoutapi.entity.ai.SpoutAI;

/**
 *
 * @author ZNickq
 */
public class SpoutcraftEntity implements SpoutEntity{
	private EntityBase base;
	
	private SpoutcraftEntity(EntityBase base) {
		this.base = base;
	}

	@Override
	public void addTask(SpoutAI task) {
		base.addTask(task);
	}

	@Override
	public List<SpoutAI> getTasks() {
		return base.getTasks();
	}
	
}
