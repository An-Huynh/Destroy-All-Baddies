package dab.server.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import dab.common.entity.player.Player;
import dab.server.logic.PlayerUpdater;
import dab.server.players.PlayerList;

public class SocketManager {

	private Map<String, ClientConnection> connections;
	private PlayerList playerList;
	private ThreadPoolExecutor executor;
	
	private ServerSocket listener;
	
	private SocketAcceptor socketAcceptor;
	private SocketLifeChecker socketLifeChecker;
	
	public SocketManager(PlayerList playerList) {
		this.playerList = playerList;
		connections = new TreeMap<String, ClientConnection>();
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
	}
	
	public void start(int port) throws IOException {
		startListener(port);
		startAcceptor();
		startSocketChecker();
	}
	
	public void stop() throws IOException {
		listener.close();
		socketAcceptor.stop();
		socketLifeChecker.stop();
		executor.shutdownNow();
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
	
	public Socket acceptConnection() throws IOException {
		return listener.accept();
	}
	
	public void setupNewConnection(String playerName, ClientConnection conn) {
		connections.put(playerName, conn);
		playerList.addPlayer(playerName);
		startPlayerUpdater(playerList.getPlayer(playerName));
	}
	
	public void removeConnection(String playerName) throws IOException {
		playerList.removePlayer(playerName);
		ClientConnection conn = getConnection(playerName);
		connections.remove(playerName);
		conn.close();
		writeToAll("update.player.removal");
		writeToAll(playerName);
	}
	
	public ClientConnection getConnection(String playerName) {
		return connections.get(playerName);
	}
	
	private void startPlayerUpdater(Player player) {
		executor.execute(new PlayerUpdater(player, getConnection(player.getName())));
	}
	
	public void writeToAll(Object obj) throws IOException {
		for (ClientConnection conn : connections.values()) {
			conn.writeObject(obj);
		}
	}
	
	public Collection<String> getKeys() {
		return connections.keySet();
	}
	
	public Collection<ClientConnection> keyConnections() {
		return connections.values();
	}
	
}
