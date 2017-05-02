package dab.server.logic.components;

import java.util.Iterator;

import dab.common.entity.Enemy;
import dab.common.logic.Component;
import dab.server.Server;
import dab.server.entity.enemy.EnemyBehaviorFactory;

/**
 * This class is a Component that when invoked executes the
 * behavior of all enemies while also allowing the ability to
 * execute the behavior of a specific enemy.
 * 
 * @author Eli Irvin
 */
public class EnemyBehavior implements Component
{
	/**
	 * Gets an Iterator of all the enemies and then
	 * executes their behaviors.
	 */
	@Override
	public void invoke()
	{
		Iterator<Enemy> enemies = Server.getEnemyList().getEnemyIterator();
		while (enemies.hasNext())
		{
			executeBehavior(enemies.next());
		}
	}
	
	/**
	 * Executes the behaviors of an Enemy
	 * 
	 * @param enemy - the enemy whose behavior is to be executed
	 */
	private void executeBehavior(Enemy enemy)
	{
		EnemyBehaviorFactory.get(enemy.getClassName()).execBehavior(enemy);
	}
}
