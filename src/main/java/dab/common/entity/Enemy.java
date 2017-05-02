package dab.common.entity;

/**
 * Superclass for the enemies of the game. Works just like an entity,
 * but also contains a className object to differentiate between
 * enemies.
 * 
 * @author Eli Irvin
 */
public class Enemy extends Entity
{
	private static final long serialVersionUID = 5028909663469024138L;
	private String className; // zombie, spider, etc
	
	/**
	 * Default constructor for an Enemy
	 */
	public Enemy()
	{
		super();
		setClassName("");
	}
	
	/**
	 * Gets the className for an Enemy
	 * 
	 * @return
	 */
	public String getClassName()
	{
		return className;
	}
	
	/**
	 * Sets the className for an Enemy
	 * 
	 * @param className - the class name for the Enemy
	 */
	public void setClassName(String className)
	{
		this.className = className;
	}
}
