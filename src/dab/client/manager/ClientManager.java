package dab.client.manager;

import dab.client.network.ServerConnection;
import dab.client.players.PlayerList;

public class ClientManager {

    private PlayerList players;
    private ClientUpdater clientUpdater;
    private Thread clientUpdaterThread;
    
    public ClientManager() {
        players = new PlayerList();
    }
    
    public PlayerList getPlayerList() {
        return players;
    }
    
    public void start(ServerConnection conn) {
        clientUpdater = new ClientUpdater(conn, this);
        clientUpdaterThread = new Thread(clientUpdater);
        clientUpdaterThread.start();
    }
    
    public void stop() {
        clientUpdater.stop();
    }
    
    public boolean isRunning() {
        return clientUpdaterThread.isAlive();
    }
}
