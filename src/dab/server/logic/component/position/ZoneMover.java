package dab.server.logic.component.position;

import java.io.IOException;
import java.util.ArrayList;

import dab.common.entity.player.Player;
import dab.common.registry.ZoneRegistry;
import dab.common.tile.Tile;
import dab.common.zone.Zone;
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
	public void invoke(){
		for(Player player : playerList.getPlayers()) {
			sendZonetoAllPlayers(player);
			ArrayList<Tile> collidedTiles = new ArrayList<Tile>();
			if(MovementCollisionChecker.checkPlayerZoneCollisionIgnoreSolid(player)) {
				collidedTiles = MovementCollisionChecker.getTilesCollidingWithPlayerIgnoreSolid(player);
				for(Tile tile : collidedTiles) {
					if(tile.getName().equals("dab:tile:movezoneblock")) {
						Zone zone = ZoneRegistry.get(player.getZone());
						for(int i = 0; i < 24; ++i) {
							if(!zone.getLeftZone().equals("") && player.getLocation().x >= -1 && player.getLocation().x < 0) {
								player.setZone(zone.getLeftZone());
								player.setLocation(31, player.getLocation().y);
								sendZonetoAllPlayers(player);
							} else if(!zone.getRightZone().equals("") && player.getLocation().x <= 32 && player.getLocation().x > 31) {
								player.setZone(zone.getRightZone());
								player.setLocation(0, player.getLocation().y);
								sendZonetoAllPlayers(player);
								// TODO: Actually change the player's zone
							}
						}
					}
				}
			}
		}
	}
	
	private void sendZonetoAllPlayers(Player sourcePlayer) {
		for (Player player : playerList.getPlayers()) {
			sendZoneToPlayer(player.getName(), sourcePlayer);
		}
	}
	
	private void sendZoneToPlayer(String remotePlayerName, Player player) {
		ClientConnection conn = socketManager.getConnection(remotePlayerName);
		synchronized (conn.getOut()) {
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
