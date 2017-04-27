package dab.client.logic.components;

import org.joml.Vector2f;

import dab.client.graphic.renderRegistry.VariableRenderRegistry;
import dab.client.manager.ClientManager;
import dab.client.players.BotList;
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
    	for(Player player : clientManager.getPlayerList().getRemotePlayers()) {
    		if(player.getZone() != null) {
	    		if(player.getZone().equals(clientManager.getPlayerList().getMainPlayer().getZone())) {
	    			setDrawCenter(player);
	    			renderPlayer();
	    		}
    		}
    	}
    	for (Player bot : BotList.getBots()) {
    		if (bot.getZone() != null) {
    			if (bot.getZone().equals(clientManager.getPlayerList().getMainPlayer().getZone())) {
    				setDrawCenter(bot);
    				renderPlayer();
    			}
    		}
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
