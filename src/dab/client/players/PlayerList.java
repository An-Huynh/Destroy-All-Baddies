package dab.client.players;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import dab.common.entity.player.Player;

public class PlayerList {
    
    private Player mainPlayer;
    private Map<String, Player> remotePlayers;
    
    public PlayerList() {
        mainPlayer = new Player();
        remotePlayers = new TreeMap<String, Player>();
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
    
    public void setRemotePlayer(String playerName, Player player) {
    	getRemotePlayer(playerName).copy(player);
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
    
    public Collection<String> getRemotePlayerNames() {
    	return remotePlayers.keySet();
    }
    
    public Collection<Player> getRemotePlayers() {
    	return remotePlayers.values();
    }
    
    public Iterator<String> getIteratorRemotePlayersName() {
    	return remotePlayers.keySet().iterator();
    }
    
    public Iterator<Player> getIteratorRemotePlayers() {
    	return remotePlayers.values().iterator();
    }
    
}
