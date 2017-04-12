package dab.server.network;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class SocketAcceptor implements Runnable {
	
	private SocketManager socketManager;
	private boolean running;
	
	public SocketAcceptor(SocketManager socketManager) {
		this.socketManager = socketManager;
		running = false;
	}
	
	@Override
	public void run() {
		System.out.println("Starting Socket Acceptor thread...");
		running = true;
		while (running) {
			try {
				Socket socket = socketManager.accept();
				ClientConnection conn = new ClientConnection(socket);
				conn.writeObject("request.name");
				conn.readObject();
				String message = (String) conn.readObject();
				if (socketManager.getConnection(message) != null) {
				    conn.writeObject("update.error");
				    conn.writeObject("Player already has your name");
				    conn.close();
				} else {
				    socketManager.addConnection(message, conn);
				}
			} catch (SocketException e) {
				// probably from socket being closed server side
				running = false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// continue to run but print error message
				e.printStackTrace();
			}
		}
		System.out.println("Stopping Socket Acceptor thread...");
	}
	
}