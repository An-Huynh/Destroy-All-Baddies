package dab.server.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketAcceptor {
	
	private ServerSocket listener;
	
	public void start(int port) throws IOException {
		this.listener = new ServerSocket();
		this.listener.setReuseAddress(true);
		this.listener.bind(new InetSocketAddress(port));
	}
	
	public Socket accept() throws IOException {
		return listener.accept();
	}
	
	public void stop() {
		try {
			listener.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
