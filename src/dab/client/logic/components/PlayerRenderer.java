package dab.client.logic.components;

import java.util.Iterator;

import org.joml.Vector2f;

import dab.client.graphic.renderRegistry.VariableRenderRegistry;
import dab.client.manager.ClientManager;
import dab.common.loop.Tickable;

public class PlayerRenderer implements Tickable {
    
    private ClientManager clientManager;
    
    Vector2f center;
    
    public PlayerRenderer(ClientManager clientManager) {
        center = new Vector2f();
        this.clientManager = clientManager;
    }
    
    @Override
    public void update() {
        Iterator<String> key = clientManager.getPlayerList().getRemoteList().keySet().iterator();
        while (key.hasNext()) {
            String playerKey = key.next();
            center.set(clientManager.getPlayerList().getRemotePlayer(playerKey).getCenter());
            center.add(-0.5f, -0.5f);
            VariableRenderRegistry.get("dab:player").render(center.x, center.y);
            
        }
        
        
        
        // display main player
        center.set(clientManager.getPlayerList().getMainPlayer().getCenter());
        center.add(-0.5f, -0.5f);
        VariableRenderRegistry.get("dab:player").render(center.x, center.y);
    }
}
