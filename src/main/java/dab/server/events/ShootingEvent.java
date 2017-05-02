package dab.server.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.joml.Vector2f;

import dab.common.entity.Enemy;
import dab.common.entity.Player;
import dab.common.event.Event;
import dab.common.physics.AABB;
import dab.common.physics.AABBMath;
import dab.server.Server;

/**
 * This class is the event handler for shooting enemies. It has process
 * and methods to help achieve that process.
 * 
 * @author Eli Irvin
 *
 */
public class ShootingEvent implements Event
{
	private Player player;
	private Vector2f target;
	private List<Enemy> enemiesToKill;
	
	/**
	 * Constructor for ShootingEvent
	 * 
	 * @param player - Player that performed the shot
	 * @param target - Location of the shot that was fired
	 */
	public ShootingEvent(Player player, Vector2f target)
	{
		this.player = player;
		this.target = target;
		enemiesToKill = new ArrayList<Enemy>();
	}
	
	/**
	 * Gets all the enemies in the zone of the player who performed the
	 * shot. Then iterates through those enemies and attempts to shoot
	 * them. Them for all enemies scheduled to have been shot, it removes
	 * them from the EnemyList.
	 */
	@Override
	public void process()
	{
		Iterator<Enemy> enemies = getEnemiesInZone(player.getZoneID());
		while (enemies.hasNext())
		{
			shootAtEnemy(enemies.next(), target);
		}
		enemiesToKill.forEach(enemy -> Server.getEnemyList().remove(enemy.getName()));
	}
	
	/**
	 * Gets all the enemies inside a specific zone
	 * 
	 * @param zoneID - the zone to be checked against
	 * 
	 * @return Iterator of the Enemies inside the specified zone
	 */
	private Iterator<Enemy> getEnemiesInZone(String zoneID)
	{
		return Server.getEnemyList().getEnemies()
				     .stream()
				     .filter(enemy -> enemy.getZoneID().equals(zoneID))
				     .iterator();
	}
	
	/**
	 * Checks for collision with the enemies AABB and the location of the
	 * shot. If it detects collision it then adds that enemy to the list
	 * of ones to kill.
	 * 
	 * @param enemy - the Enemy to shoot at
	 * @param target - the location of the shot that was fired
	 */
	private void shootAtEnemy(Enemy enemy, Vector2f target)
	{
		if (AABBMath.aabbCollides(enemy.getBoundingBox(), new AABB(target.x, target.y, 0, 0)))
		{
			enemiesToKill.add(enemy);
		}
	}
}
