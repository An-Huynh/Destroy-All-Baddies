package dab.client.players;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import dab.common.entity.player.Player;

public class BotList {

	public static Map<String, Player> bots = new HashMap<String, Player>();
	
	public static Player getBot(String name) {
		return bots.get(name);
	}
	
	public static void addBot(Player player) {
		bots.put(player.getName(), player);
	}
	
	public static void removeBot(String name) {
		bots.remove(name);
	}
	
	public static boolean hasBot(String playerName) {
		return bots.containsKey(playerName);
	}
	
	public static Collection<String> getBotNames() {
		return bots.keySet();
	}
	
	public static Collection<Player> getBots() {
		return bots.values();
	}
	
}
