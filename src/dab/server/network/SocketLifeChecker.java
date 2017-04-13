package dab.server.network;

import java.io.IOException;

public class SocketLifeChecker implements Runnable {
	
	private SocketManager socketManager;
	private boolean running;
	
	public SocketLifeChecker(SocketManager socketManager) {
		this.socketManager = socketManager;
		running = false;
	}
	
	@Override
	public void run() {
		running = true;
		while (running) {
			testConnections();
			pauseThread(5000);
		}
	}
	
	public void stop() {
		running = false;
	}
	
	private void pauseThread(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void testConnections() {
		for (String connectionKey : socketManager.getKeys()) {
			pingConnection(connectionKey);
		}
	}
	
	private void pingConnection(String connectionKey) {
		try {
			ClientConnection conn = socketManager.getConnection(connectionKey);
			conn.setTimeout(10000);
			conn.writeObject("request.heartbeat");
		} catch (IOException e) {
			try {
				socketManager.removeConnection(connectionKey);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
