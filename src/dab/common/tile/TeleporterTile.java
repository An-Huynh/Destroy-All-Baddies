package dab.common.tile;

import java.util.HashMap;
import java.util.Map;

public class TeleporterTile extends Tile {
	
	private Map<TileLocation, TileLocation> destinations;
	
	public TeleporterTile() {
		super();
		
		setName("dab:tile:teleporter");
		setCenter(0.5f, 0.5f);
		setWidth(1.0f);
		setWidth(1.0f);
		setSolid(false);
		
		destinations = new HashMap<TileLocation, TileLocation>();
	}
	
	public void addTeleport(TileLocation src, TileLocation dest) {
		destinations.put(src, dest);
	}
	
	public TileLocation getDestination(TileLocation src) {
		return destinations.get(src);
	}
	
}
