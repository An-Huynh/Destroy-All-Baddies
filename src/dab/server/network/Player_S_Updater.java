package dab.server.network;

public class Player_S_Updater implements Runnable {
	
	private Player_S player;
	private boolean stop;
	
	public Player_S_Updater(Player_S player) {
		this.player = player;
		this.stop = false;
	}
	
	public void run() {
		while (!stop) {
			player.update();
		}
	}
	
	public void stop() {
		this.stop = true;
	}

}
