package dab.common.entity;

public class Zombie extends Enemy
{
	private static final long serialVersionUID = -6117733551372414411L;
	
	public Zombie()
	{
		super();
		
		setClassName("dab:enemy:zombie");
		setMovementSpeedModifier(0.5f);
		setWidth(0.7f);
		setHeight(1.0f);
	}
}
