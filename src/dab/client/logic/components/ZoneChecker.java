package dab.client.logic.components;

import java.io.IOException;

import dab.client.network.ServerConnection;
import dab.common.entity.player.Player;
import dab.common.loop.Tickable;

public class ZoneChecker implements Tickable {

    private Player player;
    private ServerConnection conn;
    
    public ZoneChecker(Player player, ServerConnection conn) {
        this.player = player;
        this.conn = conn;
    }
     
    @Override
    public void update() throws IOException {
    	if (player.isZoneModified()) {
    		conn.write("update.zone");
    		conn.write(player.getZone());
    		player.setIsZoneModified(false);
    	}
    }

}
