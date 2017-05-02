package dab.server.entity.enemy;

import dab.common.entity.Player;

public class PlayerDistance
{
	private Player player;
	private float distance;
	
	public PlayerDistance(Player player, float distance)
	{
		this.player = player;
		this.distance = distance;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public float getDistance()
	{
		return distance;
	}
}
