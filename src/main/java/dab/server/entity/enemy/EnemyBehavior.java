package dab.server.entity.enemy;

import dab.common.entity.Enemy;

/**
 * This interface is used to have a base class of enemy
 * behaviors so each individual enemy can have its own
 * behavior while still allowing for a collection
 * of all behaviors.
 * 
 * @author Eli Irvin
 */
public interface EnemyBehavior
{
	/**
	 * Executes the behavior of the Enemy
	 * 
	 * @param enemy - the Enemy whose behavior will be executed
	 */
	public void execBehavior(Enemy enemy);
}
