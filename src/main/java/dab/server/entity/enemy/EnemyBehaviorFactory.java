package dab.server.entity.enemy;

public class EnemyBehaviorFactory
{
	public static ZombieBehavior zombieBehavior = new ZombieBehavior();
	
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
