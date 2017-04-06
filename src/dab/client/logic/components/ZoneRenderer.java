package dab.client.logic.components;

import dab.client.graphic.renderRegistry.RenderRegistry;
import dab.client.manager.ClientManager;
import dab.common.loop.Tickable;

public class ZoneRenderer implements Tickable {
    
    private ClientManager clientManager;
    
    public ZoneRenderer(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
    
    @Override
    public void update() {
        RenderRegistry.get(clientManager.getPlayerList().getMainPlayer().getZone()).render();
    }
    
}
