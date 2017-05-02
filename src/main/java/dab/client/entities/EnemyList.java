package dab.client.entities;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import dab.common.entity.Enemy;

/**
 *  This class is for managing a list of
 *  Enemy Objects and classes that derive an Enemy.
 *  It can be used to add, remove, and get an Enemy as
 *  well as methods to get Collections and/or iterators
 */
public class EnemyList
{
	private Map<String, Enemy> enemies;
	
	/**
	 * Default constructor
	 * 
	 * initializes enemies map container
	 */
	public EnemyList()
	{
		enemies = new TreeMap<String, Enemy>();
	}
	
	/**
	 * Add an Enemy to the map keyed
	 * by the Enemy's name which is unique to itself
	 * 
	 * @param enemy
	 */
	public void add(Enemy enemy)
	{
		enemies.put(enemy.getName(), enemy);
	}
	
	/**
	 * get an Enemy in the list keyed by a Name
	 * unique to the enemy
	 * 
	 * @param name
	 * 
	 * @return an Enemy Object if the name is in the list
	 *         or null otherwise
	 */
	public Enemy get(String name)
	{
		return enemies.get(name);
	}
	
	/**
	 * remove an enemy keyed by name from the list
	 * if found in the list
	 * 
	 * @param name
	 */
	public void remove(String name)
	{
		enemies.remove(name);
	}
	
	/**
	 * function to determine whether an Enemy object is stored
	 * in enemies list or not
	 * 
	 * @param enemy
	 * 
	 * @return true if enemy is found in the list
	 *         false if enemy is not found
	 */
	public boolean hasEnemy(Enemy enemy)
	{
		return enemies.get(enemy.getName()) != null;
	}
	
	/**
	 * function to determine whether an Enemy object is stored
	 * in enemies list or not
	 * 
	 * @param name
	 * 
	 * @return true if enemy is found in the list
	 *         false if enemy is not found
	 */
	public boolean hasEnemy(String name)
	{
		return enemies.get(name) != null;
	}
	
	/**
	 * get a Collection that holds the names of the Enemy stored
	 * in the list
	 * 
	 * @return Collection of Enemy names
	 */
	public Collection<String> getEnemyNameCollection()
	{
		return enemies.keySet();
	}
	
	/**
	 * get a Collection of Enemies stored in the list
	 * 
	 * @return Collection of Enemy objects
	 */
	public Collection<Enemy> getEnemyCollection()
	{
		return enemies.values();
	}
	
	/**
	 * get an Iterator to the Enemies in the list
	 * 
	 * @return iterator to enemies in the list
	 */
	public Iterator<Enemy> getEnemyIterator()
	{
		return enemies.values().iterator();
	}
	
	/**
	 * get an Iterator to the Enemies in the list
	 * 
	 * @return iterator to enemies in the list
	 */
	public Iterator<String> getEnemyNameIterator()
	{
		return enemies.keySet().iterator();
	}
}
