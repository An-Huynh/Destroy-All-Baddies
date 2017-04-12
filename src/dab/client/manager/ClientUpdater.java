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
        if (message.equals("request.heartbeart")) {
            conn.write("update.heartbeat");
        } else if (message.equals("update.player.client")) {
            //Player player = (Player) conn.read();
            //clientManager.getPlayerList().getMainPlayer().copy(player);
            clientManager.getPlayerList().setMainPlayer((Player) conn.read());
        } else if (message.equals("update.player.center")) {
        	handlePlayerLocationUpdate();
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
    	}
    }
    
    public boolean isMainPlayer(String playerName) {
    	return clientManager.getPlayerList().getMainPlayer().getName().equals(playerName);
    }
    
    public void stop() {
        running = false;
    }
    
}
