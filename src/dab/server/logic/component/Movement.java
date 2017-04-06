package dab.server.logic.component;

import java.io.IOException;
import java.util.Iterator;

import dab.common.entity.attribute.Direction;
import dab.common.entity.player.Player;
import dab.common.physics.AABB;
import dab.common.registry.ZoneRegistry;
import dab.common.tile.Tile;
import dab.common.zone.Zone;
import dab.server.network.ClientConnection;
import dab.server.network.SocketManager;
import dab.server.players.PlayerList;

public class Movement implements Tickable_S {
    
    private PlayerList playerList;
    private SocketManager socketManager;
    private AABB boundingBox;
    
    public Movement(PlayerList playerList, SocketManager socketManager) {
        boundingBox = new AABB();
        this.socketManager = socketManager;
        this.playerList = playerList;
    }
    
    @Override
    public void update() {
        Iterator<String> keyIterator = playerList.getKeyIterator();
        while (keyIterator.hasNext()) {
            String playerKey = keyIterator.next();
            Player player = playerList.getPlayer(playerKey);
            calculatePossibleLocation(player.getAABB(), player.getDirection());
            if (checkLocationSafe(this.boundingBox, player.getZone())) {
                player.getAABB().copy(this.boundingBox);
            }
        }
        
        // all players have been updated. send to each client
        keyIterator = playerList.getKeyIterator();
        while (keyIterator.hasNext()) {
            String playerKey = keyIterator.next();
            ClientConnection conn = socketManager.getConnection(playerKey);
            try {
                conn.writeObject("update.playerlist");
                conn.getOut().reset();
                conn.writeObject(playerList.getPlayerList());
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }
    
    
    
    
    
    /*
    @Override
    public void update() {
        Iterator<String> keyIterator = playerList.getKeyIterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            Player player = playerList.getPlayer(key);
            calculatePossibleLocation(player.getAABB(), player.getDirection());
            if (checkLocationSafe(this.boundingBox, player.getZone())) {
                player.getAABB().copy(this.boundingBox);
                try {
                    synchronized(this.socketManager.getConnection(key).getOut()) {
                        this.socketManager.getConnection(key).writeObject("update.player.client");
                        this.socketManager.getConnection(key).getOut().reset();
                        this.socketManager.getConnection(key).writeObject(player);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }
            }
        }
        
    }
    */
    
    private void calculatePossibleLocation(AABB boundingBox, Direction direction) {
        this.boundingBox.copy(boundingBox);
        if (direction == Direction.LEFT) {
            this.boundingBox.addOffset(-0.1f, 0.0f);
        } else if (direction == Direction.RIGHT) {
            this.boundingBox.addOffset(0.1f, 0.0f);
        }
    }
    
    private boolean checkLocationSafe(AABB bb, String zoneName) {
        Zone zone = ZoneRegistry.get(zoneName);
        if (zone != null) {
            int centerX = (int) Math.floor(bb.getCenter().x);
            int centerY = (int) Math.floor(bb.getCenter().y);
            
            for (int i = centerX-1; i <= centerX+1; ++i) {
                for (int j = centerY-1; j <= centerY+1; ++j) {
                    Tile tile = zone.getTile(i, j);
                    AABB tileAABB = zone.getTileAABB(i, j);
                    
                    if (tile != null && tile.isSolid() && tileAABB.testCollision(bb)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
}
