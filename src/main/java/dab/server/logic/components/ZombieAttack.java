package dab.server.logic.components;

import java.util.Iterator;

import dab.common.entity.Player;
import dab.common.entity.Zombie;
import dab.common.logic.Component;
import dab.common.physics.AABBMath;
import dab.server.Server;

public class ZombieAttack implements Component
{
	@Override
	public void invoke()
	{
		Server.getEnemyList().getEnemies()
			  .stream()
			  .filter(enemy -> "dab:enemy:zombie".equals(enemy.getClassName()))
			  .forEach(zombie -> processZombieKills((Zombie) zombie));
	}
	
	private void processZombieKills(Zombie zombie)
	{
		String zombieZoneID = zombie.getZoneID();
		Iterator<Player> players = Server.getPlayerList().getPlayerIteratorInZone(zombieZoneID);
		while (players.hasNext())
		{
			Player player = players.next();
			if (AABBMath.aabbCollides(player.getBoundingBox(), zombie.getBoundingBox()))
			{
				player.setZoneID("dab:zone:start");
				player.setLocation(0.0f, 1.0f);
				player.setDirty(true);
			}
		}
	}
}
