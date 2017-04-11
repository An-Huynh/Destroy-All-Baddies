package dab.server.logic.component.position;

import java.io.IOException;
import java.util.Iterator;

import dab.common.entity.player.Player;
import dab.common.physics.AABB;
import dab.server.logic.component.Tickable_S;
import dab.server.network.ClientConnection;
import dab.server.network.SocketManager;
import dab.server.players.PlayerList;

public class Movement implements Tickable_S {
	
	private PlayerList playerList;
	private SocketManager socketManager;
	
	public Movement(PlayerList playerList, SocketManager socketManager) {
		this.playerList = playerList;
		this.socketManager = socketManager;
	}
	
	@Override
	public void update() {
		Iterator<Player> players = getPlayerIterator();
		while (players.hasNext()) {
			Player player = players.next();
			updatePlayerPosition(player);
		}
		
		sendUpdateToAllPlayers();
		
	}
	
	private Iterator<Player> getPlayerIterator() {
		return playerList.getPlayerList().values().iterator();
	}
	
	private void updatePlayerPosition(Player player) {
		AABB updatedAABB = MovementCollisionChecker.calculateNextPlayerAABB(player);
		if (!MovementCollisionChecker.checkPlayerZoneSolidCollision(player)) {
			setPlayerAABB(player, updatedAABB);
		}
	}
	
	private Player setPlayerAABB(Player player, AABB aabb) {
		player.getAABB().copy(aabb);
		return player;
	}
	
	private void sendUpdateToAllPlayers() {
		Iterator<Player> players = getPlayerIterator();
		while (players.hasNext()) {
			Player player = players.next();
			sendPlayerPositionToAll(player);
		}
	}
	
	private void sendPlayerPositionToAll(Player player) {
		Iterator<Player> clients = getPlayerIterator();
		while (clients.hasNext()) {
			ClientConnection conn = getPlayerConnection(clients.next());
			synchronized (conn.getOut()) {
				try {
					conn.writeObject("update.playerlist");
					conn.getOut().reset();
					conn.writeObject(playerList.getPlayerList());
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}
	
	private ClientConnection getPlayerConnection(Player player) {
		return socketManager.getConnection(player.getName());
	}
    
}
