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
 *  
 *  @author Eli Irvin
 */
public class EnemyList
{
	private Map<String, Enemy> enemies;
	
	/**
	 * Default constructor
	 * 
	 * Initializes an empty map for the EnemyList
	 */
	public EnemyList()
	{
		enemies = new TreeMap<String, Enemy>();
	}
	
	/**
	 * Adds an Enemy to the map keyed
	 * by the Enemy's name which is unique
	 * 
	 * @param enemy - the enemy being added
	 */
	public void add(Enemy enemy)
	{
		enemies.put(enemy.getName(), enemy);
	}
	
	/**
	 * Get an Enemy in the list keyed by a Name
	 * unique to the enemy
	 * 
	 * @param name - the name of the enemy
	 * 
	 * @return an Enemy Object if the name is in the list
	 *         or null otherwise
	 */
	public Enemy get(String name)
	{
		return enemies.get(name);
	}
	
	/**
	 * Remove an enemy keyed by name from the list
	 * if found in the list
	 * 
	 * @param name - the name of the enemy
	 */
	public void remove(String name)
	{
		enemies.remove(name);
	}
	
	/**
	 * Determines whether an Enemy object is stored
	 * in the enemies list or not
	 * 
	 * @param enemy - the enemy to search for
	 * 
	 * @return True if enemy is found in the list |
	 *         False if enemy is not found
	 */
	public boolean hasEnemy(Enemy enemy)
	{
		return enemies.get(enemy.getName()) != null;
	}
	
	/**
	 * Determines whether an Enemy object is stored
	 * in enemies list or not
	 * 
	 * @param name - the name of the enemy
	 * 
	 * @return True if enemy is found in the list |
	 *         False if enemy is not found
	 */
	public boolean hasEnemy(String name)
	{
		return enemies.get(name) != null;
	}
	
	/**
	 * Gets a Collection that holds the names of the Enemy stored
	 * in the list
	 * 
	 * @return Collection of Enemy names
	 */
	public Collection<String> getEnemyNameCollection()
	{
		return enemies.keySet();
	}
	
	/**
	 * Gets a Collection of Enemies stored in the list
	 * 
	 * @return Collection of Enemy objects
	 */
	public Collection<Enemy> getEnemyCollection()
	{
		return enemies.values();
	}
	
	/**
	 * Get an Iterator to the Enemies in the list
	 * 
	 * @return Iterator to enemies in the list
	 */
	public Iterator<Enemy> getEnemyIterator()
	{
		return enemies.values().iterator();
	}
	
	/**
	 * Get an Iterator to the Enemies in the list
	 * 
	 * @return Iterator to enemies in the list
	 */
	public Iterator<String> getEnemyNameIterator()
	{
		return enemies.keySet().iterator();
	}
}
