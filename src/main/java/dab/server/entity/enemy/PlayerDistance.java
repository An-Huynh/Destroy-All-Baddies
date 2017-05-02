package dab.server.entity.enemy;

import dab.common.entity.Player;

/**
 * This class is used to store the distance between a certain player
 * and an enemy. Primarily used to determine the closest player so
 * enemies know who to move towards
 * 
 * @author Eli Irvin
 *
 */
public class PlayerDistance
{
	private Player player;
	private float distance;
	
	/**
	 * Constructor for PlayerDistance
	 * 
	 * @param player - the player associated with this distance
	 * @param distance - the distance of the player
	 */
	public PlayerDistance(Player player, float distance)
	{
		this.player = player;
		this.distance = distance;
	}
	
	/**
	 * Gets the player
	 * 
	 * @return the player for this PlayerDistance
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * Gets the distance from the player
	 * 
	 * @return the distance for the PlayerDistance
	 */
	public float getDistance()
	{
		return distance;
	}
}
