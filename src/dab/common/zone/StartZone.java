package dab.common.zone;

import org.joml.Vector2i;

import dab.common.registry.TileRegistry;
import dab.common.tile.TileLocation;

public class StartZone extends Zone {
    
    public StartZone() {
        super();
        setName("dab:zone:start");
    }
    
    public void setup() {
		// add ground layer
		for (int i = 0; i < 32; ++i) {
			addTile(TileRegistry.get("dab:tile:solid"), i, 0);
		}
		
		// Destinations
		TileLocation rightSideTeleport = new TileLocation("dab:zone:night", 
				                                         new Vector2i(0, 1));
		
		// Add Teleporters
		for (int i = 0; i < 24; ++i) {
			addTile(TileRegistry.get("dab:tile:teleporter"), 32, i);
			TileLocation src = this.getTeleporterSrc(32, i);
			this.getTeleporter().addTeleport(src, rightSideTeleport);
		}
		
		// add left Wall
		for (int i = 0; i < 24; ++i) {
			addTile(TileRegistry.get("dab:tile:solid"), -1, i);
		}
	}

}
