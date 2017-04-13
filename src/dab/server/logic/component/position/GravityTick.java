package dab.server.logic.component.position;

import dab.common.entity.player.Player;
import dab.common.physics.AABB;
import dab.server.logic.component.Tickable_S;
import dab.server.players.PlayerList;

public class GravityTick implements Tickable_S {
	
	private PlayerList playerList;
	
	public GravityTick(PlayerList playerList) {
		this.playerList = playerList;
	}
	
	@Override
	public void invoke() {
		for (Player player : playerList.getPlayers()) {
			if (!MovementCollisionChecker.checkFuturePlayerGravityCollision(player)) {
				updatePlayerPosition(player);
			}
		}
	}
	
	private void updatePlayerPosition(Player player) {
		AABB UpdatedAABB = MovementCollisionChecker.calculateNextPlayerGravityAABB(player);
		setPlayerAABB(player, UpdatedAABB);
		player.setIsCenterModified(true);
	}
	
	private Player setPlayerAABB(Player player, AABB aabb) {
		player.getAABB().copy(aabb);
		return player;
	}
	
}
