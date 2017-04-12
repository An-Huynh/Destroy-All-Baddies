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
		this.playerList = playerList;
		this.conn = conn;
	}
	
	@Override
	public void run() {
		boolean running = true;
		while (running) {
			try {
				handleMessage();
			} catch (IOException | ClassNotFoundException e) {
				running = false;
			}
		}
	}
	
	private void handleMessage() throws ClassNotFoundException, IOException {
		String[] controlMessage = splitMessage((String) conn.readObject());
		if (controlMessage[0] != "") {
			if (controlMessage[0].equals("request")) {
				handleRequest(controlMessage[1]);
			} else if (controlMessage[0].equals("update")) {
				handleUpdate(controlMessage[1]);
			}
		}
	}
	
	private String[] splitMessage(String message) {
		String[] components = new String[2];
		if (message.contains(".")) { // can be split into parts
			components[0] = message.substring(0, message.indexOf('.'));
			components[1] = message.substring(message.indexOf('.') + 1);
		} else { // store entire message in second part
			components[0] = "";
			components[1] = message;
		}
		return components;
	}
	
	private void handleRequest(String message) throws IOException {
		String[] controlMessage = splitMessage(message);
		if (controlMessage[0].equals("player")) {
			writePlayer(controlMessage[1]);
		}
	}
	
	private void writePlayer(String message) throws IOException {
		if (message.equals("client")) {
			synchronized (conn.getOut()) {
				conn.writeObject("update.player.client");
				conn.writeObject(player);
			}
		}
	}
	
	private void handleUpdate(String message) throws ClassNotFoundException, IOException {
		String[] controlMessage = splitMessage(message);
		if (controlMessage[0].equals("")) {
			updateSelf(controlMessage[1]);
		}
	}
	
	private void updateSelf(String message) throws ClassNotFoundException, IOException {
		if (message.equals("direction")) {
			player.setDirection((Direction) conn.readObject());
		}
	}
	
}
