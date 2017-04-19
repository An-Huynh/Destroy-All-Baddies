package dab.server.logic;

import dab.server.logic.component.LocationUpdater;
import dab.server.logic.component.position.GravityTick;
import dab.server.logic.component.position.Movement;
import dab.server.logic.component.position.ZoneMover;
import dab.server.network.SocketManager;
import dab.server.players.PlayerList;

public class LoopComponentInitializer {
	
    private static Movement movement;
    private static LocationUpdater locationUpdater;
    private static GravityTick gravityTick;
    private static ZoneMover zoneMover;
    
	public static void preInit(SocketManager socketManager, PlayerList playerList) {
	    movement = new Movement(playerList);
	    gravityTick = new GravityTick(playerList);
	    locationUpdater = new LocationUpdater(playerList, socketManager);
	    zoneMover = new ZoneMover(playerList, socketManager);
	}
	
	public static void init() {
	    GameLoop.registerTickable(movement);
	    GameLoop.registerTickable(gravityTick);
	    GameLoop.registerTickable(locationUpdater);
	    GameLoop.registerTickable(zoneMover);
	}
	
}
 