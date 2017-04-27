package dab.common.entity.enemy;

import org.joml.Vector2f;

import dab.common.entity.attribute.JumpState;

public class Zombie extends Enemy {
	
	private static final long serialVersionUID = -7219779075230562497L;

	public Zombie(String name) {
		super();
		
		setClassName("zombie");
		setName(name);
		setZone("dab:zone:start");
		setLocation(new Vector2f(5.0f, 5.0f));
		setJumpState(JumpState.GROUND);
		setHeight(1.0f);
		setWidth(1.0f);
	}
	
}
