package dab.common.registry;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dab.common.entity.enemy.Enemy;

public class EnemyRegistry {

	private static final Map<String, Enemy> enemyList = new HashMap<String, Enemy>();
	
	public static void add(Enemy enemy) {
		enemyList.put(enemy.getName(), enemy);
	}
	
	public static void remove(Enemy enemy) {
		enemyList.remove(enemy.getName());
	}
	
	public static void remove(String name) {
		enemyList.remove(name);
	}
	
	public static Enemy get(String name) {
		return enemyList.get(name);
	}
	
	public static Collection<String> getEnemyNames() {
		return enemyList.keySet();
	}
	
	public static Collection<Enemy> getEnemies() {
		return enemyList.values();
	}
	
}