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

public class ShootingEvent implements Event
{
	private Player player;
	private Vector2f target;
	private List<Enemy> enemiesToKill;
	
	public ShootingEvent(Player player, Vector2f target)
	{
		this.player = player;
		this.target = target;
		enemiesToKill = new ArrayList<Enemy>();
	}
	
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
	
	private Iterator<Enemy> getEnemiesInZone(String zoneID)
	{
		return Server.getEnemyList().getEnemies()
				     .stream()
				     .filter(enemy -> enemy.getZoneID().equals(zoneID))
				     .iterator();
	}
	
	private void shootAtEnemy(Enemy enemy, Vector2f target)
	{
		if (AABBMath.aabbCollides(enemy.getBoundingBox(), new AABB(target.x, target.y, 0, 0)))
		{
			enemiesToKill.add(enemy);
		}
	}
}
