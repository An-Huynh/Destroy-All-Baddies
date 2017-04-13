package dab.server.logic.component;

import java.io.IOException;

import dab.common.entity.player.Player;
import dab.server.network.ClientConnection;
import dab.server.network.SocketManager;
import dab.server.players.PlayerList;

public class LocationUpdater implements Tickable_S {

	private PlayerList playerList;
	private SocketManager socketManager;
	
	public LocationUpdater(PlayerList playerList, SocketManager socketManager) {
		this.playerList = playerList;
		this.socketManager = socketManager;
	}
	
	@Override
	public void invoke() throws IOException {
		for (Player player : playerList.getPlayers()) {
			if (player.isCenterModified()) {
				sendClientPositionToAll(player);
				player.setIsCenterModified(false);
			}
		}
	}
	
	private void sendClientPositionToAll(Player sourcePlayer) {
		for (Player player : playerList.getPlayers()) {
			sendPositionToPlayer(player.getName(), sourcePlayer);
		}
	}
	
	private ClientConnection getPlayerConnection(String playerName) {
		return socketManager.getConnection(playerName);
	}
	
	private void sendPositionToPlayer(String remotePlayerName, Player sourcePlayer) {
		ClientConnection conn = getPlayerConnection(remotePlayerName);
		synchronized (conn.getOut()) {
			try {
				conn.writeObject("update.player.center");
				conn.writeObject(sourcePlayer.getName());
				conn.writeObject(sourcePlayer.getCenter());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
