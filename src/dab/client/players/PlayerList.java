package dab.client.players;

import java.util.HashMap;
import java.util.Map;

import dab.common.entity.player.Player;

public class PlayerList {
    
    private Player mainPlayer;
    private Map<String, Player> remotePlayers;
    
    public PlayerList() {
        mainPlayer = new Player();
        remotePlayers = new HashMap<String, Player>();
    }
    
    public Player getMainPlayer() {
        return mainPlayer;
    }
    
    public Player getRemotePlayer(String playerName) {
    	return remotePlayers.get(playerName);
    }
    
    public void setMainPlayer(Player player) {
        mainPlayer.copy(player);
    }
    
    public void addRemotePlayer(Player player) {
        remotePlayers.put(player.getName(), player);
    }
    
    public void removeRemotePlayer(String playerName) {
    	remotePlayers.remove(playerName);
    }
    
    public boolean hasPlayer(String playerName) {
    	return remotePlayers.containsKey(playerName);
    }
    
    public Map<String, Player> getRemoteList() {
        return remotePlayers;
    }
    
}
