package dab.common.zone;

import org.joml.Vector2i;

import dab.common.registry.TileRegistry;
import dab.common.tile.TileLocation;

public class GraveyardZone extends Zone {
	
	public GraveyardZone() {
		super();
		setName("dab:zone:graveyard");
	}
	
	public void setup() {
		
		// add ground layer
		for (int i = 0; i < 32; ++i) {
			addTile(TileRegistry.get("dab:tile:solid"), i, 0);
		}
		
		// add graves
		addTile(TileRegistry.get("dab:tile:solid"), 8, 1);
		addTile(TileRegistry.get("dab:tile:solid"), 8, 2);
		
		addTile(TileRegistry.get("dab:tile:solid"), 11, 1);
		addTile(TileRegistry.get("dab:tile:solid"), 11, 2);
		
		addTile(TileRegistry.get("dab:tile:solid"), 13, 1);
		addTile(TileRegistry.get("dab:tile:solid"), 13, 2);
		
		addTile(TileRegistry.get("dab:tile:solid"), 14, 1);
		addTile(TileRegistry.get("dab:tile:solid"), 14, 2);
		
		// Destinations
		TileLocation leftSideTeleport = new TileLocation("dab:zone:nightzone", 
				                                         new Vector2i(31, 1));
		
		// Add Teleporters
		for (int i = 0; i < 24; ++i) {
			addTile(TileRegistry.get("dab:tile:teleporter"), -1, i);
			TileLocation src = this.getTeleporterSrc(-1, i);
			this.getTeleporter().addTeleport(src, leftSideTeleport);
		}
		
		// add Right Wall
		for (int i = 0; i < 24; ++i) {
			addTile(TileRegistry.get("dab:tile:solid"), 32, i);
		}
	}
	
}