package dab.client.manager;

import java.io.IOException;

import org.joml.Vector2f;

import dab.client.network.ServerConnection;
import dab.common.entity.player.Player;

public class ClientUpdater implements Runnable {
    
    private ServerConnection conn;
    private ClientManager clientManager;
    private boolean running;
    
    public ClientUpdater(ServerConnection conn, ClientManager clientManager) {
        this.conn = conn;
        this.clientManager = clientManager;
        running = false;
    }
    
    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                String message = (String) conn.read();
                handleMessage(message);
            } catch (ClassNotFoundException | IOException e) {
                //e.printStackTrace();
                running = false;
            }
        }
    }
    
    private void handleMessage(String message) throws IOException, ClassNotFoundException {
        if (message.equals("request.heartbeat")) {
            conn.write("heartbeat");
        } else if (message.equals("update.player.client")) {
            clientManager.getPlayerList().setMainPlayer((Player) conn.read());
        } else if (message.equals("update.player.center")) {
        	handlePlayerLocationUpdate();
        } else if (message.equals("update.player.removal")) {
        	handlePlayerRemoval();
        } else if (message.equals("update.player.zone")) {
        	handlePlayerZoneUpdate();
        }
    }
    
    public void handlePlayerZoneUpdate() throws ClassNotFoundException, IOException {
    	String playerName = (String) conn.read();
    	String zoneName = (String) conn.read();
    	updatePlayerZone(playerName, zoneName);
    }
    
    public void updatePlayerZone(String playerName, String zoneName) {
    	if (isMainPlayer(playerName)) {
    		clientManager.getPlayerList().getMainPlayer().setZone(zoneName);
    		clientManager.getPlayerList().getMainPlayer().setIsZoneModified(true);
    	} else {
    		if(clientManager.getPlayerList().hasPlayer(playerName)) {
    			clientManager.getPlayerList().getRemotePlayer(playerName).setZone(zoneName);
    			clientManager.getPlayerList().getRemotePlayer(playerName).setIsZoneModified(true);
    		} else {
    			clientManager.getPlayerList().addRemotePlayer(new Player(playerName));
    			clientManager.getPlayerList().getRemotePlayer(playerName).setZone(zoneName);
    			clientManager.getPlayerList().getRemotePlayer(playerName).setIsZoneModified(true);
    		}
    	}
    }
    
    public void handlePlayerLocationUpdate() throws ClassNotFoundException, IOException {
    	String playerName = (String) conn.read();
    	Vector2f location = (Vector2f) conn.read();
    	updatePlayerLocation(playerName, location);
    }

    public void updatePlayerLocation(String playerName, Vector2f location) {
    	if (isMainPlayer(playerName)) {
    		clientManager.getPlayerList().getMainPlayer().setLocation(location);
    	} else {
    		if (clientManager.getPlayerList().hasPlayer(playerName)) {
    			clientManager.getPlayerList().getRemotePlayer(playerName).setLocation(location);
    		} else {
    			clientManager.getPlayerList().addRemotePlayer(new Player(playerName));
    			clientManager.getPlayerList().getRemotePlayer(playerName).setLocation(location);
    		}
    	}
    }
    
    private void handlePlayerRemoval() {
    	String playerName;
		try {
			playerName = (String) conn.read();
			clientManager.getPlayerList().removeRemotePlayer(playerName);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
    }
    
    public boolean isMainPlayer(String playerName) {
    	if (clientManager.getPlayerList().getMainPlayer().getName().equals(playerName)) {
    		return true;
    	}
    	return false;
    }
    
    public void stop() {
        running = false;
    }
    
}
