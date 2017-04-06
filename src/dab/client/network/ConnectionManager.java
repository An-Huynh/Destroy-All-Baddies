package dab.client.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import dab.client.manager.ClientManager;
import dab.common.entity.player.Player;

public class ConnectionManager {
    
    private ServerConnection conn;
    private ClientManager clientManager;
    
    public ConnectionManager(ClientManager cm) {
        clientManager = cm;
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
        conn.write("update.name");
        conn.write(name);
        boolean playerSynced = false;
        conn.write("request.player.client");
        while (!playerSynced) {
            String controlMessage = (String) conn.read();
            if (controlMessage.equals("request.heartbeat")) {
                conn.write("heartbeat");
            } else if (controlMessage.equals("update.player.client")) {
                clientManager.getPlayerList().setMainPlayer((Player) conn.read());
                playerSynced = true;
            } else if (controlMessage.equals("request.name")) {
                // do nothing.already sent name
            } else if (controlMessage.equals("update.error")) {
                System.out.println((String) conn.read());
                throw new IOException();
            }
        }
    }
    
    public ClientManager getClientManager() {
        return clientManager;
    }
    
    public ServerConnection getConnection() {
        return conn;
    }
    
}
