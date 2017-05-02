package dab.server.logic.components;

import java.util.Iterator;

import dab.common.entity.Enemy;
import dab.common.logic.Component;
import dab.server.Server;
import dab.server.entity.enemy.EnemyBehaviorFactory;

public class EnemyBehavior implements Component
{
	@Override
	public void invoke()
	{
		Iterator<Enemy> enemies = Server.getEnemyList().getEnemyIterator();
		while (enemies.hasNext())
		{
			executeBehavior(enemies.next());
		}
	}
	
	private void executeBehavior(Enemy enemy)
	{
		EnemyBehaviorFactory.get(enemy.getClassName()).execBehavior(enemy);
	}
}
