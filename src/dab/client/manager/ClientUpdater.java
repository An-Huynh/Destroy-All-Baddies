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
        	System.out.println("sending heatbeat");
            conn.write("update.heartbeat");
        } else if (message.equals("update.player.client")) {
            //Player player = (Player) conn.read();
            //clientManager.getPlayerList().getMainPlayer().copy(player);
            clientManager.getPlayerList().setMainPlayer((Player) conn.read());
        } else if (message.equals("update.player.center")) {
        	handlePlayerLocationUpdate();
        } else if (message.equals("update.player.removal")) {
        	handlePlayerRemoval();
        }
    }
    
    public void handlePlayerLocationUpdate() throws ClassNotFoundException, IOException {
    	String playerName = (String) conn.read();
    	Vector2f location = (Vector2f) conn.read();
    	updatePlayerLocation(playerName, location);
    }

    public void updatePlayerLocation(String playerName, Vector2f location) {
    	if (isMainPlayer(playerName)) {
    		System.out.println("updating main player");
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
    	System.out.println(clientManager.getPlayerList().getMainPlayer().getName());
    	System.out.println(playerName);
    	if (clientManager.getPlayerList().getMainPlayer().getName().equals(playerName)) {
    		System.out.println("equals");
    		return true;
    	}
    	return false;
    }
    
    public void stop() {
        running = false;
    }
    
}
