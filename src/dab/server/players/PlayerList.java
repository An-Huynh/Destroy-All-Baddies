package dab.server.players;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import dab.common.entity.attribute.Direction;
import dab.common.entity.attribute.JumpState;
import dab.common.entity.player.Player;

public class PlayerList {
	
	private Map<String, Player> players;
	
	public PlayerList() {
		players = new TreeMap<String, Player>();
	}
	
	public void addPlayer(String playerName) {
		players.put(playerName, createTempPlayer(playerName));
	}
	
	public Player getPlayer(String playerName) {
		return players.get(playerName);
	}
	
	public void removePlayer(String playerName) {
		players.remove(playerName);
	}
	
	public Collection<String> getKeys() {
		return players.keySet();
	}
	
	public Collection<Player> getPlayers() {
		return players.values();
	}
	
	
	private Player createTempPlayer(String playerName) {
		Player player = new Player();
		player.setName(playerName);
		player.setHeight(1.0f);
		player.setWidth(0.6f);
		player.setLocation(1.5f, 20.0f);
		player.setDirection(Direction.NONE);
		player.setZone("dab:zone:start");
		player.setJumpState(JumpState.GROUND);
		return player;
	}
	
}
