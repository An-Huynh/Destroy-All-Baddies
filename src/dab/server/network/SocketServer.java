package dab.server.network;

import java.io.IOException;
import java.net.Socket;

import dab.common.entity.player.Player;
import dab.server.players.PlayerList;

public class SocketServer implements Runnable {

	private SocketAcceptor socketAcceptor;
	private int port;
	private boolean stop;
	
	public SocketServer(int port) {
		this.port = port;
		this.stop = false;
		this.socketAcceptor = new SocketAcceptor();
	}
	
	public void run() {
		try {
			socketAcceptor.start(this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (!stop) {
			Socket conn;
			try {
				conn = socketAcceptor.accept();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			if (PlayerList.getNumPlayers() < 4) {
				try {
					Player_S player = new Player_S(new Player(), new ClientConnection(conn));
					PlayerList.addPlayer(player);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					conn.getOutputStream().write("init.connection.fail".getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void stop() {
		this.stop = true;
		socketAcceptor.stop();
	}
	
	
}
