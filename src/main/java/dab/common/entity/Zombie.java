package dab.common.entity;

/**
 * This class is a slow moving enemy that will track down the player
 * and try to eat them.
 * 
 * @author Eli Irvin
 *
 */
public class Zombie extends Enemy
{
	private static final long serialVersionUID = -6117733551372414411L;
	
	/**
	 * Default Constructor for the Zombie
	 */
	public Zombie()
	{
		super();
		
		setClassName("dab:enemy:zombie");
		setMovementSpeedModifier(0.5f);
		setWidth(0.7f);
		setHeight(1.0f);
	}
}
