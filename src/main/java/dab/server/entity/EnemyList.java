package dab.server.entity;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import dab.common.entity.Enemy;
import dab.server.Server;
import dab.server.network.ClientConnection;

/**
 * This class is used to contain a list of all the enemies
 * currently in the game and give methods for adding and
 * removing those enemies.
 * 
 * @author Eli Irvin
 */
public class EnemyList
{
	private Map<String, Enemy> enemies;
	
	/**
	 * Default Constructor for EnemyList
	 */
	public EnemyList()
	{
		enemies = new TreeMap<String, Enemy>();
	}
	
	/**
	 * Adds an Enemy to the list
	 * 
	 * @param enemy - the Enemy to be added
	 */
	public synchronized void add(Enemy enemy)
	{
		enemies.put(enemy.getName(), enemy);
	}
	
	/**
	 * Gets an Enemy from the list
	 * 
	 * @param name - the name of the Enemy to be searched for
	 * 
	 * @return the Enemy with the name searched for or null
	 */
	public Enemy get(String name)
	{
		return enemies.get(name);
	}
	
	/**
	 * Removes an enemy from the list, then send a message to all clients
	 * to remove that enemy as well
	 * 
	 * @param name - the name of the Enemy to remove
	 */
	public synchronized void remove(String name)
	{
		enemies.remove(name);
		Iterator<ClientConnection> connIterator = Server.getClientManager().getConnectionIterator();
		while (connIterator.hasNext())
		{
			try
			{
				ClientConnection conn = connIterator.next();
				conn.write("enemy.removal");
				conn.write(name);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Gets an Iterator of the enemy list
	 * 
	 * @return Iterator of enemies
	 */
	public Iterator<Enemy> getEnemyIterator()
	{
		return enemies.values().iterator();
	}
	
	/**
	 * Gets a Collection of all the enemies
	 * 
	 * @return Collection of the Enemy values in enemies
	 */
	public Collection<Enemy> getEnemies()
	{
		return enemies.values();
	}
	
	/**
	 * Gets an Iterator of all enemies that are in zones that also
	 * have players in them
	 * 
	 * @return Iterator of updatable enemies
	 */
	public Iterator<Enemy> getUpdatableEnemyIterator()
	{
		List<String> zonesWithPlayer = Server.getPlayerList().getPlayers()
				                             .stream()
				                             .filter(player -> !player.getZoneID().equals(""))
				                             .map(player -> player.getZoneID())
				                             .collect(Collectors.toList());
		return enemies.values().stream()
				      .filter(enemy -> zonesWithPlayer.contains(enemy.getZoneID()))
				      .collect(Collectors.toList())
				      .iterator();
	}
}
