package dab.server.entity.enemy;

import org.joml.Vector2f;

import dab.common.entity.Enemy;
import dab.common.entity.Player;
import dab.common.entity.attributes.Direction;
import dab.server.Server;

/**
 * This class is the behavior of the Zombie enemy. It contains
 * the standard operations of the Zombies, which is to find the
 * closest player and run towards that player. 
 * 
 * @author Eli Irvin
 */
public class ZombieBehavior implements EnemyBehavior
{
	/**
	 * Executes the behavior of the Zombie. This is to find
	 * the closest player. If there is not close player, then
	 * it will do nothing. If there is one, then it will
	 * set it's direction towards that player.
	 */
	@Override
	public void execBehavior(Enemy enemy)
	{
		Player closestPlayer = getClosestPlayerInZone(enemy);
		if (closestPlayer == null)
		{
			enemy.setDirection(Direction.NONE);
		}
		else
		{
			Direction direction = getPlayerDirectionRelativeToEnemy(closestPlayer, enemy);
			enemy.setDirection(direction);
		}
	}
	
	/**
	 * Finds the closest player to this Zombie. It does this by
	 * checking all the players in the PlayerList and filtering
	 * them to only be the ones in this Zombie's zone. It then
	 * calculates the distance between each player and finds the
	 * smallest distance.
	 * 
	 * @param enemy - the Enemy the be compared to for distance
	 * 
	 * @return the closet Player in the Zone or null is there 
	 *         are not players in the zone
	 */
	private Player getClosestPlayerInZone(Enemy enemy)
	{
		String enemyZoneID = enemy.getZoneID();
		synchronized(Server.getPlayerList())
		{
			return Server.getPlayerList().getPlayers().stream()
			             .filter(player -> player.getZoneID().equals(enemyZoneID))
			             .map(player -> new PlayerDistance(player, calcDistanceToPlayer(player, enemy)))
			             .sorted((x, y) -> Float.compare(x.getDistance(), y.getDistance()))
			             .map(pd -> pd.getPlayer())
			             .findFirst()
			             .orElse(null);
		}
	}
	
	/**
	 * Calculates the distance between a player and an enemy.
	 * 
	 * @param player - the Player to be used
	 * @param enemy - the Enemy to be used
	 * 
	 * @return the distance between the player and the enemy
	 */
	private float calcDistanceToPlayer(Player player, Enemy enemy)
	{
		Vector2f p1 = player.getBoundingBox().getMidPoint();
		Vector2f p2 = enemy.getBoundingBox().getMidPoint();
		
		return (float) Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
	
	/**
	 * Determines whether the player is to the left or right of the
	 * enemy. If it is to the right, then the enemy will go right.
	 * Otherwise, it will go left.
	 * 
	 * @param player - the player to be checked against
	 * @param enemy - the enemy to be checked against
	 * 
	 * @return the Direction relative to the Enemy
	 */
	private Direction getPlayerDirectionRelativeToEnemy(Player player, Enemy enemy)
	{
		if (player.getBoundingBox().getMidPoint().x > enemy.getBoundingBox().getMidPoint().x)
		{
			return Direction.RIGHT;
		}
		else
		{
			return Direction.LEFT;
		}
	}
}
