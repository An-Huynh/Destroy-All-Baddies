package dab.server.logic;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

import org.joml.Vector2f;

import dab.common.entity.attribute.Direction;
import dab.common.entity.enemy.Enemy;
import dab.common.entity.enemy.Zombie;
import dab.common.entity.player.Player;
import dab.common.registry.EnemyRegistry;
import dab.server.GameServer;
import dab.server.logic.component.Tickable_S;
import dab.server.logic.component.position.MovementCollisionChecker;
import dab.server.network.ClientConnection;

public class AIComponent implements Tickable_S {

	@Override
	public void invoke() throws IOException {
		Iterator<Enemy> enemyIterator = EnemyRegistry.getEnemies().iterator();
		while (enemyIterator.hasNext()) {
			updateEnemy(enemyIterator.next());
		}
	}
	
	private void updateEnemy(Enemy enemy) {
		if (enemy.getClassName().equals("zombie")) {
			handleZombieEnemy((Zombie) enemy);
		}
	}
	
	private void handleZombieEnemy(Zombie zombie) {
		Player target = findPlayerInZone(zombie).orElse(null);
		if (target != null) {
			if (target.getCenter().x > zombie.getCenter().x) {
				moveZombie(zombie, Direction.RIGHT);
			} else {
				moveZombie(zombie, Direction.LEFT);
			}
		}
		updateAllClients(zombie);
	}
	
	private Optional<Player> findPlayerInZone(Zombie zombie) {
		return GameServer.playerList
				         .getPlayers()
				         .stream()
				         .filter(p -> p.getZone().equals(zombie.getZone()))
				         .findAny();
	}
	
	private void moveZombie(Zombie zombie, Direction direction) {
		zombie.setDirection(direction);
		Vector2f movement = MovementCollisionChecker.calculateNextPlayerAABB(zombie)
				                .getCenter()
				                .sub(zombie.getCenter());
		movement.x *= 0.5;
		movement.y *= 0.5;
		
		zombie.setLocation(zombie.getCenter().add(movement));
		
		if (!MovementCollisionChecker.checkFuturePlayerGravityCollision(zombie)) {
			zombie.setLocation(MovementCollisionChecker.calculateNextPlayerGravityAABB(zombie).getCenter());
		}
	}
	
	private void updateAllClients(Zombie zombie) {
		Iterator<ClientConnection> conns = GameServer.socketManager.getConnections().iterator();
		while (conns.hasNext()) {
			try {
				updateClientZombie(zombie, conns.next());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateClientZombie(Zombie zombie, ClientConnection conn) throws IOException {
		synchronized(conn.getOut()) {
			conn.writeObject("update.bot.center");
			conn.writeObject(zombie.getName());
			conn.writeObject(zombie.getCenter());
			
			conn.writeObject("update.bot.zone");
			conn.writeObject(zombie.getName());
			conn.writeObject(zombie.getZone());
		}
	}
	
}
