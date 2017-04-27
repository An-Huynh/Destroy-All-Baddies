package dab.common.entity.enemy;

import dab.common.entity.player.Player;

public abstract class Enemy extends Player {
	

	private static final long serialVersionUID = -5378842369168272555L;
	
	private String className;
	
	public Enemy() {
		super();
	}
	
	public void setClassName(String name) {
		className = name;
	}
	
	public String getClassName() {
		return className;
	}

}