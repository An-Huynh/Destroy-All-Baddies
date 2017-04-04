package dab.server.network;

import dab.common.entity.attribute.Direction;
import dab.common.entity.player.Player;

public class Player_S {

	private Player player;
	private ClientConnection  conn;
	
	// Constructor
	
	public Player_S(Player player, ClientConnection conn) {
		this.player = player;
		this.conn = conn;
	}
	
	// Accessor
	
	public Player getPlayer() {
		return this.player;
	}
	
	public ClientConnection getConn() {
		return this.conn;
	}
	
	public void update() {
		String controlMessage = (String) conn.readObject();
		switch (controlMessage) {
			case "client.init.start": initialConnection(); break;
			case "client.player.update.direction": updateDirection(); break;
		}	
	}
	
	// Update helper functions
	public void initialConnection() {
		// For now, pre-define attributes for player
		player.setLocation(3.0f, 3.0f);
		player.setDirection(Direction.NONE);
		player.setZone("dab:zone:maze");
		player.setHeight(1.0f);
		player.setWidth(1.0f);
		player.setName((String) conn.readObject());
		
		System.out.println(player.getCenter().x + " " + player.getCenter().y);
		
		conn.writeObject("server.init.player");
		conn.writeObject(player);
	}
	
	public void updateDirection() {
		player.setDirection((Direction) conn.readObject());
	}
	
}
