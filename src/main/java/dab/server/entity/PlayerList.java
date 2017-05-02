package dab.server.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import dab.common.entity.Player;
import dab.common.entity.attributes.Direction;
import dab.common.entity.attributes.JumpState;
import dab.common.physics.AABB;

/**
 * This class is used to contain a list of all the players
 * currently in the game and give methods for adding and
 * removing those players.
 * 
 * @author Eli Irvin
 */
public class PlayerList
{
	private Map<String, Player> players;
	
	/**
	 * Default Constructor for PlayerList
	 */
	public PlayerList()
	{
		players = new TreeMap<String, Player>();
	}
	
	/**
	 * Adds a player to the list
	 * 
	 * @param player - the Player to be added
	 */
	public synchronized void add(Player player)
	{
		players.put(player.getName(), player);
	}
	
	/**
	 * Adds a default player with the given name
	 * 
	 * @param clientName - name for the default player
	 */
	public synchronized void addDefault(String clientName)
	{
		Player player = createDefaultPlayer(clientName);
		add(player);
	}
	
	/**
	 * Removes a player from the list
	 * 
	 * @param clientName - name of Player to remove
	 */
	public synchronized void remove(String clientName)
	{
		players.remove(clientName);
	}
	
	/**
	 * Removes a player from the list
	 * 
	 * @param player - player to remove
	 */
	public synchronized void remove(Player player)
	{
		players.remove(player.getName());
	}
	
	/**
	 * Gets a player from the list
	 * 
	 * @param clientName - name of the player to get
	 * 
	 * @return the Player with the name given, or null
	 */
	public Player get(String clientName)
	{
		return players.get(clientName);
	}
	
	/**
	 * Gets a collection of all players
	 * 
	 * @return Collection of the Player values in players
	 */
	public Collection<Player> getPlayers()
	{
		return players.values();
	}
	
	/**
	 * Gets an Iterator of all players
	 * 
	 * @return Iterator of players
	 */
	public Iterator<Player> getPlayerIterator()
	{
		return players.values().iterator();
	}
	
	/**
	 * Gets an Iterator of all players in a specific zone
	 * 
	 * @param zone - zone to get iterator for
	 * 
	 * @return Iterator of all players in specified zone
	 */
	public Iterator<Player> getPlayerIteratorInZone(String zone)
	{
		return players.values().stream()
				      .filter(player -> player.getZoneID().equals(zone))
				      .iterator();
	}
	
	/**
	 * Creates a Player in a default state.
	 * 
	 * @param name - name of the default Player
	 * 
	 * @return Player in a default state
	 */
	private Player createDefaultPlayer(String name)
	{
		Player player = new Player();
		player.setName(name);
		player.setZoneID("dab:zone:start");
		player.setJumpState(JumpState.GROUND);
		player.setDirection(Direction.NONE);
		player.setBoundingBox(new AABB(3.5f, 3.5f, 0.8f, 1.0f));
		player.setDirty(true);
		return player;
	}
}
