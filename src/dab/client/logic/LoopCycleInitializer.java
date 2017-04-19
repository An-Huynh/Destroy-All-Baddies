package dab.client.logic;

import dab.client.logic.components.JumpChecker;
import dab.client.logic.components.PlayerRenderer;
import dab.client.logic.components.PositionChecker;
import dab.client.logic.components.ZoneChecker;
import dab.client.logic.components.ZoneRenderer;
import dab.client.manager.ClientManager;
import dab.client.network.ConnectionManager;

public class LoopCycleInitializer {
    
    private static PositionChecker positionChecker;
    private static ZoneChecker zoneChecker;
    private static ZoneRenderer zoneRenderer;
    private static PlayerRenderer playerRenderer;
    private static JumpChecker jumpChecker;
    
    public static void init(ClientManager clientManager, ConnectionManager connManager) {
        positionChecker = new PositionChecker(
            clientManager.getPlayerList().getMainPlayer(),
            connManager.getConnection());
        zoneChecker = new ZoneChecker(
        		clientManager.getPlayerList().getMainPlayer(),
        		connManager.getConnection());
        zoneRenderer = new ZoneRenderer(clientManager);
        playerRenderer = new PlayerRenderer(clientManager);
        jumpChecker = new JumpChecker(
        	clientManager.getPlayerList().getMainPlayer(),
        	connManager.getConnection());
    }
    
    public static void postInit() {
        LoopCycle.registerTickable(positionChecker);
        LoopCycle.registerTickable(jumpChecker);
        LoopCycle.registerTickable(zoneRenderer);
        LoopCycle.registerTickable(playerRenderer);
        LoopCycle.registerTickable(zoneChecker);
    }
    
}
