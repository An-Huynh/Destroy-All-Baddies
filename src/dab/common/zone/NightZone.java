package dab.common.zone;

import org.joml.Vector2i;

import dab.common.registry.TileRegistry;
import dab.common.tile.TileLocation;

public class NightZone  extends Zone {
	
	public NightZone() {
        super();
        setName("dab:zone:night");
    }
    
    public void setup() {
    	
		// add ground layer
		for (int i = 0; i < 32; ++i) {
			addTile(TileRegistry.get("dab:tile:solid"), i, 0);
		}
		
		// Destinations
		TileLocation leftSideTeleport = new TileLocation("dab:zone:start", 
				                                         new Vector2i(31, 1));
		TileLocation rightSideTeleport = new TileLocation("dab:zone:graveyard",
				                                          new Vector2i(0, 1));
		
		// Add Teleporters
		for (int i = 0; i < 24; ++i) {
			addTile(TileRegistry.get("dab:tile:teleporter"), -1, i);
			TileLocation src = this.getTeleporterSrc(-1, i);
			this.getTeleporter().addTeleport(src, leftSideTeleport);
			
			addTile(TileRegistry.get("dab:tile:teleporter"), 32, i);
			src = this.getTeleporterSrc(32, i);
			this.getTeleporter().addTeleport(src, rightSideTeleport);
		}
    }
}