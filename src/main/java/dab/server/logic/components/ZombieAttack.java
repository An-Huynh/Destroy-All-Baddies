package dab.server.logic.components;

import java.util.Iterator;

import dab.common.entity.Player;
import dab.common.entity.Zombie;
import dab.common.logic.Component;
import dab.common.physics.AABBMath;
import dab.server.Server;

/**
 * This class handles the Zombie's attacks and handles the
 * death of Players from those attacks.
 * 
 * @author Eli Irvin
 */
public class ZombieAttack implements Component
{
	/**
	 * For each zombie in the enemyList, this will process its kills
	 */
	@Override
	public void invoke()
	{
		Server.getEnemyList().getEnemies()
			  .stream()
			  .filter(enemy -> "dab:enemy:zombie".equals(enemy.getClassName()))
			  .forEach(zombie -> processZombieKills((Zombie) zombie));
	}
	
	/**
	 * Gets all the Players in the same zone as the Zombie. Then, for each Player,
	 * it checks for collision between the Player and the Zombie. If there
	 * is collision, then Player is sent back to the start of the game.
	 * 
	 * @param zombie - the zombie to process
	 */
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
