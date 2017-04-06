package dab.client.manager;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

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
        } else if (message.equals("update.playerlist")) {
            Map<String, Player> playerList = (Map<String, Player>) conn.read();
            String mainPlayer = clientManager.getPlayerList().getMainPlayer().getName();
            clientManager.getPlayerList().getMainPlayer().copy(playerList.get(mainPlayer));
            
            Map<String, Player> remotePlayerList = clientManager.getPlayerList().getRemoteList();
            // remove names not on the list
            Iterator<String> iter = remotePlayerList.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                if (!playerList.containsKey(key)) {
                    remotePlayerList.remove(key);
                }
            }
            
           iter = playerList.keySet().iterator();
           while (iter.hasNext()) {
               String key = iter.next();
               if (!key.equals(mainPlayer)) {
                   remotePlayerList.put(key, playerList.get(key));
               }
           }
        }
    }

    public void stop() {
        running = false;
    }
    
}
