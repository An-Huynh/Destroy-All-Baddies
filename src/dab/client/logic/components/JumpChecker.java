package dab.client.logic.components;

import java.io.IOException;

import dab.client.network.ServerConnection;
import dab.common.entity.attribute.JumpState;
import dab.common.entity.player.Player;
import dab.common.loop.Tickable;

public class JumpChecker implements Tickable {

	private Player player;
	private ServerConnection conn;
	
	public JumpChecker(Player player, ServerConnection conn) {
		this.player = player;
		this.conn = conn;
	}
	
	@Override
	public void update() throws IOException {
		if (player.getJumpState().equals(JumpState.READY)) {
			conn.write("update.jump.ready");
			player.setJumpState(JumpState.GROUND);
		}
	}
	
}