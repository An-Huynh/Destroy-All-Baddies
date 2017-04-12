package dab.server.logic;

import java.io.IOException;

import dab.common.entity.attribute.Direction;
import dab.common.entity.player.Player;
import dab.server.network.ClientConnection;
import dab.server.players.PlayerList;

public class PlayerUpdater implements Runnable {
	
	private Player player;
	private PlayerList playerList;
	private ClientConnection conn;
	
	public PlayerUpdater(Player player, ClientConnection conn, PlayerList playerList) {
		this.player = player;
		this.conn = conn;
		this.playerList = playerList;
	}
	
	@Override
	public void run() {
		boolean running = true;
		while (running) {
		    try {
		        String controlMessage = (String) conn.readObject();
		        handleMessage(controlMessage);
		    } catch (IOException | ClassNotFoundException e) {
		        // e.printStackTrace();
		        running = false;
		    }
		}
	}
	
	// Temporary. will be replaced with a better expandable system
	private void handleMessage(String message) throws IOException, ClassNotFoundException {
	    if (message.equals("update.live")) {
	        // do nothing
	    } else if (message.equals("request.player.client")) {
	        conn.writeObject("update.player.client");
	        conn.writeObject(player);
	    } else if (message.equals("update.direction")) {
	    	System.out.println("reading direction update");
	        Direction direction = (Direction) conn.readObject();
	        player.setDirection(direction);
	    }
	}
	
}
