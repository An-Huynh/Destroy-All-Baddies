package dab.server.entity.enemy;

import org.joml.Vector2f;

import dab.common.entity.Enemy;
import dab.common.entity.Player;
import dab.common.entity.attributes.Direction;
import dab.server.Server;

public class ZombieBehavior implements EnemyBehavior
{
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
	
	private float calcDistanceToPlayer(Player player, Enemy enemy)
	{
		Vector2f p1 = player.getBoundingBox().getMidPoint();
		Vector2f p2 = enemy.getBoundingBox().getMidPoint();
		
		return (float) Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
	
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
