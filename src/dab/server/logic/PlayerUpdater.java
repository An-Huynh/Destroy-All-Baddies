package dab.server.logic;

import java.io.IOException;

import dab.common.entity.player.Player;
import dab.server.network.ClientConnection;

public class PlayerUpdater implements Runnable {
	
	private Player player;
	private ClientConnection conn;
	
	public PlayerUpdater(Player player, ClientConnection conn) {
		this.player = player;
		this.conn = conn;
	}
	
	@Override
	public void run() {
		boolean running = true;
		while (running) {
			try {
				String controlMessage = (String) conn.readObject();
				if (controlMessage.equals("client.request.player")) {
					sendPlayer();
				} else if (controlMessage.equals("client.send.live")) {
					// do nothing
				}
			} catch (IOException | ClassNotFoundException  e) {
				//e.printStackTrace();
				running = false;
			}
		}
	}
	
	private void sendPlayer() throws IOException {
		System.out.println("writing player");
		conn.writeObject("server.send.player");
		conn.writeObject(player);
	}
}
