package dab.client.logic;

import dab.client.logic.components.PlayerRenderer;
import dab.client.logic.components.PositionChecker;
import dab.client.logic.components.ZoneRenderer;
import dab.client.manager.ClientManager;
import dab.client.network.ConnectionManager;

public class LoopCycleInitializer {
    
    private static PositionChecker positionChecker;
    private static ZoneRenderer zoneRenderer;
    private static PlayerRenderer playerRenderer;
    
    public static void init(ClientManager clientManager, ConnectionManager connManager) {
        positionChecker = new PositionChecker(
            clientManager.getPlayerList().getMainPlayer(),
            connManager.getConnection());
        zoneRenderer = new ZoneRenderer(clientManager);
        playerRenderer = new PlayerRenderer(clientManager);
    }
    
    public static void postInit() {
        LoopCycle.registerTickable(positionChecker);
        LoopCycle.registerTickable(zoneRenderer);
        LoopCycle.registerTickable(playerRenderer);
    }
    
}
