package dab.server.entity.enemy;

/**
 * This class contains an instance of the different EnemyBehaviors
 * into a single class
 * 
 * @author Eli Irvin
 */
public class EnemyBehaviorFactory
{
	public static ZombieBehavior zombieBehavior = new ZombieBehavior();
	
	/**
	 * Gets the EnemyBehavior based on the enemyClassName
	 * 
	 * @param enemyClassName - the name of the enemy whose behavior will be gotten
	 * 
	 * @return EnemyBehavior of the className or NULL if the class does not exist
	 */
	public static EnemyBehavior get(String enemyClassName)
	{
		if ("dab:enemy:zombie".equals(enemyClassName))
		{
			return zombieBehavior;
		}
		else
		{
			return null;
		}
	}
}
