package dab.client.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import dab.client.manager.ClientManager;
import dab.common.entity.attribute.JumpState;
import dab.common.entity.player.Player;

public class ConnectionManager {
    
    private ServerConnection conn;
    private ClientManager clientManager;
    
    public ConnectionManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
    
    public void start(String host, int port) throws UnknownHostException, IOException {
        connect(host, port);
    }
    
    private void connect(String host, int port) throws UnknownHostException, IOException {
        conn = new ServerConnection(new Socket(host, port));
    }
    
    public void close() {
        conn.close();
    }
    
    public void sync(String name) throws ClassNotFoundException, IOException {
    	String controlMessage = (String) conn.read();
    	while (!controlMessage.equals("update.sync.complete")) {
    		if (controlMessage.equals("request.name")) {
    			System.out.println(name);
    			conn.write("update." + name);
    		}
    		controlMessage = (String) conn.read();
    	}
    	conn.write("request.player.client");
    	conn.read();
    	clientManager.getPlayerList().getMainPlayer().copy((Player) conn.read()); 
    	clientManager.getPlayerList().getMainPlayer().setJumpState(JumpState.GROUND);
    }
    
    public ClientManager getClientManager() {
        return clientManager;
    }
    
    public ServerConnection getConnection() {
        return conn;
    }
    
}
