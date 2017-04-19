package dab.server.logic.component.position;

import dab.common.entity.attribute.JumpState;
import dab.common.entity.player.Player;
import dab.common.physics.AABB;
import dab.server.logic.component.Tickable_S;
import dab.server.players.PlayerList;

public class JumpTick implements Tickable_S {
	
	private PlayerList playerList;
	
	public JumpTick(PlayerList playerList) {
		this.playerList = playerList;
	}
	
	@Override
	public void invoke() {
		for (Player player : playerList.getPlayers()) {
			if (isJumping(player)) {
				updatePlayerPosition(player);
			}
		}
	}
	
	public boolean isJumping(Player player) {
		return !player.getJumpState().equals(JumpState.GROUND);
	}
	
	public void updatePlayerPosition(Player player) {
		if (!MovementCollisionChecker.checkFuturePlayerJumpCollision(player)) {
			AABB nextAABB = MovementCollisionChecker.calculateNextPlayerJumpAABB(player);
			setPlayerAABB(player, nextAABB);
			player.setIsCenterModified(true);
			player.iterateJumpState();
		} else {
			setPlayerGrounded(player);
		}
	}
	
	private void setPlayerGrounded(Player player) {
		player.setJumpState(JumpState.GROUND);
	}
	
	private Player setPlayerAABB(Player player, AABB aabb) {
		player.getAABB().copy(aabb);
		return player;
	}
	
	
}
