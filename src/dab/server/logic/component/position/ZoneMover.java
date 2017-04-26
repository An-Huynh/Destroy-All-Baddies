package dab.server.logic.component.position;

import java.io.IOException;
import java.util.List;

import dab.common.entity.player.Player;
import dab.common.registry.TileRegistry;
import dab.common.tile.TileLocation;
import dab.server.logic.component.Tickable_S;
import dab.server.network.ClientConnection;
import dab.server.network.SocketManager;
import dab.server.players.PlayerList;

public class ZoneMover implements Tickable_S {

	private PlayerList playerList;
	private SocketManager socketManager;
	
	public ZoneMover(PlayerList playerList, SocketManager socketManager) {
		this.playerList = playerList;
		this.socketManager = socketManager;
	}
	
	@Override 
	public void invoke() {
		for (Player player : playerList.getPlayers()) {
			if (MovementCollisionChecker.checkPlayerZoneCollisionIgnoreSolid(player)) {
				List<TileLocation> locations = MovementCollisionChecker.getCollidingTeleporterLocations(player);
				if (!locations.isEmpty()) {
					TileLocation destination = TileRegistry.getTeleporter().getDestination(locations.get(0));
					player.setZone(destination.getZone());
					player.setLocation(destination.getLocationfv());
					player.setIsCenterModified(true);
					sendZoneUpdate(player);
				}
			}
		}
	}
	
	private void sendZoneUpdate(Player player) {
		for (Player client : playerList.getPlayers()) {
			sendZoneUpdateToClient(player, client);
		}
	}
	
	private void sendZoneUpdateToClient(Player player, Player client) {
		ClientConnection conn = socketManager.getConnection(client.getName());
		synchronized(conn.getOut()) {
			try {
				conn.writeObject("update.player.zone");
				conn.writeObject(player.getName());
				conn.writeObject(player.getZone());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
