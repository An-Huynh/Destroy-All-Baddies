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
 * 
 * @author Eli Irvin
 */
public class PlayerList
{
	private Player mainPlayer;
	private Map<String, Player> players;
	
	/**
	 * Default Constructor
	 * 
	 * Sets mainPlayer to a default player
	 */
	public PlayerList()
	{
		players = new TreeMap<String, Player>();
		mainPlayer = new Player();
	}
	
	/**
	 * Adds a Player object to the players list
	 * keyed by the player's unique name
	 * 
	 * @param player - Player to add to the map
	 */
	public void add(Player player)
	{
		players.put(player.getName(), player);
	}
	
	/**
	 * Sets the mainPlayer object to a different player
	 * 
	 * @param player - Player to set as mainPlayer
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
	 * @param clientName - name of the client to remove
	 */
	public void remove(String clientName)
	{
		players.remove(clientName);
	}
	
	/**
	 * Remove a player from players map
	 * 
	 * @param player - Player to remove
	 */
	public void remove(Player player)
	{
		players.remove(player.getName());
	}
	
	/**
	 * Get a player from the players map
	 * keyed by clientName
	 * 
	 * @param clientName - name of the player to get
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

	/**
	 * Checks if a player is inside the player list
	 * 
	 * @param name - name of the player to search for
	 * @return True if the name is in the map or is equal to main player |
	 *         False if the name is not found
	 */
	public boolean hasPlayer(String name)
	{
		if (players.containsKey(name) || mainPlayer.getName().equals(name)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets a Collection of Player objects
	 * 
	 * @return a Collection of player objects
	 */
	public Collection<Player> getPlayers()
	{
		return players.values();
	}
	
	/**
	 * Gets an Iterator for the player map
	 * 
	 * @return Iterator to players in the list
	 */
	public Iterator<Player> getPlayerIterator()
	{
		return players.values().iterator();
	}
}