package dab.server.logic.component.position;

import java.util.Iterator;

import dab.common.entity.player.Player;
import dab.common.physics.AABB;
import dab.server.logic.component.Tickable_S;
import dab.server.players.PlayerList;

public class Movement implements Tickable_S {
	
	private PlayerList playerList;
	
	public Movement(PlayerList playerList) {
		this.playerList = playerList;
	}
	
	@Override
	public void invoke() {
		Iterator<Player> players = getPlayerIterator();
		while (players.hasNext()) {
			Player player = players.next();
			if (MovementCollisionChecker.isPlayerMoving(player)) {
				updatePlayerPosition(player);
			}
		}
	}
	
	private Iterator<Player> getPlayerIterator() {
		return playerList.getPlayerList().values().iterator();
	}
	
	private void updatePlayerPosition(Player player) {
		AABB updatedAABB = MovementCollisionChecker.calculateNextPlayerAABB(player);
		if (!MovementCollisionChecker.checkFuturePlayerZoneSolidCollision(player)) {
			setPlayerAABB(player, updatedAABB);
			player.setIsCenterModified(true);
		}
	}
	
	private Player setPlayerAABB(Player player, AABB aabb) {
		player.getAABB().copy(aabb);
		return player;
	}
    
}
