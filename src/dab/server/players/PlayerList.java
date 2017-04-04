package dab.server.players;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import dab.server.network.Player_S;
import dab.server.network.Player_S_Updater;

public class PlayerList {
	
	private static final Map<Player_S, Player_S_Updater> playerList = new HashMap<Player_S, Player_S_Updater>();
	private static final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
	
	
	public static void addPlayer(Player_S player) {
		playerList.put(player, new Player_S_Updater(player));
		executor.execute(playerList.get(player));
	}
	
	public static void removePlayer(Player_S player) {
		playerList.get(player).stop();
		playerList.remove(player);
	}
	
	public static Iterator<Player_S> getKeyIterator() {
		return playerList.keySet().iterator();
	}
	
	public static int getNumPlayers() {
		return playerList.size();
	}
	
}
