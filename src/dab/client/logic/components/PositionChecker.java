package dab.client.logic.components;

import java.io.IOException;

import dab.client.network.ServerConnection;
import dab.common.entity.player.Player;
import dab.common.loop.Tickable;

public class PositionChecker implements Tickable {

    private Player player;
    private ServerConnection conn;
    
    public PositionChecker(Player player, ServerConnection conn) {
        this.player = player;
        this.conn = conn;
    }
     
    @Override
    public void update() throws IOException {
    	if (player.isDirectionModified()) {
    		conn.write("update.direction");
    		conn.write(player.getDirection());
    		player.setIsDirectionModified(false);
    	}
    }

}
