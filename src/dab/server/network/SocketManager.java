package dab.server.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import dab.server.players.PlayerList;

public class SocketManager {

	private Map<String, ClientConnection> connections;
	
	private PlayerList playerList;
	
	private ServerSocket listener;
	
	private SocketAcceptor socketAcceptor;
	private SocketLifeChecker socketLifeChecker;
	
	public SocketManager(PlayerList playerList) {
		connections = new HashMap<String, ClientConnection>();
		this.playerList = playerList;
	}
	
	public void start(int port) throws IOException {
		startListener(port);
		startAcceptor();
		startSocketChecker();
	}
	
	public void stop() {
		if (listener != null) {
			try {
				if (socketLifeChecker != null) {
					socketLifeChecker.stop();
				}
				listener.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				listener = null;
			}
		}
	}
	
	private void startListener(int port) throws IOException {
		listener = new ServerSocket();
		listener.setReuseAddress(true);
		listener.bind(new InetSocketAddress(port));
	}
	
	private void startAcceptor() {
		socketAcceptor = new SocketAcceptor(this);
		Thread socketAcceptorThread = new Thread(socketAcceptor);
		socketAcceptorThread.start();
	}
	
	private void startSocketChecker() {
		socketLifeChecker = new SocketLifeChecker(this);
		Thread socketCheckerThread = new Thread(socketLifeChecker);
		socketCheckerThread.start();
	}
	
	
	// Other
	public Socket accept() throws IOException {
		return listener.accept();
	}
	
	public void addConnection(String name, ClientConnection conn) throws IOException {
		System.out.println(name + " : connection added");
		if (connections.size() < 4) {
			connections.put(name, conn);
			playerList.addPlayer(name, conn);
		} else {
			try {
				conn.writeObject("server.room.full");
			} catch (IOException e) {
				conn.close();
				throw e;
			}
		}
	}
	
	public void removeConnection(String name) {
		System.out.println(name + " : connection removed");
		connections.remove(name);
		playerList.removePlayer(name);
	}
	
	public Iterator<String> getKeyIterator() {
		return connections.keySet().iterator();
	}
	
	public ClientConnection getConnection(String key) {
		return connections.get(key);
	}
	
	public void writeMessage(String name, Object obj) {
		ClientConnection conn = getConnection(name);
		if (conn != null) {
			synchronized(conn.getOut()) {
				try {
					conn.writeObject(obj);
				} catch (IOException e) {
					// do nothing. continue to run and let socketlifechecker handle
					e.printStackTrace();
				}
			}
		}
	}
	
	public Object readMessage(String name) {
		ClientConnection conn = getConnection(name);
		if (conn != null) {
			synchronized(conn.getIn()) {
				try {
					return conn.readObject();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		PlayerList playerList = new PlayerList();
		
		
		SocketManager sm = new SocketManager(playerList);
		try {
			sm.start(7720);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		Scanner in = new Scanner(System.in);
		while (!in.nextLine().equals("/stop")) {}
		sm.stop();
		in.close();
		System.out.println("Shutting Down...");
		Thread.sleep(15000);
		
		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
	      int noThreads = currentGroup.activeCount();
	      Thread[] lstThreads = new Thread[noThreads];
	      currentGroup.enumerate(lstThreads);
	      
	      for (int i = 0; i < noThreads; i++) System.out.println("Thread No:" + i + " = " + lstThreads[i].getName());
		
		
	}
	
}
