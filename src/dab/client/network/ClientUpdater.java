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
		while (!stop) {
			try {
				update();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void update() throws ClassNotFoundException, IOException {
		String controlMessage = (String) conn.readObject();
		switch (controlMessage) {
			case "server.init.player": initPlayer(); break;
		}
	}
	
	// Helper Methods
	
	private void initPlayer() throws ClassNotFoundException, IOException {
		System.out.println("Sync - Start");
		
		Player player = (Player) conn.readObject();
		gameState.getPlayer().copy(player);
		System.out.println(player.getCenter().x + " " + player.getCenter().y);
		System.out.println("Sync - finish");
	}
	
	public void sync() throws IOException, ClassNotFoundException {
		conn.writeObject("client.init.start");
		conn.writeObject("Finmitz");
		update();
	}
	
	public void stop() {
		this.stop = true;
	}

}
