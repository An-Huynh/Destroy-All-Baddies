package dab.client.network;

import java.io.IOException;

import dab.client.state.GameState;
import dab.common.entity.player.Player;

public class ClientUpdater implements Runnable {
	
	private ServerConnection conn;
	private GameState gameState;
	private boolean stop;
	
	public ClientUpdater(GameState gameState, ServerConnection conn) {
		this.gameState = gameState;
		this.conn = conn;
		this.stop = false;
	}
	
	public void run() {
		System.out.println("running updater");
		while (!stop) {
			try {
				System.out.println("Calling Updater");
				update();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void update() throws ClassNotFoundException, IOException {
		String controlMessage = (String) conn.readObject();
		System.out.println(controlMessage);
		if (controlMessage.equals("server.request.name")) {
			sendName();
		} else if (controlMessage.equals("server.send.player")) {
			readPlayer();
		} else if (controlMessage.equals("server.send.check")) {
			conn.writeObject("client.send.live");
		}
	}
	
	// Helper Methods
	
	private void sendName() throws IOException {
		//conn.writeObject("finmitz");
	}
	
	private void readPlayer() throws ClassNotFoundException, IOException {
		System.out.println("Reading in player");
		Player player = (Player) conn.readObject();
		
		System.out.println(player.getZone());
		
		gameState.getPlayer().copy(player);
	}
	
	public void sync() throws IOException, ClassNotFoundException {
		conn.writeObject("finmitz");
		System.out.println("asking for player");
		conn.writeObject("client.request.player");
		update();
	}
	
	public void stop() {
		this.stop = true;
	}

}
