package dab.client.entities;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import dab.common.entity.Player;

/**
 * This class is for managing a List of Player objects.
 * It can be used to add, remove, and get Players as well
 * as get Collections and Iterators to Players and their names
 */
public class PlayerList
{
	private Player mainPlayer;
	private Map<String, Player> players;
	
	/**
	 * Default Constructor
	 * sets mainPlayer to a default player
	 */
	public PlayerList()
	{
		players = new TreeMap<String, Player>();
		mainPlayer = new Player();
	}
	
	/**
	 * Add a Player object to the players list
	 * keyed by the player's unique name
	 * 
	 * @param player
	 */
	public void add(Player player)
	{
		players.put(player.getName(), player);
	}
	
	/**
	 * Sets the mainPlayer object to a different player
	 * 
	 * @param player
	 */
	public void setMainPlayer(Player player)
	{
		mainPlayer = player;
	}
	
	/**
	 * get the mainPlayer object managed by the list
	 * 
	 * @return mainPlayer
	 */
	public Player getMainPlayer()
	{
		return mainPlayer;
	}
	
	/**
	 * Remove a player from the list keyed by clientName parameter
	 * will not remove mainPlayer
	 * 
	 * @param clientName
	 */
	public void remove(String clientName)
	{
		players.remove(clientName);
	}
	
	/**
	 * Remove a player from players map
	 * 
	 * @param player
	 */
	public void remove(Player player)
	{
		players.remove(player.getName());
	}
	
	/**
	 * Get a player from the players map
	 * keyed by clientName
	 * 
	 * @param clientName
	 * 
	 * @return Player if found in player map
	 *         null otherwise
	 */
	public Player get(String clientName)
	{
		if (clientName.equals(mainPlayer.getName()))
		{
			return mainPlayer;
		}
		else
		{
			return players.get(clientName);
		}
	}

	public boolean hasPlayer(String name)
	{
		if (players.containsKey(name) || mainPlayer.getName().equals(name))
			return true;
		return false;
	}
	
	public Collection<Player> getPlayers()
	{
		return players.values();
	}
	
	public Iterator<Player> getPlayerIterator()
	{
		return players.values().iterator();
	}
}