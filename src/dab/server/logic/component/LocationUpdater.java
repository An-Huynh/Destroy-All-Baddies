package dab.server.logic.component;

import java.io.IOException;
import java.util.Iterator;

import org.joml.Vector2f;

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
		Iterator<Player> players = playerList.getPlayerIterator();
		while (players.hasNext()) {
			Player player = players.next();
			if (player.isCenterModified()) {
				System.out.println("yes");
				sendClientPositionToAll(player);
				player.setIsCenterModified(false);
			} else {
				System.out.println("no");
			}
		}
	}
	
	private void sendClientPositionToAll(Player player) {
		Iterator<Player> players = playerList.getPlayerIterator();
		while (players.hasNext()) {
			Player remotePlayer = players.next();
			sendPositionToPlayer(remotePlayer, player.getAABB().getCenter());
		}
	}
	
	private ClientConnection getPlayerConnection(Player player) {
		return socketManager.getConnection(player.getName());
	}
	
	private void sendPositionToPlayer(Player player, Vector2f position) {
		ClientConnection conn = getPlayerConnection(player);
		synchronized (conn.getOut()) {
			try {
				conn.writeObject("update.player.center");
				conn.writeObject(player.getName());
				conn.writeObject(position);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
