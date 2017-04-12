package dab.server.logic;

import dab.server.logic.component.position.Movement;
import dab.server.network.SocketManager;
import dab.server.players.PlayerList;

public class LoopComponentInitializer {
	
    private static Movement movement;
    
	public static void preInit(SocketManager socketManager, PlayerList playerList) {
	    movement = new Movement(playerList, socketManager);
	}
	
	public static void init() {
	    GameLoop.registerTickable(movement);
	}
	
}
 