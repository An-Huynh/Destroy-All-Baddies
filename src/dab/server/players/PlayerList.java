package dab.server.players;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import dab.common.entity.attribute.Direction;
import dab.common.entity.player.Player;
import dab.server.logic.PlayerUpdater;
import dab.server.network.ClientConnection;

public class PlayerList {
	
	private Map<String, Player> playerList;
	private ThreadPoolExecutor executor;
	
	public PlayerList() {
		playerList = new HashMap<String, Player>();
		executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
	}
	
	public void addPlayer(String name, ClientConnection conn) {
		playerList.put(name, createPlayer(name));
		executor.execute(new PlayerUpdater(getPlayer(name), conn, this));
	}
	
	public void removePlayer(String playerName) {
		System.out.println("removing " + playerName);
		playerList.remove(playerName);
	}
	
	public Iterator<String> getKeyIterator() {
		return playerList.keySet().iterator();
	}
	
	public Iterator<Player> getPlayerIterator() {
		return playerList.values().iterator();
	}
	
	public Player getPlayer(String key) {
		return playerList.get(key);
	}
	
	public void stop() {
		executor.shutdownNow();
	}
	
	public Map<String, Player> getPlayerList() {
	    return playerList;
	}
	
	// Helper methods
	
	private Player createPlayer(String name) {
		System.out.println("creating player w/ name " + name);
		Player player = new Player();
		player.setName(name);
		player.setDirection(Direction.NONE);
		player.setHeight(1.0f);
		player.setWidth(1.0f);
		player.setLocation(1.5f, 1.5f);
		player.setZone("dab:zone:start");
		return player;
	}
	
}
