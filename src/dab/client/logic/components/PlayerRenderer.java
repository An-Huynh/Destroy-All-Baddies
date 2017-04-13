package dab.client.logic.components;

import java.util.Iterator;

import org.joml.Vector2f;

import dab.client.graphic.renderRegistry.VariableRenderRegistry;
import dab.client.manager.ClientManager;
import dab.common.entity.player.Player;
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
    	Iterator<Player> players = clientManager.getPlayerList().getIteratorRemotePlayers();
    	while (players.hasNext()) {
    		setDrawCenter(players.next());
    		renderPlayer();
    	}
    	drawMainPlayer();
    }
    
    private void setDrawCenter(Player player) {
    	center.set(player.getCenter());
    	center.add(-0.5f, -0.5f);
    }
    
    private void renderPlayer() {
    	VariableRenderRegistry.get("dab:player").render(center.x, center.y);
    }
    
    private void drawMainPlayer() {
    	setDrawCenter(clientManager.getPlayerList().getMainPlayer());
    	renderPlayer();
    }
}
